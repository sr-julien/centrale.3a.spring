package com.centrale.rest.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReturnBook {
    private Long idLoan = Long.valueOf(0);
    private Long idClient = Long.valueOf(0);
    private Long idBook = Long.valueOf(0);
}
