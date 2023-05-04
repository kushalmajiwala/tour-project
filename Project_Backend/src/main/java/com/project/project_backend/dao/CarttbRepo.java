package com.project.project_backend.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.project.project_backend.model.Carttb;

public interface CarttbRepo extends JpaRepository<Carttb, Integer> {

	@Query(value="select * from carttb where username=?", nativeQuery=true)
	List<Carttb> findCartByUsername(String username);
}
