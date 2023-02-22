package com.gravity.book.repositories;

import com.gravity.book.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {


}
