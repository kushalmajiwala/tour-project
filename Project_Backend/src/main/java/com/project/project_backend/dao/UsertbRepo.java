package com.project.project_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.project_backend.model.Usertb;

public interface UsertbRepo extends JpaRepository<Usertb, String> {
	
}
