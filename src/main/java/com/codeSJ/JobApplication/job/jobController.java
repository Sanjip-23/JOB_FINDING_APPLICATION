package com.codeSJ.JobApplication.job;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class jobController {
	
	//Creating object of jobService interface 
	private jobService jobService;
	
	public jobController(jobService jobService) {
		super();
		this.jobService = jobService;
	}


	@GetMapping("/jobs")
    public List<Job> findAll(){
    	return jobService.findAll();
    }
	
	
	@PostMapping("/jobs")
	public String createJob(@RequestBody Job job) {
		jobService.createJob(job);
		return "JOB ADDED SUCCESSFULLY";
	}
	
	@GetMapping("/jobs/{id}")
	public Job getJobById(@PathVariable long id) {
		
	   Job job = jobService.getJobById(id);
	   if(job != null)
		   return job;
	   return new Job(0, null, null, null, null, null);   
	}
}
