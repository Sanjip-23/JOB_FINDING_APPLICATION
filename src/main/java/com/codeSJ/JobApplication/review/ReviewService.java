package com.codeSJ.JobApplication.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(long companyId);
}
