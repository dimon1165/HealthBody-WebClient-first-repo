package edu.softserveinc.healthbody.webclient.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.softserveinc.healthbody.webclient.api.HealthBodyService;
import edu.softserveinc.healthbody.webclient.api.UserDTO;

@Controller
public class MainController {

	@RequestMapping(value = "/userlist.html", method = RequestMethod.GET)
	public String getBudget(@ModelAttribute("user") UserDTO user, Model model) {

		List<UserDTO> userDTO = HealthBodyService.getAllUsers(1, 5);

		model.addAttribute("GetAllUsers", userDTO);

		return "userlist";

	}

}