package com.project.project_backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("/getgroup/{gname}")
	public List<Projectgroups> getGroup(@PathVariable("gname") String gname)
	{
		List<Projectgroups> g = grouprepo.findGroupByGroupname(gname);
		return g;
	}
	 
	@PutMapping("/updatedata")
	public Projectgroups updateUser(@RequestBody Projectgroups pg) {
       grouprepo.save(pg);
       return pg;
    }
}
