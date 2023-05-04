package com.project.project_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.project_backend.dao.FeedbacktbRepo;
import com.project.project_backend.model.Feedbacktb;

@RestController()
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	FeedbacktbRepo feedbackrepo;
	
	@RequestMapping("")
    public String myname()
    {
        return "This is the Feedback Controller";
    }
	@GetMapping("/getallfeedback")
    public List<Feedbacktb> getAllFeedback()
    {
    	return feedbackrepo.findAll();
    }
    @PostMapping("/addfeedback")
    public Feedbacktb addData(@RequestBody Feedbacktb f)
    {
    	feedbackrepo.save(f);
    	return f;
    }
}
