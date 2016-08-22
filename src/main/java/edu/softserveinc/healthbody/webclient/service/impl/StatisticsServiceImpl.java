package edu.softserveinc.healthbody.webclient.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.softserveinc.healthbody.webclient.dto.StatisticsDTO;
import edu.softserveinc.healthbody.webclient.dto.StatisticsMapper;
import edu.softserveinc.healthbody.webclient.entity.Statistics;
import edu.softserveinc.healthbody.webclient.repository.StatisticsRepository;
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

//	@Override
//	@Transactional
//	public List<StatisticsDTO> getAllStatistics() {
//		return statisticsRepository.getAll();
//	}
//
//	@Override
//	@Transactional
//	public List<StatisticsDTO> getStatisticsByUserLogin(String userLogin) {
//		List<StatisticsDTO> statisticsList= new ArrayList<>();
//		StatisticsDTO statisticsDTO = new StatisticsDTO();
//		for(Statistics statistics : statisticsRepository.findByUserLogin(userLogin)) {
//			statisticsList.add(StatisticsMapper.toDto(statisticsDTO, statistics));
//		}
//		return statisticsList;
//	}
}
