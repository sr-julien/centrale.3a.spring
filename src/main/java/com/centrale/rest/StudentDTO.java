package com.centrale.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDTO {
    private String firstname;
    private  String lastname;
    private String className;
}
