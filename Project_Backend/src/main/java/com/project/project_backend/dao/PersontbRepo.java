package com.project.project_backend.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.project.project_backend.model.Persontb;

public interface PersontbRepo extends JpaRepository<Persontb, Integer> {

	@Query(value="select * from persontb where tourid=?", nativeQuery=true)
	List<Persontb> findPersonByTourId(int tourid);
}
