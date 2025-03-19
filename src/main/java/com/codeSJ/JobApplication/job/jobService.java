package com.codeSJ.JobApplication.job;

import java.util.List;

public interface jobService {
	 List<Job> findAll();
	    void createJob(Job job);
		Job getJobById(long id);
}
