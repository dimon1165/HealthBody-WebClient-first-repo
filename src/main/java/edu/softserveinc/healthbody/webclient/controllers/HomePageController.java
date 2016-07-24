package edu.softserveinc.healthbody.webclient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {
	
	@RequestMapping("/HomePage.html")
	public String homePage() {
		return "HomePage";
	}
	
	@RequestMapping("/Login.html")
	public String login() {
		return "Login";
	}

}
