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
import com.project.project_backend.dao.CarttbRepo;
import com.project.project_backend.model.Carttb;

@RestController()
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CarttbRepo cartrepo;
	
	@RequestMapping("")
    public String myname()
    {
        return "This is the Cart Controller";
    }
	@PostMapping("/addcart")
    public Carttb addData(@RequestBody Carttb c)
    {
        cartrepo.save(c);
    	return c;
    }
	@GetMapping("/getcart/{uname}")
    public List<Carttb> getCart(@PathVariable("uname") String uname)
    {
    	List<Carttb> c = cartrepo.findCartByUsername(uname);
    	return c;
    }
	@DeleteMapping("/deletecart/{cartid}")
	public Optional<Carttb> deleteCart(@PathVariable("cartid") int cartid)
	{
		Optional<Carttb> c = cartrepo.findById(cartid);
		cartrepo.deleteById(cartid);
		return c;
	}
	@PutMapping("/updatestatus")
 	public Carttb updatePerson(@RequestBody Carttb c) {
        cartrepo.save(c);
        return c;
    }
}
