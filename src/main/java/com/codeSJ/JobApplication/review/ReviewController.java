package com.codeSJ.JobApplication.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {
     private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}
     
	 @GetMapping
	 public ResponseEntity<List<Review>> getAllReviews(@PathVariable long companyId){
		 List<Review> review = reviewService.getAllReviews(companyId);
		 if(review != null) {
			 return new ResponseEntity<List<Review>>(review, HttpStatus.OK);
		 }
		 else {
			return new ResponseEntity<List<Review>>(HttpStatus.NOT_FOUND);
		}
	 }
}
