package edu.softserveinc.healthbody.webclient.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.softserveinc.healthbody.webclient.api.HealthBodyService;
import edu.softserveinc.healthbody.webclient.api.HealthBodyServiceImplService;

@Controller
public class CompetitionController {

	final Integer COMPETITIONS_PER_PAGE = 4;

	@RequestMapping(value = "/listCompetitions.html", method = RequestMethod.GET)
	public String getListCurrentCompetitions(Model model, @Autowired HealthBodyServiceImplService healthBody,
			@RequestParam(value = "partNumber", required = false) Integer partNumber, HttpServletRequest request) {

		if (partNumber == null) partNumber = 1;
		int currentPage = partNumber;
		int startPartNumber = 1;

		String userLogin = request.getUserPrincipal().getName();
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();

		int n = service.getAllActiveCompetitions(1, Integer.MAX_VALUE).size();
		int lastPartNumber = (int) Math.ceil(n * 1.0 / COMPETITIONS_PER_PAGE);

		model.addAttribute("getUser", service.getUserByLogin(userLogin));
		model.addAttribute("startPartNumber", startPartNumber);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPartNumber", lastPartNumber);
		model.addAttribute("getCompetitions", service.getAllActiveCompetitions(partNumber, COMPETITIONS_PER_PAGE));
		return "listCompetitions";
	}

}