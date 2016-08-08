package edu.softserveinc.healthbody.webclient.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.softserveinc.healthbody.webclient.api.HealthBodyService;
import edu.softserveinc.healthbody.webclient.api.HealthBodyServiceImplService;
import edu.softserveinc.healthbody.webclient.api.UserDTO;

@Controller
public class EditUserController {
	
	@RequestMapping(value = "/editUser.html",method = RequestMethod.GET)
	public String getUserForEdit(Model model, @Autowired HealthBodyServiceImplService healthBody, HttpServletRequest request) {
		String userLogin = request.getUserPrincipal().getName();
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		UserDTO userToEdit = service.getUserByLogin(userLogin);
		model.addAttribute("getUser", userToEdit);
		return "editUser";
	}
	
	@RequestMapping(value = "/editUser.html",method = RequestMethod.POST)
	public String saveEdit(Model model, @ModelAttribute("getUser") UserDTO userToEdit, @Autowired HealthBodyServiceImplService healthBody, HttpServletRequest request) {
		String userLogin = request.getUserPrincipal().getName();
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
	//	user = service.getUserByLogin(userLogin);
		userToEdit.setLogin(userLogin);
		service.updateUser(userToEdit);
		model.addAttribute("getUser", service.getUserByLogin(userLogin));
		return "usercabinet";
	}

}
