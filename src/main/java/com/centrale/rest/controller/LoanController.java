package com.centrale.rest.controller;

import com.centrale.rest.DTO.NewLoan;
import com.centrale.rest.DTO.ReturnBook;
import com.centrale.rest.entity.Book;
import com.centrale.rest.entity.Client;
import com.centrale.rest.entity.Loan;
import com.centrale.rest.repository.BookRepository;
import com.centrale.rest.repository.ClientRepository;
import com.centrale.rest.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@ResponseBody
@RequestMapping(value = "/loan")
@AllArgsConstructor
public class LoanController {
    private LoanRepository loanRepository;
    private ClientRepository clientRepository;
    private BookRepository bookRepository;

    @GetMapping(value = "/getLoans")
    public Iterable<Loan> getEachLoan(){return this.loanRepository.findAll();}

    @GetMapping(value = "/getLoan/{id}")
    public Optional<Loan> getIdLoan(@PathVariable Long id){return this.loanRepository.findById(id);}


    @PostMapping(value = "/borrowBook")
    public String createNewLoan(@RequestBody NewLoan body) {
        Loan loan = new Loan();
        Optional<Book> opt_book = this.bookRepository.findById(body.getIdBook());
        Optional<Client> opt_client = this.clientRepository.findById(body.getIdClient());

        if(opt_client.isPresent() && opt_book.isPresent()){
            Book book = opt_book.get();
            Client client = opt_client.get();
            if(this.loanRepository.countLoansByBorrowerIdAndReturned(body.getIdClient(),false)<client.getNumberBookAllowed()){
                if (this.loanRepository.countLoansByBorrowedBookIdAndReturned(body.getIdBook(), false)==0){
                    if(!book.isOnlyForAdult() || client.getBirthDayYear()<=(2022 - 18)){
                        loan.setBorrower(client);
                        loan.setBorrowedBook(book);
                        this.loanRepository.save(loan);

                        client.addLoan(loan);
                        book.addLoan(loan);

                        return "Borrowed, idLoan : "+ Long.toString(loan.getId());
                    }else{
                        return "This book is only for adult.";
                    }
                }else{
                    return "This book has already been borrowed.";
                }
            }else{
                return "You can only borrow "+ Integer.toString(client.getNumberBookAllowed())+" books . Return book to borrow more.";
            }
        }else{
            return "idClient or idBook doesn't exist";
        }

    }

    @PostMapping(value = "/returnBook")
    public String returnBook(@RequestBody ReturnBook body){
        if(body.getIdLoan()!=Long.valueOf(0)){
            Optional<Loan> opt_loan = this.loanRepository.findById(body.getIdLoan());
            if(opt_loan.isPresent()){
                Loan loan = opt_loan.get();
                loan.setReturned(true);
                this.loanRepository.save(loan);
                return "Returned";
            }else{
                return "This loan doesn't exist";
            }
        }
        else if(body.getIdClient()!=Long.valueOf(0) && body.getIdBook()!=Long.valueOf(0)){
            Optional<Loan> opt_loan = this.loanRepository.findLoanByBorrowedBookIdAndBorrowerId(body.getIdBook(), body.getIdClient());
            if(opt_loan.isPresent()){
                Loan loan = opt_loan.get();
                loan.setReturned(true);
                this.loanRepository.save(loan);
                return "Returned";
            }else{
                return "This loan doesn't exist";
            }

        }
        return "Need at least idLoan, or idBook and idClient";
    }
}
