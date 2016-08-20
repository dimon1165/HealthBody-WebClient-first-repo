package edu.sofserveinc.healthbody.webclient.service;

import java.util.List;

import edu.sofserveinc.healthbody.webclient.persistence.entity.Statistics;

public interface StatisticsService {
	
	void addStatistics(Statistics statistics);
	Statistics editStatistics(Statistics statistics);
	List<Statistics> getAll();
	List<Statistics> getStatisticsByUserLogin(String userLogin);

}
