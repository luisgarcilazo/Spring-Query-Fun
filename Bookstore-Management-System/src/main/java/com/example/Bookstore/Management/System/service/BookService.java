package com.example.Bookstore.Management.System.service;

import com.example.Bookstore.Management.System.DTO.BookRequest;
import com.example.Bookstore.Management.System.DTO.BookResponse;
import com.example.Bookstore.Management.System.entity.Book;

import java.util.List;

public interface BookService {

    List<BookResponse> getAllBooks();

    BookResponse findById(Long id);
    BookResponse saveBook(BookRequest book);

    BookResponse updateBook(Long id, BookRequest book);

    void deleteBookById(Long id);

    List<BookResponse> findBooksByPublicationYearRange(int start_year, int end_year);

    List<BookResponse> findByAuthor(String author);
}
