package com.project.project_backend.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.project_backend.dao.ComplaintRepo;
import com.project.project_backend.model.Complainttb;

@RestController()
@RequestMapping("/complaint")
public class ComplaintController {

	@Autowired
	ComplaintRepo complaintrepo;
	
	@RequestMapping("")
    public String myname()
    {
        return "This is the Complaint Controller";
    }
	@GetMapping("/getallcomplaint")
    public List<Complainttb> getAllComplaint()
    {
    	return complaintrepo.findAll();
    }
    @PostMapping("/addcomplaint")
    public Complainttb addComplaint(@RequestBody Complainttb c)
    {
    	complaintrepo.save(c);
    	return c;
    }
    @GetMapping("/getcomplaint/{uname}")
    public List<Complainttb> getComplaint(@PathVariable("uname") String uname)
    {
    	List<Complainttb> c = complaintrepo.findComplaintByUsername(uname);
    	return c;
    }
    @DeleteMapping("/deletecomplaint/{complaintid}")
    public Optional<Complainttb> deleteComplaint(@PathVariable("complaintid") int complaintid)
    {
    	Optional<Complainttb> c = complaintrepo.findById(complaintid);
    	complaintrepo.deleteById(complaintid);
    	return c;
    }
}
