
package com.project.project_backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.project_backend.dao.UsertbRepo;
import com.project.project_backend.model.Usertb;

@RestController()
@RequestMapping("/user")
public class UserController {
    
	@Autowired
	UsertbRepo userrepo;
	
    @RequestMapping("")
    public String myname()
    {
        return "This is the User Controller";
    }
    @GetMapping("/getalldata")
    public List<Usertb> getAllData()
    {
    	return userrepo.findAll();
    }
    @PostMapping("/adddata")
    public Usertb addData(@RequestBody Usertb u)
    {
    	userrepo.save(u);
    	return u;
    }
    @GetMapping("/getdata/{uname}")
    public Optional<Usertb> getData(@PathVariable("uname") String uname)
    {
    	Optional<Usertb> u = userrepo.findById(uname);
    	return u;
    }
    @PutMapping("/updatedata")
    public Usertb updateUser(@RequestBody Usertb u) {
        userrepo.save(u);
        return u;
    }
}
