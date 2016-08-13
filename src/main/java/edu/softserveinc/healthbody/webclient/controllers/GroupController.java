package edu.softserveinc.healthbody.webclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.softserveinc.healthbody.webclient.healthbody.webservice.GroupDTO;
import edu.softserveinc.healthbody.webclient.healthbody.webservice.HealthBodyService;
import edu.softserveinc.healthbody.webclient.healthbody.webservice.HealthBodyServiceImplService;
import edu.softserveinc.healthbody.webclient.healthbody.webservice.UserDTO;

@Controller
public class GroupController {

	@RequestMapping(value = "/listGroups.html", method = RequestMethod.GET)
	public String getGroups(Model model, @Autowired HealthBodyServiceImplService healthBody,
			@RequestParam(value = "groupsParticipantsPartnumber", required = false) Integer groupsParticipantsPartnumber) {

		/** Setting default quantity groups per page */
		final Integer DEFAULT_QUANTITY_GROUPS_PER_PAGE = 1;

		/**
		 * Avoid access to hole list of groups if in URL field will be inputed
		 * negative value -1 or lower (by hands)
		 */
		if (groupsParticipantsPartnumber == null || groupsParticipantsPartnumber <= 0)
			groupsParticipantsPartnumber = 1;

		/** Set current page */
		int currentPage = groupsParticipantsPartnumber;
		int startPartNumber = 1;

		String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();

		/** Getting quantity of records in groups table in DB */
		int dataBaseRecordsQuontity = service.getAllGroupsParticipants(1, Integer.MAX_VALUE).size();

		/** Calculating last page number without remain */
		int lastpagePartNumber = (int) Math.ceil(dataBaseRecordsQuontity * 1.0 / DEFAULT_QUANTITY_GROUPS_PER_PAGE);

		/**
		 * Avoid access to the blank page if in URL will be inputed value more
		 * than last page number (by hands)
		 */
		if (groupsParticipantsPartnumber > lastpagePartNumber)
			groupsParticipantsPartnumber = lastpagePartNumber;

		model.addAttribute("user", service.getUserByLogin(userLogin));
		model.addAttribute("startPartNumber", startPartNumber);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastpagePartNumber", lastpagePartNumber);
		model.addAttribute("groups",
				service.getAllGroupsParticipants(groupsParticipantsPartnumber, DEFAULT_QUANTITY_GROUPS_PER_PAGE));
		return "listGroups";
	}

	@RequestMapping(value = "/group.html", method = RequestMethod.GET)
	public String getGroup(Model model, @Autowired HealthBodyServiceImplService healthBody, String nameGroup) {
		String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		boolean test = false;
		for (GroupDTO group : service.getUserByLogin(userLogin).getGroups()) {
			if (group.getName().equals(nameGroup)) {
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

	@RequestMapping(value = "/Join the group.html", method = RequestMethod.GET)
	public String joinGroup(Model model, @Autowired HealthBodyServiceImplService healthBody, String nameGroup) {
		String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		UserDTO user = service.getUserByLogin(userLogin);
		user.getGroups().add(service.getGroupByName(nameGroup));
		service.updateUser(user);
		model.addAttribute("user", service.getUserByLogin(userLogin));
		return "usercabinet";
	}
}
