package com.allAboutReviews.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "status")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id", nullable = false)
    private Long statusId;

    @Column(name = "status", nullable = false)
    private String statusType;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private Set<Place> placesWithStatus;

    public Status(String statusType) {
        this.statusType = statusType;
    }

    public Status(Long statusId, String statusType) {
        this.statusId = statusId;
        this.statusType = statusType;
    }
}
