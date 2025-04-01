package com.codeSJ.JobApplication.review.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codeSJ.JobApplication.company.Company;
import com.codeSJ.JobApplication.company.CompanyService;
import com.codeSJ.JobApplication.review.Review;
import com.codeSJ.JobApplication.review.ReviewRepository;
import com.codeSJ.JobApplication.review.ReviewService;
@Service
public class ReviewServiceImpl implements ReviewService {

	private final ReviewRepository reviewRepository;
	private final CompanyService companyService;
	
	
	public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
		super();
		this.reviewRepository = reviewRepository;
		this.companyService = companyService;
	}

	@Override
	public List<Review> getAllReviews(long companyId) {
		List<Review> reviews = reviewRepository.findByCompanyId(companyId);
		return reviews;
	}

	@Override
	public boolean addReview(long companyId, Review review) {
		Company company = companyService.getCompanyById(companyId);
		if(company!= null) {
			review.setCompany(company);
		    reviewRepository.save(review);
		    return true;
		}
		return false;
	}

	@Override
	public Review getReviewById(long companyId, long reviewId) {
		List<Review> reviews = reviewRepository.findByCompanyId(companyId);
		
		return reviews.stream()
				.filter(review -> review.getId()==reviewId)
				.findFirst().orElse(null)
				;
	}

	@Override
	public boolean updateReview(long companyId, long reviewId, Review updatedReview) {
		if(companyService.getCompanyById(companyId) != null) {
			 updatedReview.setCompany(companyService.getCompanyById(companyId));
			 updatedReview.setId(reviewId);
			 reviewRepository.save(updatedReview);
			 return true;
		}
		return false;
	}

	@Override
	public boolean deleteReview(long companyId, long reviewId) {
		if(companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)) {
//			reviewRepository.deleteById(reviewId);
			Review review = reviewRepository.findById(reviewId).orElse(null);
			Company company = review.getCompany();
			company.getReviews().remove(review);
			companyService.updateCompany(company, companyId);
			reviewRepository.deleteById(reviewId);
			return true;
		}
		return false;
	}

}
