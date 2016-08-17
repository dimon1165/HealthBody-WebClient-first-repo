package edu.softserveinc.healthbody.webclient.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.softserveinc.healthbody.webclient.healthbody.webservice.CompetitionDTO;
import edu.softserveinc.healthbody.webclient.healthbody.webservice.HealthBodyService;
import edu.softserveinc.healthbody.webclient.healthbody.webservice.HealthBodyServiceImplService;

@Controller
public class CompetitionController {

	final Integer COMPETITIONS_PER_PAGE = 4;

	@RequestMapping(value = "/listCompetitions.html", method = RequestMethod.GET)
	public String getListCurrentCompetitions(Model model, @Autowired HealthBodyServiceImplService healthBody,
			@RequestParam(value = "partNumber", required = false) Integer partNumber, HttpServletRequest request) {

		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();

		int n = service.getAllActiveCompetitions(1, Integer.MAX_VALUE).size();
		int lastPartNumber = (int) Math.ceil(n * 1.0 / COMPETITIONS_PER_PAGE);
		if (partNumber == null || partNumber <= 0)
			partNumber = 1;
		if (partNumber > lastPartNumber)
			partNumber = lastPartNumber;
		int currentPage = partNumber;
		int startPartNumber = 1;

		String userLogin = request.getUserPrincipal().getName();

		model.addAttribute("user", service.getUserByLogin(userLogin));
		model.addAttribute("startPartNumber", startPartNumber);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPartNumber", lastPartNumber);
		model.addAttribute("getCompetitions", service.getAllActiveCompetitions(partNumber, COMPETITIONS_PER_PAGE));
		return "listCompetitions";
	}

	@RequestMapping(value = "/competition.html", method = RequestMethod.GET)
	public String getCompetition(Model model, @Autowired HealthBodyServiceImplService healthBody,
			String nameCompetition) {
		String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		boolean test = false;
		for (CompetitionDTO competition : service.getAllCompetitionsByUser(1, Integer.MAX_VALUE, userLogin)) {
			if (competition.getName().equals(nameCompetition)) {
				test = true;
			}
		}
		model.addAttribute("user", service.getUserByLogin(userLogin));
		model.addAttribute("getCompetition", service.getCompetitionViewByName(nameCompetition));
		model.addAttribute("getScore", service.getUserCompetition(nameCompetition, userLogin));
		if (test) {
			return "competition";
		} else {
			return "Join the competition";
		}
	}
	
	@RequestMapping(value = "/Join the competition.html", method = RequestMethod.GET)
	public String joinCompetition(Model model, @Autowired HealthBodyServiceImplService healthBody, String nameCompetition) {
		String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		service.addUserInCompetitionView(nameCompetition, userLogin);
		model.addAttribute("user", service.getUserByLogin(userLogin));
		model.addAttribute("usercompetitions", service.getAllCompetitionsByUser(1, Integer.MAX_VALUE, userLogin));
		return "usercabinet";
	}

}