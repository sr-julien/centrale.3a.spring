package com.centrale.rest;

import com.centrale.rest.entity.Book;
import com.centrale.rest.entity.Client;
import com.centrale.rest.repository.BookRepository;
import com.centrale.rest.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class RestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    // Simply to execute some code after startup
    @Bean
    public CommandLineRunner demo(ClientRepository clientRepository, BookRepository bookRepository) {
        return (args) -> {
            Client client = new Client();
            client.setFirstName("Eva");
            client.setLastName("Porée");
            client.setBirthDayYear(1997);
            clientRepository.save(client);

            Book book1 = new Book();
            book1.setTitle("Headd First Java");
            book1.setAuthor("Kathy Sierra");
            book1.setOnlyForAdult(false);

            Book book2 = new Book();
            book2.setTitle("The Exorcist");
            book2.setAuthor("William Peter Blatty");
            book2.setOnlyForAdult(true);

            Book book3 = new Book();
            book3.setTitle("Les plus belles randonnées de France");
            book3.setAuthor("Patrick Espel");
            book3.setOnlyForAdult(false);

            Book book4 = new Book();
            book4.setTitle("Histoire de Marseille");
            book4.setAuthor("Émile Temime");
            book4.setOnlyForAdult(false);

            bookRepository.save(book1);
            bookRepository.save(book2);
            bookRepository.save(book3);
            bookRepository.save(book4);
        };
    }
}
