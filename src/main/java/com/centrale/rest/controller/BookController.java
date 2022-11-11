package com.centrale.rest.controller;

import com.centrale.rest.entity.Book;
import com.centrale.rest.repository.BookRepository;
import com.centrale.rest.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@ResponseBody
@RequestMapping(value = "/book")
@AllArgsConstructor
public class BookController {

    private BookRepository bookRepository;
    private LoanRepository loanRepository;

    @GetMapping(value = "/getBooks")
    public Iterable<Book> getEachBook(){
        return this.bookRepository.findAll();
    }

    @GetMapping(value = "/getBook/{id}")
    public Optional<Book> getIdBook(@PathVariable Long id){return this.bookRepository.findById(id);}

    @PostMapping(value = "/newBook")
    public Book createNewBook(@RequestBody Book book) {
        this.bookRepository.save(book);
        return book;
    }

    @GetMapping(value = "/freeBooks")
    public ArrayList<Book> getFreeBooks(){
        Iterable<Book> allBooks = this.bookRepository.findAll();
        ArrayList<Book> freeBooks = new ArrayList<>();

        allBooks.forEach(book -> {
            if (loanRepository.countLoansByBorrowedBookIdAndReturned(book.getId(),false)==0){
                freeBooks.add(book);
            }
        });
        return freeBooks;
    }
}
