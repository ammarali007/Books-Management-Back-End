package com.gravity.book.controllers;

import com.gravity.book.dtos.BookDTO;
import com.gravity.book.dtos.ErrorDTO;
import com.gravity.book.entities.Book;
import com.gravity.book.exceptions.BookNotFoundException;
import com.gravity.book.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {



    private final BookService service;
    @PostMapping("/books")
    public BookDTO addBook(@RequestBody @Validated BookDTO book){
        return service.addBook(book);
    }

    @GetMapping("/books/{id}")
    public BookDTO getBook(@PathVariable("id") Integer id){
        return service.getBook(id);
    }


    @DeleteMapping ("/books/{id}")
    public BookDTO deleteBook(@PathVariable("id") Integer id){
        return service.deleteBook(id);
    }


    @ExceptionHandler({ BookNotFoundException.class})
    public ResponseEntity<ErrorDTO> exception(BookNotFoundException exception) {
        ErrorDTO errorDTO = new ErrorDTO(exception.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

}
