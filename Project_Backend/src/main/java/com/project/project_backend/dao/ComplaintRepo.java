package com.project.project_backend.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.project.project_backend.model.Complainttb;

public interface ComplaintRepo extends JpaRepository<Complainttb, Integer>{

	@Query(value="select * from complainttb where username=?", nativeQuery=true)
	List<Complainttb> findComplaintByUsername(String username);
}
