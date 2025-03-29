package com.codeSJ.JobApplication.review.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codeSJ.JobApplication.review.Review;
import com.codeSJ.JobApplication.review.ReviewRepository;
import com.codeSJ.JobApplication.review.ReviewService;
@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepository reviewRepository;
	
	public ReviewServiceImpl(ReviewRepository reviewRepository) {
		super();
		this.reviewRepository = reviewRepository;
	}

	@Override
	public List<Review> getAllReviews(long companyId) {
		List<Review> reviews = reviewRepository.findByCompanyId(companyId);
		return reviews;
	}

}
