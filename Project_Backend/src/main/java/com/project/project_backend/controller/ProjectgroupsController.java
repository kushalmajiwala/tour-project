package com.project.project_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.project_backend.dao.ProjectgroupsRepo;
import com.project.project_backend.model.Projectgroups;

@RestController()
@RequestMapping("/projectgroups")
public class ProjectgroupsController {

	@Autowired
	ProjectgroupsRepo grouprepo;
	
	 @PostMapping("/addgroup")
	 public Projectgroups addGroup(@RequestBody Projectgroups pg)
	 {
		 grouprepo.save(pg);
		 return pg;
	 }
	 @PutMapping("/updatedata")
	 public Projectgroups updateUser(@RequestBody Projectgroups pg) {
        grouprepo.save(pg);
        return pg;
    }
}
