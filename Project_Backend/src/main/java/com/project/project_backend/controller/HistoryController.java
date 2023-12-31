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
import com.project.project_backend.dao.HistorytbRepo;
import com.project.project_backend.model.Historytb;

@RestController()
@RequestMapping("/history")
public class HistoryController {

	@Autowired
	HistorytbRepo historyrepo;
	
	@RequestMapping("")
    public String myname()
    {
        return "This is the History Controller";
    }
	@PostMapping("/addhistory")
    public Historytb addHistory(@RequestBody Historytb h)
    {
    	historyrepo.save(h);
    	return h;
    }
	@GetMapping("/gethistory/{uname}")
    public List<Historytb> getHistory(@PathVariable("uname") String uname)
    {
    	List<Historytb> h = historyrepo.findHistoryByUsername(uname);
    	return h;
    }
	@GetMapping("/gethistorybyid/{tid}")
    public Historytb getHistoryByTourid(@PathVariable("tid") int tid)
    {
    	Historytb h = historyrepo.findHistoryByTourid(tid);
    	return h;
    }
	@DeleteMapping("/deletehistory/{historyid}")
	public Optional<Historytb> deleteHistory(@PathVariable("historyid") int historyid)
	{
		Optional<Historytb> h = historyrepo.findById(historyid);
		historyrepo.deleteById(historyid);
		return h;
	}
	@DeleteMapping("/deletehistorybyusername/{username}")
	public void deleteHistoryByUsername(@PathVariable("username") String username)
	{
		historyrepo.deleteHistoryByUsername(username);
	}
}
