package edu.softserveinc.healthbody.webclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.softserveinc.healthbody.webclient.api.HealthBodyService;
import edu.softserveinc.healthbody.webclient.api.HealthBodyServiceImplService;

@Controller
public class MainPageController {
	
	@RequestMapping(value = "/main.html",method = RequestMethod.GET)
	public String getListCurrentCompetitions(Model model, @Autowired HealthBodyServiceImplService healthBody){
//			@RequestParam(value = "partNumber") Integer partNumber, @RequestParam(value = "partSize") Integer partSize){
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		model.addAttribute("getAllComp", service.getAllActiveCompetitions(1, 10));
		return "main";
	}
	
	@RequestMapping(value = "/register_in_comp.html",method = RequestMethod.GET)
	public String takePartInCompettion(){		
		return "register_in_comp";
	}

}
