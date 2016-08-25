package edu.softserveinc.healthbody.webclient.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.softserveinc.healthbody.webclient.entity.Statistics;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Integer>{
	
	@Query("select s from Statistics s where s.userLogin = ?1")
	List<Statistics> findByUserLogin(String userLogin);
	
	@Modifying
	@Query("update Statistics s set s.logoutDate = ?1 where s.userLogin = ?2 and s.logoutDate is null")
	void updateStatistics(Date logoutDate, String userLogin);
	
	@Query("select s from Statistics s ")
	List<Statistics> getAll();
}
