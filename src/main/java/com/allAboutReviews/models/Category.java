package com.allAboutReviews.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "categories_types")
    private String typeOfCategory;

    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Place> placeSet;

    public Category(String typeOfCategory) {
        this.typeOfCategory = typeOfCategory;
    }

    public Category(Long categoryId, String typeOfCategory) {
        this.categoryId = categoryId;
        this.typeOfCategory = typeOfCategory;
    }
}
