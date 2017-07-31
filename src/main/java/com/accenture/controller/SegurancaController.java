package com.accenture.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SegurancaController {

	@RequestMapping("/tasks/login")
	public String login(@AuthenticationPrincipal User user) {
		if (user != null) {
			return "redirect:/tasks";
		}
		
		return "login";
	}
	
}
