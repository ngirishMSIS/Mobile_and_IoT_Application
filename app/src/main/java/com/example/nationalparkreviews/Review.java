package com.example.nationalparkreviews;

public class Review {
    private String username;
    private String rating;
    private String review;

    public Review(String username, String rating, String review){
        this.username = username;
        this.rating = rating;
        this.review = review;
    }

    public String getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public String getUsername() {
        return username;
    }
}
