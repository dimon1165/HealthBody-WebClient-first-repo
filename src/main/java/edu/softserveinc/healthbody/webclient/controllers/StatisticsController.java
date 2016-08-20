package edu.softserveinc.healthbody.webclient.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.sofserveinc.healthbody.webclient.persistence.entity.Statistics;
import edu.sofserveinc.healthbody.webclient.service.impl.StatisticsServiceImpl;

@Controller
public class StatisticsController {
	
	@RequestMapping(value= "/statistics.html")
	public String loginSpringSecurity(@Autowired StatisticsServiceImpl statisticsService, Model model) {
		Statistics statistics = new Statistics("Kolja", null, null);
		statisticsService.addStatistics(statistics);
//		Date d = new Date();
		model.addAttribute("ds", statistics);
		return "statistics";
	}

}
