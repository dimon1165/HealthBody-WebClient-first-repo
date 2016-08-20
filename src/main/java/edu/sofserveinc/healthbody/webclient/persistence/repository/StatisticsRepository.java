package edu.sofserveinc.healthbody.webclient.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.sofserveinc.healthbody.webclient.persistence.entity.Statistics;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long>{
	
	@Query("select s from statistics s where s.USER_LOGIN = :userLogin")
	List<Statistics> findByUserLogin(@Param("userLogin") String userLogin);

}
