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
import com.project.project_backend.dao.TourtbRepo;
import com.project.project_backend.model.Tourtb;

@RestController()
@RequestMapping("/tour")
public class TourController {

	@Autowired
	TourtbRepo tourrepo;
	
	@RequestMapping("")
    public String myname()
    {
        return "This is the Tour Controller";
    }
    @GetMapping("/getalltour")
    public List<Tourtb> getAllTour()
    {
    	return tourrepo.findAll();
    }
    @PostMapping("/addtour")
    public Tourtb addTour(@RequestBody Tourtb t)
    {
    	tourrepo.save(t);
    	return t;
    }
    @PutMapping("/updatetour")
    public Tourtb updateUser(@RequestBody Tourtb t) {
        tourrepo.save(t);
        return t;
    }
    @GetMapping("/gettour/{uname}")
    public List<Tourtb> getTour(@PathVariable("uname") String uname)
    {
    	List<Tourtb> u = tourrepo.findTourByUsername(uname);
    	return u;
    }
    @DeleteMapping("/deletetour/{tourid}")
    public Optional<Tourtb> deleteTour(@PathVariable("tourid") int tourid)
    {
    	Optional<Tourtb> t = tourrepo.findById(tourid);
    	tourrepo.deleteById(tourid);
    	return t;
    }
}
