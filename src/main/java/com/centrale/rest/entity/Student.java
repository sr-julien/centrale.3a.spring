package com.centrale.rest.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String lastname;
    private String firstname;

    @ManyToOne
    @JoinColumn(name="school_class_id", nullable=false)
    private SchoolClass schoolClass;
}
