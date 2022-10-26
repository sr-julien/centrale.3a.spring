package com.centrale.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String lastName;
    private String firstName;
    private Date birthDayDate;

    @JsonIgnore
    @OneToMany(mappedBy="borrower", cascade = CascadeType.ALL)
    private Set<Loan> clienLoan;
}
