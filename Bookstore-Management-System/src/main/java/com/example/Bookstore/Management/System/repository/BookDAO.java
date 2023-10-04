package com.example.Bookstore.Management.System.repository;

import com.example.Bookstore.Management.System.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Repository
public interface BookDAO extends JpaRepository<Book, Long> {

    List<Book> findByAuthor(String author);

    List<Book> findByPublicationYearRange(int start_year, int end_year);
}
