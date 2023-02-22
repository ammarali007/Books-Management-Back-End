package com.gravity.book.services;

import com.gravity.book.dtos.BookDTO;
import com.gravity.book.entities.Book;
import com.gravity.book.exceptions.BookNotFoundException;
import com.gravity.book.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository repository;

    public BookDTO addBook(@NotNull BookDTO bookDTO){
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setAuthor(bookDTO.getAuthor());
        repository.save(book);

        bookDTO.setId(book.getId());
        return bookDTO;
    }

    public BookDTO getBook(Integer id) {

        Optional<Book> bookOptional = repository.findById(id);

        if (bookOptional.isPresent()){
            Book book = bookOptional.get();
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getId());
            bookDTO.setName(book.getName());
            bookDTO.setAuthor(book.getAuthor());
            return bookDTO;
        }

        throw new BookNotFoundException("book not found with : " + id);
    }

//    public List<Book> getAllBooks(){
//        return repository.findAll();
//    }

    public BookDTO deleteBook(Integer id) {

        Book book = repository.getReferenceById(id);
        if (repository.existsById(id)){
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getId());
            bookDTO.setName(book.getName());
            bookDTO.setAuthor(book.getAuthor());
            repository.deleteById(id);
            return bookDTO;
        }

        throw new BookNotFoundException("book not found with : " + id);
    }
}
