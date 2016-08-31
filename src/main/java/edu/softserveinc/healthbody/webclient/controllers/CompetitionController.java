package edu.softserveinc.healthbody.webclient.controllers;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.softserveinc.healthbody.webclient.healthbody.webservice.CompetitionDTO;
import edu.softserveinc.healthbody.webclient.healthbody.webservice.HealthBodyService;
import edu.softserveinc.healthbody.webclient.healthbody.webservice.HealthBodyServiceImplService;
import edu.softserveinc.healthbody.webclient.healthbody.webservice.UserCompetitionsDTO;
import edu.softserveinc.healthbody.webclient.utils.CustomDateFormater;
import edu.softserveinc.healthbody.webclient.utils.GoogleFitUtils;
import edu.softserveinc.healthbody.webclient.validator.CompetitionValidator;

@Controller
public class CompetitionController {

	@Autowired
	private CompetitionValidator competitionValidator;

	final Integer COMPETITIONS_PER_PAGE = 5;

	@RequestMapping(value = "/listCompetitions.html", method = RequestMethod.GET)
	public String getListCurrentCompetitions(Model model, @Autowired HealthBodyServiceImplService healthBody,
			@RequestParam(value = "partNumber", required = false) Integer partNumber, HttpServletRequest request) {

		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
		int startPartNumber = 1;
		if (partNumber == null || partNumber <= 0)
			partNumber = 1;
		model.addAttribute("user", service.getUserByLogin(userLogin));
		model.addAttribute("startPartNumber", startPartNumber);

		if ("admin".equals(service.getUserByLogin(userLogin).getRoleName())) {
			int n = service.getAllCompetitions(1, Integer.MAX_VALUE).size();
			int lastPartNumber = (int) Math.ceil(n * 1.0 / COMPETITIONS_PER_PAGE);
			if (partNumber > lastPartNumber)
				partNumber = lastPartNumber;
			int currentPage = partNumber;
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("lastPartNumber", lastPartNumber);
			model.addAttribute("getCompetitions", service.getAllCompetitions(partNumber, COMPETITIONS_PER_PAGE));
			return "listCompetitionsAdmin";
		} else {
			int n = service.getAllActiveCompetitions(1, Integer.MAX_VALUE).size();
			int lastPartNumber = (int) Math.ceil(n * 1.0 / COMPETITIONS_PER_PAGE);
			if (partNumber > lastPartNumber)
				partNumber = lastPartNumber;
			int currentPage = partNumber;
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("lastPartNumber", lastPartNumber);
			model.addAttribute("getCompetitions", service.getAllActiveCompetitions(partNumber, COMPETITIONS_PER_PAGE));
			return "listCompetitions";
		}
	}

	@RequestMapping(value = "/competition.html", method = RequestMethod.GET)
	public String getCompetition(Model model, @Autowired HealthBodyServiceImplService healthBody,
			String idCompetition) {
		String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		model.addAttribute("user", service.getUserByLogin(userLogin));
		model.addAttribute("getCompetition", service.getCompetitionViewById(idCompetition));
		model.addAttribute("getScore", service.getUserCompetition(idCompetition, userLogin));
		for (CompetitionDTO competition : service.getAllCompetitionsByUser(1, Integer.MAX_VALUE, userLogin)) {
			if (idCompetition.equals(competition.getIdCompetition())) {
				return "leaveCompetition";
			}
		}
		return "joinCompetition";
	}

	@RequestMapping(value = "/joinCompetition.html", method = RequestMethod.GET)
	public String joinCompetition(Model model, @Autowired HealthBodyServiceImplService healthBody,
			String idCompetition) {
		String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		service.addUserInCompetitionView(idCompetition, userLogin);
		String gettedAccessToken = GoogleFitUtils.postForAccessToken(service.getUserByLogin(userLogin).getGoogleApi());
		Long startTime = CustomDateFormater.getDateInMilliseconds(service.getCompetitionViewById(idCompetition).getStartDate());
		String fitData = GoogleFitUtils.post(gettedAccessToken, startTime, System.currentTimeMillis());
		String stepCount = GoogleFitUtils.getStepCount(fitData);
		UserCompetitionsDTO userCompetition =  service.getUserCompetition(idCompetition, userLogin);
		userCompetition.setUserScore(stepCount);
		service.updateUserCompetition(userCompetition);
		
		model.addAttribute("user", service.getUserByLogin(userLogin));
		model.addAttribute("usercompetitions", service.getAllCompetitionsByUser(1, Integer.MAX_VALUE, userLogin));
		return "userCabinet";
	}

