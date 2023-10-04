package com.example.Bookstore.Management.System.service;

import com.example.Bookstore.Management.System.DTO.BookRequest;
import com.example.Bookstore.Management.System.DTO.BookResponse;
import com.example.Bookstore.Management.System.entity.Book;
import com.example.Bookstore.Management.System.repository.BookDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {


    private BookDAO bookDAO;

    private EntityManager entityManager;


    @Autowired
    public BookServiceImpl(BookDAO bookDAO, EntityManager entityManager){
        this.bookDAO = bookDAO;
        this.entityManager = entityManager;
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return mapBookListToBookResponseList(this.bookDAO.findAll());
    }

    @Override
    public BookResponse findById(Long id) {
        return mapBookToBookResponse(this.bookDAO.findById(id).orElseThrow(() ->  new RuntimeException("Invalid id")));
    }


    @Override
    public List<BookResponse> findByAuthor(String author){

        TypedQuery<Book> query = entityManager.createNamedQuery("Book.findByAuthor", Book.class);

        query.setParameter("author", author);

        List<Book> results = query.getResultList();

        return mapBookListToBookResponseList(results);
    }

    @Override
    public BookResponse saveBook(BookRequest bookRequest) {
        if(bookRequest.getTitle() == null || bookRequest.getAuthor() == null
          || bookRequest.getPublicationYear() < 0 || bookRequest.getPrice() == null){
            throw new RuntimeException("Incorrect input");
        }
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setPrice(bookRequest.getPrice());
        book.setPublicationYear(bookRequest.getPublicationYear());
        return mapBookToBookResponse(this.bookDAO.save(book));
    }



    @Override
    public BookResponse updateBook(Long id, BookRequest bookRequest) {
        if(bookRequest.getTitle() == null || bookRequest.getAuthor() == null
                || bookRequest.getPublicationYear() < 0 || bookRequest.getPrice() == null){
            throw new RuntimeException("Incorrect input");
        }
        Book original = bookDAO.findById(id).orElseThrow(() -> new RuntimeException("Invalid id for update"));
        original.setTitle(bookRequest.getTitle());
        original.setAuthor(bookRequest.getTitle());
        original.setPrice(bookRequest.getPrice());
        original.setPublicationYear(bookRequest.getPublicationYear());
        return mapBookToBookResponse(this.bookDAO.save(original));
    }

    @Override
    public void deleteBookById(Long id) {
        Book bookToDelete = this.bookDAO.findById(id).orElseThrow(() -> new RuntimeException("Incorrect id for delete"));
        this.bookDAO.delete(bookToDelete);
        return;
    }

    @Override
    public List<BookResponse> findBooksByPublicationYearRange(int start_year, int end_year) {
        TypedQuery<Book> query = entityManager.createNamedQuery("Book.findByPublicationYearRange", Book.class);

        query.setParameter("start_year", start_year);
        query.setParameter("end_year", end_year);

        List<Book> results = query.getResultList();

        return mapBookListToBookResponseList(results);
    }

    private BookResponse mapBookToBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .publicationYear(book.getPublicationYear())
                .build();
    }
    private List<BookResponse> mapBookListToBookResponseList(List<Book> books) {

        List<BookResponse> responses = new ArrayList<>();
        for(Book book: books){
            responses.add(BookResponse.builder().id(book.getId())
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .price(book.getPrice())
                    .publicationYear(book.getPublicationYear()).build());
        }
        return responses;
    }
}
