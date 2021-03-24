package com.allAboutReviews.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "account_statuses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_status_id", nullable = false)
    private Long accountStatusId;

    @Column(name = "account_status_type",nullable = false)
    private String accountStatusType;

    @JsonIgnore
    @OneToMany(mappedBy = "accountStatus",fetch = FetchType.LAZY)
    private Set<Account> statusOfAccountsSet;

    public AccountStatus(Long accountStatusId, String accountStatus) {
        this.accountStatusId = accountStatusId;
        this.accountStatusType = accountStatus;
    }

    public AccountStatus(String accountStatus) {
        this.accountStatusType = accountStatus;
    }
}
