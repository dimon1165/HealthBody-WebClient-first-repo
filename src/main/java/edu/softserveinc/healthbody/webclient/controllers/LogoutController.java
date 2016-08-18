package edu.softserveinc.healthbody.webclient.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {
	@RequestMapping(value = "/logout*")
	public String logoutSpringSecurity(Model model) {
		SecurityContextHolder.clearContext();
		return "login";
	}
}
