package com.project.project_backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.project.project_backend.model.Projectgroups;

public interface ProjectgroupsRepo extends JpaRepository<Projectgroups, String> {

	@Query(value="select * from projectgroups where groupname=?", nativeQuery=true)
	List<Projectgroups> findGroupByGroupname(String gname);
}
