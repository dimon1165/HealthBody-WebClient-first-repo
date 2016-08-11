package edu.softserveinc.healthbody.webclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.softserveinc.healthbody.webclient.api.HealthBodyService;
import edu.softserveinc.healthbody.webclient.api.HealthBodyServiceImplService;

@Controller
public class MainController {
	
	@RequestMapping(value = "/userlist.html")
	public String getUserList(Model model, @Autowired HealthBodyServiceImplService healthBody) {
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		model.addAttribute("AllUsers", service.getAllUsers(1, 5));
		return "userlist";
	}
}