package com.tcs.training.bookms.controller;

import java.util.Optional;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tcs.training.bookms.model.Book;
import com.tcs.training.bookms.repo.BookRepo;
import com.tcs.training.bookms.util.ResponseData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class BookMsController {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private RestTemplate restTemplate;

    // private Logger log = LoggerFactory.getLogger(BookMsController.class);

    @PostMapping("/book/post")
    public ResponseData<Book> addBook(@RequestBody Book entity) {
        Example<Book> exampleBook = Example.of(entity);
        Optional<Book> book = bookRepo.findOne(exampleBook);
        if (book.isPresent()) {
            return new ResponseData<Book>(409, "Book already exist", entity);
        } else {
            bookRepo.saveAndFlush(entity);
            return new ResponseData<Book>(200, "Book created successfully", entity);
        }
    }

    @GetMapping("/book/{id}")
    public ResponseData<Book> getMethodName(@PathVariable Long id) {
        Optional<Book> book = bookRepo.findById(id);
        if (book.isPresent()) {
            return new ResponseData<Book>(200, "Book fetched successfully", book.get());
        } else {
            return new ResponseData<Book>(404, "Book does not exsist in the system", null);
        }
    }

    @PatchMapping("/book/patch")
    public ResponseData<Book> editBook(@RequestBody Book entity) {
        Optional<Book> bookRow = bookRepo.findById(entity.getId());
        if (bookRow.isPresent()) {
            Book book = bookRow.get();
            book.updateBook(entity);
            return new ResponseData<Book>(200, "Book updated successfully", bookRepo.save(book));
        } else {
            return new ResponseData<Book>(404, "Book does not exsist in the system", null);
        }
    }

    @DeleteMapping("/book/delete/{id}")
    public ResponseData<Book> deleteBook(@PathVariable Long id) {
        Optional<Book> deletedBook = bookRepo.findById(id);
        if (deletedBook.isPresent()) {
            bookRepo.deleteById(id);
            return new ResponseData<Book>(200, "Book deleted successfully", deletedBook.get());
        } else {
            return new ResponseData<Book>(404, "Book does not exsist in the system", null);
        }
    }
}
