package com.codingdojo.pongapp.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.codingdojo.pongapp.models.Role;
import com.codingdojo.pongapp.models.User;
import com.codingdojo.pongapp.repositories.RoleRepository;
import com.codingdojo.pongapp.repositories.UserRepository;
import com.codingdojo.pongapp.services.UserService;
import com.codingdojo.pongapp.socketobjects.ClientKeyEventMessage;
import com.codingdojo.pongapp.socketobjects.PongGame;
import com.codingdojo.pongapp.validators.UserValidator;

@EnableScheduling
@Controller
public class PongAppController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserValidator userValidator;
	@Autowired
    private SimpMessagingTemplate template;
	@Autowired
	private RoleRepository roleRepository;
	private long nextIdCounter = 1;
	HashMap<Long, PongGame> gamesMap = new HashMap<>();

	@GetMapping("/login")
    public String login(Principal principal, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model, @ModelAttribute User user) {
		
		if(roleRepository.findByName("ROLE_USER") == null) {
			roleRepository.save(new Role("ROLE_USER"));
		}
		if(roleRepository.findByName("ROLE_ADMIN") == null) {
			roleRepository.save(new Role("ROLE_ADMIN"));
		}
		if(principal != null) { return "redirect:/dashboard"; }
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successful!");
        }
        return "loginReg.jsp";
    }
	
	@PostMapping(value="/register", params = "register")
	public String register(@Valid @ModelAttribute User user, BindingResult result, HttpSession session, @RequestParam String register, @RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request) {
		userValidator.validate(user, result);
		if(result.hasErrors()) {
			return "loginReg.jsp";
		} else {
			userService.saveUserWithRole(user);
			
			try {
				request.login(email, password);
			} catch (ServletException e) {
				System.out.println(e);
			}
			
			return "redirect:/dashboard";	
		}
	}
	private long getNextId() {
		long x = nextIdCounter;
		nextIdCounter += 1;
		return x;
	}
	@GetMapping("/")
	public String success() {
		return "redirect:/dashboard";
	}
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard.jsp";
	}
	@GetMapping("/api/users")
	public List<User> userList() {
		return userService.findAll();
	}

	@GetMapping("/creategame")
	public String newPongGame(Principal p) {
		long x = getNextId();
		PongGame g = new PongGame(x);
		gamesMap.put(x, g);
		g.addUser(p.getName());

		return "redirect:/game/"+g.getId();
	}

	//
	@GetMapping("/game/{id}")
	public String joinSpecificGame(
			@PathVariable("id") long id, Model model,
			Principal p
	) {
		PongGame g = gamesMap.get(id);
		System.out.println("Size of gamesMap: "+gamesMap.size());
		if (g == null) {
			System.out.println("game doesn't exist");
			return "redirect:/dashboard";
		}
		g.addUser(p.getName());
		model.addAttribute("id", id);
		return "game_init.jsp";
	}


	@MessageMapping("/gameMovement")
	public void keyM(ClientKeyEventMessage message, Principal p
	) throws Exception{
	    PongGame g = gamesMap.get(message.getGameId());
		if (g == null) {throw new Exception("Error: no such active game with id: "+message.getGameId());}
		g.handleKeyEvent(message, p.getName());
	}

	//Would be great to get the different threads running to send 
	//the gamestates themselves, but convertAndSend returns an error
	//every time it's called from the ponggame.java thread
	private final int tickRate = 10;
	@Scheduled(fixedRate = tickRate)
	public void serverTick(){
		for(Long game : gamesMap.keySet()){
			PongGame g = gamesMap.get(game);
			if(g.isRunning){
				try{
					g.thread.run();
					this.template.convertAndSend("/topic/game/"+game, g.getStatus());
				}catch(Exception e){
					System.out.println("Error on scheduled send out");
					System.out.println(e);
				}
			}
		}
	}
}
