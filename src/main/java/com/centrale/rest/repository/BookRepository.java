package com.centrale.rest.repository;

import com.centrale.rest.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository  extends CrudRepository<Book, Long> {}
