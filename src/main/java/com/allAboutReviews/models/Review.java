package com.allAboutReviews.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id",nullable = false)
    private Long reviewId;

    @Column(name = "review_description")// nullable for now
    private String reviewDescription;

    @Column(name = "rating",nullable = false)
    private double rating;

    @ManyToOne
    @JoinColumn(name="place_id",nullable=false)
    private Place place;

    @ManyToOne
    @JoinColumn(name="account_id",nullable=false)
    private Account account;

    public Review(String reviewDescription, double rating, Place place, Account account) {
        this.reviewDescription = reviewDescription;
        this.rating = rating;
        this.place = place;
        this.account = account;
    }

//    @Override
//    public int hashCode() {
//
//        return Objects.hashCode(reviewId);
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        Review other = (Review) obj;
//        return Objects.equals(reviewId, other.reviewId);
//    }
}
