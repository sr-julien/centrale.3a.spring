package com.centrale.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String author;
    private boolean onlyForAdult;

    @JsonIgnore
    @OneToMany(mappedBy="borrowedBook", cascade = CascadeType.ALL)
    private Set<Loan> bookLoan = new HashSet<>();

    public void addLoan(Loan loan){
        bookLoan.add(loan);
    }
}
