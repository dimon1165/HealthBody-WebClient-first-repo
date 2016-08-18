package edu.softserveinc.healthbody.webclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.softserveinc.healthbody.webclient.healthbody.webservice.HealthBodyService;
import edu.softserveinc.healthbody.webclient.healthbody.webservice.HealthBodyServiceImplService;

@Controller
public class UsersController {

	final Integer USERS_PER_PAGE = 4;

	@RequestMapping(value = "/userlist.html", method = RequestMethod.GET)
	public String getUserList(Model model, @Autowired HealthBodyServiceImplService healthBody,
			@RequestParam(value = "partNumber", required = false) Integer partNumber) {
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		int n = service.getAllUsers(1, Integer.MAX_VALUE).size();
		int lastPartNumber = (int) Math.ceil(n * 1.0 / USERS_PER_PAGE);
		if (partNumber == null || partNumber <= 0) {
			partNumber = 1;
		}
		if (partNumber > lastPartNumber) {
			partNumber = lastPartNumber;
		}
		int currentPage = partNumber;
		int startPartNumber = 1;
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("login", login);
		model.addAttribute("startPartNumber", startPartNumber);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPartNumber", lastPartNumber);
		model.addAttribute("AllUsers", service.getAllUsers(partNumber, USERS_PER_PAGE));
		return "userList";
	}
}