package com.project.project_backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.project.project_backend.model.Tourtb;

public interface TourtbRepo extends JpaRepository<Tourtb, Integer>{

	@Query(value="select * from tourtb where username=?", nativeQuery=true)
	List<Tourtb> findTourByUsername(String username);
}
