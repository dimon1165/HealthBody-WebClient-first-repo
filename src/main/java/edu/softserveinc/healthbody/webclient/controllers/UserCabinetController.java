package edu.softserveinc.healthbody.webclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.softserveinc.healthbody.webclient.api.HealthBodyService;
import edu.softserveinc.healthbody.webclient.api.HealthBodyServiceImplService;

@Controller
public class UserCabinetController {

	@RequestMapping(value = "/usercabinet.html")
	public String getUserList(Model model, @Autowired HealthBodyServiceImplService healthBody, String userLogin) {

		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		model.addAttribute("getUser", service.getUserByLogin(userLogin));
		return "usercabinet";
	}
	
}
