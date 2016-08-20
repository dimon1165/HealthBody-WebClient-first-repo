package edu.softserveinc.healthbody.webclient.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.softserveinc.healthbody.webclient.persistence.dto.StatisticsDTO;
import edu.softserveinc.healthbody.webclient.service.impl.StatisticsServiceImpl;

@Controller
public class StatisticsController {
	
	@RequestMapping(value= "/statistics.html")
	public String loginSpringSecurity(@Autowired StatisticsServiceImpl statisticsService, Model model) {
		StatisticsDTO statistics = new StatisticsDTO(null, "Kolja", null, null);
		statisticsService.addStatistics(statistics);
//		Date d = new Date();
		model.addAttribute("ds", statistics);
		return "statistics";
	}

}
