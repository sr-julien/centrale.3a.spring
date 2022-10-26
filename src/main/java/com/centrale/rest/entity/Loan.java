package com.centrale.rest.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date loanBeginning;
    private Date loanEnd;

    @ManyToOne()
    @JoinColumn(name="clientId", nullable=false)
    private Client borrower;

    @ManyToOne()
    @JoinColumn(name="bookId", nullable=false)
    private Book borrowedBook;
}
