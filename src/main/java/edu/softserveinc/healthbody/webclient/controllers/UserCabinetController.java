package edu.softserveinc.healthbody.webclient.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.softserveinc.healthbody.webclient.api.HealthBodyService;
import edu.softserveinc.healthbody.webclient.api.HealthBodyServiceImplService;

@Controller
public class UserCabinetController {

	private ApplicationContext context;

	@RequestMapping(value = "/usercabinet.html")
	public String getUserList(Model model) {
		context = new AnnotationConfigApplicationContext(HealthBodyServiceImplService.class);
		HealthBodyServiceImplService healthBody = context.getBean(HealthBodyServiceImplService.class);
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		model.addAttribute("getUser", service.getUserByLogin("Login 5"));
		return "usercabinet";
	}
	
}
