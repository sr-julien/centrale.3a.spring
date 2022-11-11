package com.centrale.rest.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private int birthDayYear;
    private int numberBookAllowed = 3;

    @OneToMany(mappedBy="borrower", cascade = CascadeType.ALL)
    private Set<Loan> clientLoan = new HashSet<>();

    public void addLoan(Loan loan){
        clientLoan.add(loan);
    }
}
