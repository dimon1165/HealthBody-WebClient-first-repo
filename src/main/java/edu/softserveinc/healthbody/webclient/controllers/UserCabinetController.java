package edu.softserveinc.healthbody.webclient.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.softserveinc.healthbody.webclient.api.HealthBodyService;
import edu.softserveinc.healthbody.webclient.api.HealthBodyServiceImplService;

@Controller
public class UserCabinetController {

	@RequestMapping(value = "/usercabinet.html",method = RequestMethod.GET)
	public String getUserList(Model model, @Autowired HealthBodyServiceImplService healthBody,HttpServletRequest request) {
		String userLogin = request.getUserPrincipal().getName();
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		model.addAttribute("getUser", service.getUserByLogin(userLogin));
		return "usercabinet";
	}

}
