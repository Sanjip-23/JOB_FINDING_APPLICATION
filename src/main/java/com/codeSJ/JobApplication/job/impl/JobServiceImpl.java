package com.codeSJ.JobApplication.job.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codeSJ.JobApplication.job.Job;
import com.codeSJ.JobApplication.job.JobRepository;
import com.codeSJ.JobApplication.job.jobService;


@Service
public class JobServiceImpl implements jobService{

//	private List<Job> jobs = new ArrayList<Job>();
	
	JobRepository jobRepository;
	
	
	public JobServiceImpl(JobRepository jobRepository) {
	super();
	this.jobRepository = jobRepository;
	}

//	private long nextId = 1L;
	@Override
	public List<Job> findAll() {
		return jobRepository.findAll();
	}

	@Override
	public void createJob(Job job) {
		
//		job.setId(nextId++);
		jobRepository.save(job);
	}

	@Override
	public Job getJobById(long id) {
//		for (Job job : jobs) {
//			if(job.getId()==id) {
//				return job;
//			}
//		}
//		return null;
		
		return jobRepository.findById(id).orElse(null);
	}

	@Override
	public boolean deleteJobById(long id) {
//		try {
//			jobRepository.deleteById(id);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}
		if(jobRepository.existsById(id)) {
			jobRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateJob(long id, Job updatedJob) {
		Optional<Job> jobOptional = jobRepository.findById(id);
		
			if(jobOptional.isPresent()) {
				Job job = jobOptional.get();
				job.setTitle(updatedJob.getTitle());
				job.setDescription(updatedJob.getDescription());
				job.setMinSalary(updatedJob.getMinSalary());
				job.setMaxSalary(updatedJob.getMaxSalary());
				job.setLocation(updatedJob.getLocation());
				jobRepository.save(job);
				
				return true;
			}
		return false;
	}
    
}
