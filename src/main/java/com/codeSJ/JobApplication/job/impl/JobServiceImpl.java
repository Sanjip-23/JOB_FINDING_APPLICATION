package com.codeSJ.JobApplication.job.impl;

import java.util.ArrayList;
import java.util.Iterator;
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

	@Override
	public boolean deleteJobById(long id) {
		Iterator<Job> iterator = jobs.iterator();
		while(iterator.hasNext()) {
			Job job = iterator.next();
			if(job.getId()==id) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateJob(long id, Job updatedJob) {
		for(Job job: jobs) {
			if(job.getId()==id) {
				job.setTitle(updatedJob.getTitle());
				job.setDescription(updatedJob.getDescription());
				job.setMinSalary(updatedJob.getMinSalary());
				job.setMaxSalary(updatedJob.getMaxSalary());
				job.setLocation(updatedJob.getLocation());
				
				return true;
			}
		}
		return false;
	}
    
}
