package com.allAboutReviews.requests;

import com.sun.xml.internal.bind.v2.TODO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private Long roleId;
    private Long accountStatusId;
//    private Set<Review> reviews;

//TODO contructors

    public AccountRequest(String email, String password, String firstName, String lastName, Date dateOfBirth, String gender) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public AccountRequest(String email, String password, String firstName, String lastName, Date dateOfBirth, String gender, Long roleId) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.roleId = roleId;
    }
}
