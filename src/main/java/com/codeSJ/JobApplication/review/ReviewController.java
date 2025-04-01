package com.codeSJ.JobApplication.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     
	//Path For fetching all the reviews for a perticular company based on the company id.
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
	 
	 // Path For adding reviews for a perticular company based on the company Id.
	 @PostMapping
	 public ResponseEntity<String> addReviews(@PathVariable long companyId ,
			                                  @RequestBody Review review){
		 boolean isReviewSaved = reviewService.addReview(companyId, review);
		 if(isReviewSaved == true) {
		    return new ResponseEntity<String>("Review Added Successfully to the company :-"+companyId, HttpStatus.OK);
		 }
		 else {
			 return new ResponseEntity<String>("Review Not Saved",HttpStatus.NOT_FOUND);
		 }
	 }
	 
	 @GetMapping("/{reviewId}")
	 public ResponseEntity<Review> getReviewById(@PathVariable long companyId, @PathVariable long reviewId){
		 Review reviewById = reviewService.getReviewById(companyId,reviewId);
		 return new ResponseEntity<Review>(reviewById,HttpStatus.OK);
	 }
	 
//	 ??Updating reviwe
	 
	@PutMapping("/{reviewId}")
	public ResponseEntity<String> updateReview(@PathVariable long companyId, 
			                                   @PathVariable long reviewId,
			                                   @RequestBody Review review){
		boolean updateReview = reviewService.updateReview(companyId,reviewId,review);
		if(updateReview==true) {
			return new ResponseEntity<String>("Review Updated Successfully", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Review Updation Denied", HttpStatus.NOT_MODIFIED);
		}
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
