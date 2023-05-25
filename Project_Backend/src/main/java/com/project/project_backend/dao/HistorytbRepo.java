package com.project.project_backend.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.project.project_backend.model.Historytb;

public interface HistorytbRepo extends JpaRepository<Historytb, Integer> {

	@Query(value="select * from historytb where username=?", nativeQuery=true)
	List<Historytb> findHistoryByUsername(String username);
	
	@Query(value="select * from historytb where tourid=?", nativeQuery=true)
	Historytb findHistoryByTourid(int tourid);
	
	@Modifying
	@Transactional
	@Query(value="delete from historytb where username=?", nativeQuery=true)
	void deleteHistoryByUsername(String username);
}
