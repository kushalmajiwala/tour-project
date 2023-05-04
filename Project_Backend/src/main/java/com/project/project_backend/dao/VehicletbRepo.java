package com.project.project_backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.project.project_backend.model.Vehicletb;

public interface VehicletbRepo extends JpaRepository<Vehicletb, Integer> {

	@Query(value="select * from vehicletb where tourmasterid=?", nativeQuery=true)
	List<Vehicletb> findVehicleByTourId(int tourid);
}
