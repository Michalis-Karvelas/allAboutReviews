package com.allAboutReviews.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "places")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "place_id", nullable = false)
    private Long placeId;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "address",nullable = false)
    private String address;

    @Column(name = "description")
    private String description;

    @Column(name = "overall_rating") //,nullable = false
    private Double overallRating;// mporei kai na mhn xreiazetai

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "status_id",nullable=false)
    private Status status;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name="place_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "place")
    private Set<Review> placeReviews;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "place")
    private Set<WorkingSchedule> workingScheduleSet;

    public Place(String name, String address, String description,Status status) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.status=status;
    }

    public Place(String name, String address, String description, Status status, Set<Category> categories) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.status = status;
        this.categories = categories;
    }

    public Place(String name, String address, String description, double overallRating, Status status, Set<Category> categories) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.overallRating = overallRating;
        this.status = status;
        this.categories = categories;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(placeId);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Place other = (Place) obj;
        return Objects.equals(placeId, other.placeId);
    }

}
