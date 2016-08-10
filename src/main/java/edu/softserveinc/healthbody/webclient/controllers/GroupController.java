package edu.softserveinc.healthbody.webclient.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.softserveinc.healthbody.webclient.api.GroupDTO;
import edu.softserveinc.healthbody.webclient.api.HealthBodyService;
import edu.softserveinc.healthbody.webclient.api.HealthBodyServiceImplService;
import edu.softserveinc.healthbody.webclient.api.UserDTO;

@Controller
public class GroupController {
	
	@RequestMapping(value = "/listGroups.html", method = RequestMethod.GET)
	public String getGroups(Model model, @Autowired HealthBodyServiceImplService healthBody, HttpServletRequest request) {
		String userLogin = request.getUserPrincipal().getName();
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		model.addAttribute("user", service.getUserByLogin(userLogin));
		model.addAttribute("groups", service.getAllGroupsParticipants(1, 3));
		return "listGroups";
	}
	
	@RequestMapping(value = "/group.html", method = RequestMethod.GET)
	public String getGroup(Model model, @Autowired HealthBodyServiceImplService healthBody, String nameGroup, HttpServletRequest request) {
		String userLogin = request.getUserPrincipal().getName();
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		boolean test = false;
		for (GroupDTO group : service.getUserByLogin(userLogin).getGroups()) {
			if(group.getName().equals(nameGroup)) {
				test = true;
			}
		}
		model.addAttribute("user", service.getUserByLogin(userLogin));
		model.addAttribute("group", service.getGroupByName(nameGroup));
		if (test) {
			return "group";
		} else {
			return "Join the group";
		}
	}
	
	@RequestMapping(value = "/Join the group.html",  method = RequestMethod.GET)
	public String joinGroup(Model model, @Autowired HealthBodyServiceImplService healthBody, String nameGroup, HttpServletRequest request) {
		String userLogin = request.getUserPrincipal().getName();
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		UserDTO user = service.getUserByLogin(userLogin);
		user.getGroups().add(service.getGroupByName(nameGroup));
		service.updateUser(user);
		model.addAttribute("user", service.getUserByLogin(userLogin));
		return "usercabinet";
	}
}
