package com.codeSJ.JobApplication.job.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.codeSJ.JobApplication.job.Job;
import com.codeSJ.JobApplication.job.jobService;


@Service
public class JobServiceImpl implements jobService{

	private List<Job> jobs = new ArrayList<Job>();
	private long nextId = 1L;
	@Override
	public List<Job> findAll() {
		return jobs;
	}

	@Override
	public void createJob(Job job) {
		
		job.setId(nextId++);
		jobs.add(job);
	}

	@Override
	public Job getJobById(long id) {
		for (Job job : jobs) {
			if(job.getId()==id) {
				return job;
			}
		}
		return null;
	}
    
}
