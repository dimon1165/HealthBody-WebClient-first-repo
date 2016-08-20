package edu.sofserveinc.healthbody.webclient.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import edu.sofserveinc.healthbody.webclient.persistence.entity.Statistics;
import edu.sofserveinc.healthbody.webclient.persistence.repository.StatisticsRepository;
import edu.sofserveinc.healthbody.webclient.service.StatisticsService;

@Transactional
public class StatisticsServiceImpl implements StatisticsService {
	
	@Autowired
	private StatisticsRepository statisticsRepository;

	@Override
	public void addStatistics(Statistics statistics) {
		statisticsRepository.save(statistics);
		
	}

	@Override
	@Transactional
	public Statistics editStatistics(Statistics statistics) {
		return statisticsRepository.saveAndFlush(statistics);
	}

	@Override
	@Transactional
	public List<Statistics> getAll() {
		return statisticsRepository.findAll();
	}

	@Override
	@Transactional
	public List<Statistics> getStatisticsByUserLogin(String userLogin) {
		return statisticsRepository.findByUserLogin(userLogin);
	}

}
