package edu.softserveinc.healthbody.webclient.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.softserveinc.healthbody.webclient.healthbody.webservice.HealthBodyService;
import edu.softserveinc.healthbody.webclient.healthbody.webservice.HealthBodyServiceImplService;
import lombok.extern.slf4j.Slf4j;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import net.aksingh.owmjapis.OpenWeatherMap.Units;

@Controller
@Slf4j
public class MainPageController {

	private final Integer COMPETITIONS_PER_PAGE = 10;

	@RequestMapping(value = "/main.html", method = RequestMethod.GET)
	public String getListCurrentCompetitions(Model model, @Autowired HealthBodyServiceImplService healthBody,
			@RequestParam(value = "partNumber", required = false) Integer partNumber, HttpServletRequest request) {
		try {
		if (partNumber == null) {
			partNumber = 1;
		}
		int currentPage = partNumber;
		int startPartNumber = 1;
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
		int n = service.getAllActiveCompetitions(1, Integer.MAX_VALUE).size();
		int lastPartNumber = (int) Math.ceil(n * 1.0 / COMPETITIONS_PER_PAGE);
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("login", login);
		model.addAttribute("startPartNumber", startPartNumber);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPartNumber", lastPartNumber);
		model.addAttribute("getAllComp", service.getAllActiveCompetitions(partNumber, COMPETITIONS_PER_PAGE));

		OpenWeatherMap weatherService = new OpenWeatherMap(Units.METRIC, "b117631346fcc98856c5dbfddf9a7245");
		CurrentWeather weather = null;

		JsonObject json = null;
		JsonParser parser = new JsonParser();
		String defaultCity = "Lviv";
			log.info("Get current weather for " + defaultCity + " in JSON format");
			weather = weatherService.currentWeatherByCityName(defaultCity);
			log.info("Parsing JSON");
			json = parser.parse(weather.getRawResponse()).getAsJsonObject();
			log.info(json.get("cod").getAsString());
			if (json.get("cod").getAsString().equals("200")) {
				weather = weatherService.currentWeatherByCityName(defaultCity);
				log.info("Parsing JSON");
				json = parser.parse(weather.getRawResponse()).getAsJsonObject();
				JsonObject main = json.getAsJsonObject("main");
				JsonArray weatherArray = json.getAsJsonArray("weather");
				JsonObject weatherElement = weatherArray.get(0).getAsJsonObject();
				JsonObject wind = json.getAsJsonObject("wind");
				model.addAttribute("city_name", json.get("name").getAsString());
				model.addAttribute("temp", main.get("temp").getAsString());
				model.addAttribute("humidity", main.get("humidity").getAsString());
				model.addAttribute("weather_icon", weatherElement.get("icon").getAsString());
				model.addAttribute("wind", wind.get("speed").getAsString());
				return "main";
			}
		} catch (JSONException e) {
			log.error("JSONException", e);
		} catch (IOException e1) {
			log.error("IOException", e1);
		}
		return "main";
	}
	
	@RequestMapping(value = "/register_in_comp",method = RequestMethod.GET)
	public String takePartInCompettion(Model model, @Autowired HealthBodyServiceImplService healthBody,
			String nameCompetition){	
		String uLogin = SecurityContextHolder.getContext().getAuthentication().getName();
		
		HealthBodyService service = healthBody.getHealthBodyServiceImplPort();		
		service.addUserInCompetition(nameCompetition, uLogin);		
		model.addAttribute("user", service.getUserByLogin(uLogin));
		return "redirect:main.html";
	}

}
