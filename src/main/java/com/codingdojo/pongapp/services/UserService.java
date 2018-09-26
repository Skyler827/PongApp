package com.codingdojo.pongapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.codingdojo.pongapp.models.Role;
import com.codingdojo.pongapp.models.User;
import com.codingdojo.pongapp.repositories.RoleRepository;
import com.codingdojo.pongapp.repositories.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder)     {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    public User saveUserWithRole(User user) {
    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    	List<Role> temp = new ArrayList<Role>();
    	temp.add(roleRepository.findByName("ROLE_USER"));
    	if(!adminExists()) {
    		temp.add(roleRepository.findByName("ROLE_ADMIN"));
    	}
    	user.setRoles(temp);
    	return userRepository.save(user);
    }   
    
    public boolean emailExists(String email) {
    	User user = userRepository.findByEmail(email);
    	if(user != null) {
    		return true;
    	}
    	return false;
    }
    public boolean adminExists() {
    	Role role = roleRepository.findByName("ROLE_ADMIN");
    	User user = userRepository.findByRoles(role);
    	if(user == null) { return false; }
    	else { return true; }
    }
    
}
