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
import com.project.project_backend.dao.VehicletbRepo;
import com.project.project_backend.model.Vehicletb;

@RestController()
@RequestMapping("/vehicle")
public class VehicleController {

	@Autowired
	VehicletbRepo vehiclerepo;
	
	@RequestMapping("")
    public String myname()
    {
        return "This is the Vehicle Controller";
    }
	@GetMapping("/getallvehicle")
    public List<Vehicletb> getAllData()
    {
    	return vehiclerepo.findAll();
    }
    @PostMapping("/addvehicle")
    public Vehicletb addVehicle(@RequestBody Vehicletb v)
    {
    	vehiclerepo.save(v);
    	return v;
    }
    @GetMapping("/getvehicle/{tourid}")
    public List<Vehicletb> getVehicle(@PathVariable("tourid") int tourid)
    {
    	List<Vehicletb> v = vehiclerepo.findVehicleByTourId(tourid);
    	return v;
    }
    @PutMapping("/updatevehicle")
    public Vehicletb updateVehicle(@RequestBody Vehicletb v) {
        vehiclerepo.save(v);
        return v;
    }
    @DeleteMapping("/deletevehicle/{vehicleid}")
    public Optional<Vehicletb> deleteTour(@PathVariable("vehicleid") int vehicleid)
    {
    	Optional<Vehicletb> v = vehiclerepo.findById(vehicleid);
        vehiclerepo.deleteById(vehicleid);
    	return v;
    }
}
