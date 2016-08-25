package edu.softserveinc.healthbody.webclient.service;

import java.util.Date;
import java.util.List;

import edu.softserveinc.healthbody.webclient.dto.StatisticsDTO;

public interface StatisticsService {
	
	void addStatistics(StatisticsDTO statisticsDTO);
	
	List<StatisticsDTO> getAllStatistics();
	
	List<StatisticsDTO> getStatisticsByUserLogin(String userLogin);
	
	void updateStatistics(Date logoutDate, String userLogin);

}
