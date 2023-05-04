package com.project.project_backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.project_backend.dao.GallerytbRepo;
import com.project.project_backend.model.Gallerytb;

@RestController()
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	GallerytbRepo galleryrepo;
	
	@RequestMapping("")
    public String myname()
    {
        return "This is the Gallery Controller";
    }
	@GetMapping("/getgallery/{tourname}")
    public List<Gallerytb> getGallery(@PathVariable("tourname") String tourname)
    {
    	List<Gallerytb> g = galleryrepo.findGalleryByTourname(tourname);
    	return g;
    }
}
