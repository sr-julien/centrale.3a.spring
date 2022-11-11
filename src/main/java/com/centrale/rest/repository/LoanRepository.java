package com.centrale.rest.repository;

import com.centrale.rest.entity.Loan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LoanRepository  extends CrudRepository<Loan, Long>{
    int countLoansByBorrowerIdAndReturned(Long id, boolean returned);
    int countLoansByBorrowedBookIdAndReturned(Long borrowedBook_id, boolean returned);
    Optional<Loan> findLoanByBorrowedBookIdAndBorrowerId(Long borrowedBookId, Long BorrowerId);
}
