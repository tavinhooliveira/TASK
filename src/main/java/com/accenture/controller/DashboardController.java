package com.accenture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/tasks/dashboard")
public class DashboardController {
	
	
	private static final String DASHBOARD = "/layout/Dashboard"; 
	
	
	//Dashboard
	@RequestMapping
	public ModelAndView exibir() {
		ModelAndView mv = new ModelAndView(DASHBOARD);
		return mv;
	}
	


}
