package com.zamuraev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated
    private ReviewRating rating;

    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Review(ReviewRating reting, String description) {
        this.rating = reting;
        this.description = description;
    }
}
