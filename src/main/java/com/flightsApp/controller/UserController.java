package com.flightsApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flightsApp.entity.User;
import com.flightsApp.repo.UserRepo;

@Controller
public class UserController {
	@Autowired
	private UserRepo userRepo;

	
	@RequestMapping(path="/showReg")
	public String showReg() {
		return"login/newRegistration";
	}
	
	@RequestMapping(path = "/showLogin")
	public String showLoginPage() {
		return "login/login";
	}
	
	@RequestMapping(path = "/saveReg")
	public String saveNewRegistration(@ModelAttribute("user") User user) {
		userRepo.save(user);
		return "login/login";
	}
	
	@RequestMapping(path = "/verifyLogin")
	public String verifyLogin(@RequestParam("emailId")String emailId,@RequestParam("password") String password,ModelMap modelmap) {
		User user = userRepo.findByEmail(emailId);
		
		try{
		if(user.getEmail().equals(emailId) && user.getPassword().equals(password)) {
			return "findFlights";
		}else {
			modelmap.addAttribute("error", "Invalid username/password");
			return "login/login";
		}
		}catch(Exception e) {
			modelmap.addAttribute("error", "Invalid username/password");
			return "login/login";
		}
	}
}