	@RequestMapping(value = "/leaveCompetition.html", method = RequestMethod.GET)
	public String leaveCompetition(Model model, @Autowired HealthBodyServiceImplService healthBody,
			String idCompetition) {
		String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		service.deleteUserCompetition(idCompetition, userLogin);
		model.addAttribute("user", service.getUserByLogin(userLogin));
		model.addAttribute("usercompetitions", service.getAllCompetitionsByUser(1, Integer.MAX_VALUE, userLogin));
		model.addAttribute("getScore", service.getUserCompetition(idCompetition, userLogin));
		return "userCabinet";
	}

	@RequestMapping(value = "/createCompetition.html", method = RequestMethod.GET)
	public String createCompetitionDescription(Model model, @Autowired HealthBodyServiceImplService healthBody) {
		String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		CompetitionDTO competitionDTO = new CompetitionDTO();
		competitionDTO.setIdCompetition(null);
		competitionDTO.setName(null);
		competitionDTO.setCount(null);
		competitionDTO.setStartDate(null);
		competitionDTO.setFinishDate(null);
		competitionDTO.setDescription(null);
		model.addAttribute("user", service.getUserByLogin(userLogin));
		model.addAttribute("competitionToCreate", competitionDTO);
		return "createCompetition";
	}

	@RequestMapping(value = "/createCompetition.html", method = RequestMethod.POST)
	public String createCompetition(@ModelAttribute("competitionToCreate") CompetitionDTO competitionToCreate,
			Map<String, Object> model, @Autowired HealthBodyServiceImplService healthBody, BindingResult result) {
		competitionValidator.validate(competitionToCreate, result);
		if (result.hasErrors()) {
			return "createCompetition";
		}
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		CompetitionDTO competitionDTO = competitionToCreate;
		competitionDTO.setIdCompetition(UUID.randomUUID().toString());
		competitionDTO.setName(competitionToCreate.getName());
		competitionDTO.setDescription(competitionToCreate.getDescription());
		competitionDTO.setStartDate(competitionToCreate.getStartDate());
		competitionDTO.setFinishDate(competitionToCreate.getFinishDate());
		service.createCompetition(competitionDTO);
		return "redirect:/listCompetitions.html";
	}

	@RequestMapping(value = "/editCompetition.html", method = RequestMethod.GET)
	public String editCompetitionDescription(Model model, @Autowired HealthBodyServiceImplService healthBody,
			String idCompetition) {
		String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		CompetitionDTO competitionDTO = service.getCompetitionViewById(idCompetition);
		model.addAttribute("user", service.getUserByLogin(userLogin));
		model.addAttribute("competitionToEdit", competitionDTO);
		return "editCompetition";
	}

	@RequestMapping(value = "/editCompetition.html", method = RequestMethod.POST)
	public String editCompetition(@ModelAttribute("competitionToEdit") CompetitionDTO competitionToEdit,
			Map<String, Object> model, @Autowired HealthBodyServiceImplService healthBody, BindingResult result) {
		competitionValidator.validate(competitionToEdit, result);
		if (result.hasErrors()) {
			return "editCompetition";
		}
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		CompetitionDTO competitionDTO = service.getCompetitionViewById(competitionToEdit.getIdCompetition());
		competitionDTO.setName(competitionToEdit.getName());
		competitionDTO.setDescription(competitionToEdit.getDescription());
		competitionDTO.setStartDate(competitionToEdit.getStartDate());
		competitionDTO.setFinishDate(competitionToEdit.getFinishDate());
		service.updateCompetition(competitionDTO);
		return "redirect:/listCompetitions.html";
	}

}