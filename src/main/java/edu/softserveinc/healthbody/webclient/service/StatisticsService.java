package edu.softserveinc.healthbody.webclient.service;

import java.util.List;

import edu.softserveinc.healthbody.webclient.dto.StatisticsDTO;

public interface StatisticsService {
	
	void addStatistics(StatisticsDTO statisticsDTO);
	void editStatistics(StatisticsDTO statisticsDTO);
//	List<StatisticsDTO> getAllStatistics();
//	List<StatisticsDTO> getStatisticsByUserLogin(String userLogin);

}
