package com.centrale.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean returned = false;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name="clientId", nullable=false)
    private Client borrower;

    @ManyToOne()
    @JoinColumn(name="bookId", nullable=false)
    private Book borrowedBook;
}
