package com.example.Bookstore.Management.System.controller;

import com.example.Bookstore.Management.System.DTO.BookRequest;
import com.example.Bookstore.Management.System.DTO.BookResponse;
import com.example.Bookstore.Management.System.entity.Book;
import com.example.Bookstore.Management.System.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {


    private BookService bookService;

    @Autowired
    public BooksController(BookService bookService){
        this.bookService = bookService;
    }

    //GET /books
    //Retrieves all books from the database
    @GetMapping("")
    public List<BookResponse> getAllBooks(){
        return this.bookService.getAllBooks();
    }

    //POST /books
    //Adds a new book to the database
    @PostMapping("")
    public BookResponse addBook(@RequestBody BookRequest bookRequest){
        return this.bookService.saveBook(bookRequest);
    }

    //PUT /books/{id}
    //Updates a book based on id
    @PutMapping("/{id}")
    public BookResponse updateBook(@PathVariable Long id,
                                   @RequestBody BookRequest bookRequest) {
        return this.bookService.updateBook(id, bookRequest);
    }

    //DELETE /books/{id}
    @DeleteMapping("/{id}")
    public String deleteBookById(@PathVariable Long id){
        this.bookService.deleteBookById(id);
        return "Book for id " + id + " deleted.";
    }

    //GET /books/byAuthor
    //Find books for author specified
    //help from https://stackoverflow.com/questions/32201441/how-do-i-retrieve-query-parameters-in-a-spring-boot-controller
    @GetMapping("/byAuthor")
    public List<BookResponse> findBooksByAuthor(@RequestParam("author") String author){
        return this.bookService.findByAuthor(author);
    }

    //GET /books/byAuthor
    //Find books for author specified
    //help from https://stackoverflow.com/questions/32201441/how-do-i-retrieve-query-parameters-in-a-spring-boot-controller
    @GetMapping("/byPublicationYearRange")
    public List<BookResponse> findBooksByAuthor(@RequestParam("startYear") int start_year,
                                                @RequestParam("endYear") int end_year){
        return this.bookService.findBooksByPublicationYearRange(start_year, end_year);
    }
}
