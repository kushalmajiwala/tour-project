package com.project.project_backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.project_backend.dao.PersontbRepo;
import com.project.project_backend.model.Persontb;

@RestController()
@RequestMapping("/person")
public class PersonController {

	@Autowired
	PersontbRepo personrepo;
	
	@RequestMapping("")
    public String myname()
    {
        return "This is the Person Controller";
    }
    @PostMapping("/addperson")
    public Persontb addPerson(@RequestBody Persontb p)
    {
    	personrepo.save(p);
    	return p;
    }
    @GetMapping("/getperson/{tourid}")
    public List<Persontb> getPerson(@PathVariable("tourid") int tourid)
    {
    	List<Persontb> p = personrepo.findPersonByTourId(tourid);
    	return p;
    }
    @PutMapping("/updateperson")
    public Persontb updatePerson(@RequestBody Persontb p) {
        personrepo.save(p);
        return p;
    }
    @DeleteMapping("/deleteperson/{personid}")
    public Optional<Persontb> deletePerson(@PathVariable("personid") int personid)
    {
    	Optional<Persontb> p = personrepo.findById(personid);
    	personrepo.deleteById(personid);
    	return p;
    }
}
