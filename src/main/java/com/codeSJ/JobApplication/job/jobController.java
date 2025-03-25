package com.codeSJ.JobApplication.job;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/jobs")
public class jobController {
	
	//Creating object of jobService interface 
	private jobService jobService;
	public jobController(jobService jobService) {
		super();
		this.jobService = jobService;
	}


//	@RequestMapping(value = "/jobs" , method = RequestMethod.POST)
	@GetMapping
    public ResponseEntity<List<Job>> findAll(){
    	return ResponseEntity.ok(jobService.findAll());
    }
	
//	@RequestMapping(value = "/jobs" , method = RequestMethod.GET)
	@PostMapping
	public ResponseEntity<String> createJob(@RequestBody Job job) {
		jobService.createJob(job);
		return new ResponseEntity<String>("JOB ADDED SUCCESSFULLY",HttpStatus.CREATED);
	}
	
	
//	@RequestMapping(value = "/jobs/{id}" , method = RequestMethod.GET)
	@GetMapping("/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable long id) {
	   Job job = jobService.getJobById(id);
	   if(job != null)
		   return new ResponseEntity<Job>(job, HttpStatus.OK);
	   return new ResponseEntity<Job>(HttpStatus.NOT_FOUND);   
	}
	
//	@RequestMapping(value = "/jobs/{id}" , method = RequestMethod.DELETE)
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable long id){		
		boolean deleted = jobService.deleteJobById(id);
		if(deleted == true) {
			return new ResponseEntity<String>("Deleted Successfully", HttpStatus.ACCEPTED);
		}
	return new ResponseEntity<String>("Id Not Found", HttpStatus.NOT_FOUND);
	}
	
	
//	@RequestMapping(value = "/jobs/{id}" , method = RequestMethod.PUT)
	@PutMapping("/{id}")
	public ResponseEntity<String> updateJob(@PathVariable long id, 
			                                @RequestBody Job updatedJob){
		boolean updated = jobService.updateJob(id,updatedJob);
		if(updated)
			return new ResponseEntity<String>("Job Updated Successfully", HttpStatus.OK);
		return new ResponseEntity<String>("Updation Unsuccessful",HttpStatus.NOT_MODIFIED);
	}
	
	
}
