package com.project.project_backend.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.project.project_backend.model.Gallerytb;

public interface GallerytbRepo extends JpaRepository<Gallerytb, Integer> {

	@Query(value="select * from gallerytb where tour_name=?", nativeQuery=true)
	List<Gallerytb> findGalleryByTourname(String tourname);
}
