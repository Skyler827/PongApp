package com.codingdojo.pongapp.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.pongapp.models.User;
import com.codingdojo.pongapp.repositories.RoleRepository;
import com.codingdojo.pongapp.repositories.UserRepository;
import com.codingdojo.pongapp.services.UserService;
import com.codingdojo.pongapp.validators.UserValidator;

@Controller
public class PongAppController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserValidator userValidator;
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model, @ModelAttribute User user) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successful!");
        }
        return "loginReg.jsp";
    }
	
	@GetMapping("/success")
	public String success(Principal principal) {
		String email = principal.getName();
		User currentUser = userRepository.findByEmail(email);
		if(currentUser.getRoles().contains(roleRepository.findByName("ROLE_USER"))) {
			return "redirect:/users/"+currentUser.getId();
		}else {
			return "redirect:/packages";	
		}
	}
	
	@PostMapping(value="/register", params = "register")
	public String register(@Valid @ModelAttribute User user, BindingResult result, HttpSession session, @RequestParam String register, @RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request) {
		userValidator.validate(user, result);
		if(result.hasErrors()) {
			return "loginReg.jsp";
		}else {
			userService.saveUserWithRole(user);
			
			try {
				request.login(email, password);
			} catch (ServletException e) {
				System.out.println(e);
			}
			
			return "redirect:/dashboard";	
		}
	}
	
	@GetMapping("/dasboard")
	public String dashboard() {
		return "dashboard.jsp";
	}
	
}
