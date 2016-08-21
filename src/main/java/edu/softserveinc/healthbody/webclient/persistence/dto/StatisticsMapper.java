package edu.softserveinc.healthbody.webclient.persistence.dto;

import edu.softserveinc.healthbody.webclient.persistence.entity.Statistics;

public class StatisticsMapper {
	
	public static Statistics toEntity(StatisticsDTO statisticsDTO, Statistics statistics) {
		statistics.setUserLogin(statisticsDTO.getUserLogin());
		statistics.setLoginDate(statisticsDTO.getLoginDate());
		statistics.setLogoutDate(statisticsDTO.getLogoutDate());
		return statistics;
	}

}
