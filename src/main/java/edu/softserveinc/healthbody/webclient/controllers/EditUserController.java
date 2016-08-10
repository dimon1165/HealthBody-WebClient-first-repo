package edu.softserveinc.healthbody.webclient.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.softserveinc.healthbody.webclient.api.HealthBodyService;
import edu.softserveinc.healthbody.webclient.api.HealthBodyServiceImplService;
import edu.softserveinc.healthbody.webclient.api.UserDTO;

@Controller
@RequestMapping(value = "/editUser.html")
public class EditUserController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getUserForEdit(Map<String, Object> model, @Autowired HealthBodyServiceImplService healthBody, HttpServletRequest request) {
		String userLogin = request.getUserPrincipal().getName();
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		UserDTO userToEdit = service.getUserByLogin(userLogin);
		model.put("userToEdit", userToEdit);
		return "editUser";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String saveEdit(@ModelAttribute("userToEdit") UserDTO userToEdit, Map<String, Object> model, @Autowired HealthBodyServiceImplService healthBody, HttpServletRequest request) {
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		String userLogin = request.getUserPrincipal().getName();
		UserDTO user = service.getUserByLogin(userLogin);
		user.setFirstname(userToEdit.getFirstname());
		user.setLastname(userToEdit.getLastname());
		user.setAge(userToEdit.getAge());
		user.setWeight(userToEdit.getWeight());
		user.setGender(userToEdit.getGender());
		user.setHealth(userToEdit.getHealth());
		service.updateUser(user);
		model.put("user", service.getUserByLogin(userLogin));
//		userToEdit.setLogin(userLogin);
//		service.updateUser(userToEdit);
//		model.put("user", userToEdit);
		return "usercabinet";
	}

}
