package edu.softserveinc.healthbody.webclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.softserveinc.healthbody.webclient.healthbody.webservice.HealthBodyService;
import edu.softserveinc.healthbody.webclient.healthbody.webservice.HealthBodyServiceImplService;

import edu.softserveinc.healthbody.webclient.utils.CustomDateFormater;
import edu.softserveinc.healthbody.webclient.utils.GoogleFitUtils;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserCabinetController {

	@RequestMapping(value = "/userCabinet.html", method = RequestMethod.GET)
	public String getUserList(Model model, @Autowired HealthBodyServiceImplService healthBody) {
		String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
		/* request.getUserPrincipal().getName(); */
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		Long currentTime = System.currentTimeMillis();
		/* Rest **/
		// URLFormatter formatter = new URLFormatter();
		// model.addAttribute("user", formatter.getUserByLogin("UserByLogin",
		// userLogin));
		/* Google Fit */
		log.info(GoogleFitUtils.postForAccessToken(service.getUserByLogin(userLogin).getScore()));
		String gettedAccessToken = GoogleFitUtils.postForAccessToken(service.getUserByLogin(userLogin).getGoogleApi());
		Long startTime = CustomDateFormater.getDateInMilliseconds("2016-08-01");
		String fitData = GoogleFitUtils.post(gettedAccessToken, startTime, currentTime);
		String stepCount = GoogleFitUtils.getStepCount(fitData);
		log.info(stepCount);
		/* SOAP **/
		model.addAttribute("user", service.getUserByLogin(userLogin));
		model.addAttribute("usercompetitions", service.getAllCompetitionsByUser(1, Integer.MAX_VALUE, userLogin));
		return "userCabinet";
	}

}
