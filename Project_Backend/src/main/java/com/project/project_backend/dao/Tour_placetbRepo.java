package com.project.project_backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.project_backend.model.Tour_placetb;

public interface Tour_placetbRepo extends JpaRepository<Tour_placetb, Integer> {

	@Query(value="select * from tour_placetb where tourmasterid=?", nativeQuery=true)
	List<Tour_placetb> findPlaceByTourId(int tourid);
}
