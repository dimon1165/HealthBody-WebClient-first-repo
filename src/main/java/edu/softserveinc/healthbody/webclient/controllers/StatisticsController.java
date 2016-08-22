package edu.softserveinc.healthbody.webclient.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.softserveinc.healthbody.webclient.dto.StatisticsDTO;
import edu.softserveinc.healthbody.webclient.service.StatisticsService;

@Controller
public class StatisticsController {
	
	@Autowired(required = true)
	private StatisticsService statisticsService;
	
	@RequestMapping(value= "/statistics.html")
	public String loginSpringSecurity(Model model) {
		StatisticsDTO statistics = new StatisticsDTO(null, "Kolja", new Date(), null);
		statisticsService.addStatistics(statistics);
		model.addAttribute("ds", statistics);
		return "statistics";
	}

}
