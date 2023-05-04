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

import com.project.project_backend.dao.Tour_mastertbRepo;
import com.project.project_backend.model.Tour_mastertb;

@RestController()
@RequestMapping("/tourmaster")
public class TourmasterController {
	
	@Autowired
	Tour_mastertbRepo tourmasterrepo;
	
	@RequestMapping("")
    public String myname()
    {
        return "This is the Tour Master Controller";
    }
	@GetMapping("/getalltour")
    public List<Tour_mastertb> getAllTour()
    {
    	return tourmasterrepo.findAll();
    }
    @PostMapping("/addtour")
    public Tour_mastertb addTour(@RequestBody Tour_mastertb t)
    {
    	tourmasterrepo.save(t);
    	return t;
    }
    @GetMapping("/gettour/{tourid}")
    public Optional<Tour_mastertb> getTour(@PathVariable("tourid") int tourid)
    {
    	Optional<Tour_mastertb> u = tourmasterrepo.findById(tourid);
    	return u;
    }
    @PutMapping("/updatetour")
    public Tour_mastertb updateTour(@RequestBody Tour_mastertb t) {
        tourmasterrepo.save(t);
        return t;
    }
    @DeleteMapping("/deletetour/{tourid}")
    public Optional<Tour_mastertb> deleteTour(@PathVariable("tourid") int tourid)
    {
    	Optional<Tour_mastertb> t = tourmasterrepo.findById(tourid);
    	tourmasterrepo.deleteById(tourid);
    	return t;
    }
}
