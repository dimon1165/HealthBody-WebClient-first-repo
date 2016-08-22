package edu.softserveinc.healthbody.webclient.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.softserveinc.healthbody.webclient.dto.StatisticsDTO;
import edu.softserveinc.healthbody.webclient.entity.Statistics;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Integer>{
	
//	@Query("select s from statistics s where s.userlogin = :userLogin")
//	List<Statistics> findByUserLogin(@Param("userLogin") String userLogin);
//	
//	@Query("select new edu.softserveinc.healthbody.webclient.dto.StatisticsDTO" +
//			"(s.id, s.userlogin, s.logindate, s.logoutdate) from statistics s ")
//	List<StatisticsDTO> getAll();
}
