package com.project.project_backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.project.project_backend.model.Historytb;

public interface HistorytbRepo extends JpaRepository<Historytb, Integer> {

	@Query(value="select * from historytb where username=?", nativeQuery=true)
	List<Historytb> findHistoryByUsername(String username);
}
