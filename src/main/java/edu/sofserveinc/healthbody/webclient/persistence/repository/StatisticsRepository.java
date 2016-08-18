package edu.sofserveinc.healthbody.webclient.persistence.repository;

import edu.sofserveinc.healthbody.webclient.persistence.entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics, Long>{
	
//	@Repository
//	@Transactional
	

}
