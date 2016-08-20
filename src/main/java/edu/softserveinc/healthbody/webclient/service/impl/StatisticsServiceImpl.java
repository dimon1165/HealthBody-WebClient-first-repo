package edu.softserveinc.healthbody.webclient.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.softserveinc.healthbody.webclient.persistence.dto.StatisticsDTO;
import edu.softserveinc.healthbody.webclient.persistence.dto.StatisticsMapper;
import edu.softserveinc.healthbody.webclient.persistence.entity.Statistics;
import edu.softserveinc.healthbody.webclient.persistence.repository.StatisticsRepository;
import edu.softserveinc.healthbody.webclient.service.StatisticsService;

@Service
public class StatisticsServiceImpl implements StatisticsService {
	
	@Autowired
	private StatisticsRepository statisticsRepository;

	@Override
	@Transactional
	public void addStatistics(StatisticsDTO statisticsDTO) {
		Statistics statistics = new Statistics();
		StatisticsMapper.toEntity(statisticsDTO, statistics);
		statisticsRepository.save(statistics);
	}

	@Override
	@Transactional
	public void editStatistics(StatisticsDTO statisticsDTO) {
		Statistics statistics = new Statistics();
		StatisticsMapper.toEntity(statisticsDTO, statistics);
		statisticsRepository.saveAndFlush(statistics);
	}

	@Override
	@Transactional
	public List<StatisticsDTO> getAllStatistics() {
		return statisticsRepository.getAll();
	}

	@Override
	@Transactional
	public List<StatisticsDTO> getStatisticsByUserLogin(String userLogin) {
		return statisticsRepository.findByUserLogin(userLogin);
	}

}
