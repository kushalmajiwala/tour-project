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
import com.project.project_backend.dao.Tour_placetbRepo;
import com.project.project_backend.model.Tour_placetb;

@RestController()
@RequestMapping("/tourplace")
public class TourplaceController {

	@Autowired
	Tour_placetbRepo tourplacerepo;
	
	@RequestMapping("")
    public String myname()
    {
        return "This is the Tour Place Controller";
    }
	@GetMapping("/getallplace")
    public List<Tour_placetb> getAllPlace()
    {
    	return tourplacerepo.findAll();
    }
    @PostMapping("/addplace")
    public Tour_placetb addPlace(@RequestBody Tour_placetb t)
    {
    	tourplacerepo.save(t);
    	return t;
    }
    @GetMapping("/getplace/{tourid}")
    public List<Tour_placetb> getPlace(@PathVariable("tourid") int tourid)
    {
    	return tourplacerepo.findPlaceByTourId(tourid);
    }
    @PutMapping("/updateplace")
    public Tour_placetb updateTour(@RequestBody Tour_placetb t) {
        tourplacerepo.save(t);
        return t;
    }
    @DeleteMapping("/deleteplace/{placeid}")
    public Optional<Tour_placetb> deletePlace(@PathVariable("placeid") int placeid)
    {
    	Optional<Tour_placetb> t = tourplacerepo.findById(placeid);
    	tourplacerepo.deleteById(placeid);
    	return t;
    }
}
