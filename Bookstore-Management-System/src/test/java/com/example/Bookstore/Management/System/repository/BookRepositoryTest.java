package com.example.Bookstore.Management.System.repository;

import com.example.Bookstore.Management.System.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


//help from here https://www.youtube.com/watch?v=Geq60OVyBPg
@DataJpaTest
class BookRepositoryTest {


    @Autowired
    private BookDAO bookDAO;
    @Test
    void findByAuthorTest() {
        //given
        //Entry 1
        Book book1 = new Book();
        book1.setAuthor("Luis Test");
        book1.setTitle("Test Book 1");
        book1.setPublicationYear(2023);
        book1.setPrice(new BigDecimal(70.99));
        bookDAO.save(book1);

        //Entry 2
        Book book2 = new Book();
        book2.setAuthor("Mary Test");
        book2.setTitle("Test Book 2");
        book2.setPublicationYear(2021);
        book2.setPrice(new BigDecimal(11.22));
        bookDAO.save(book2);

        //Entry 3
        Book book3 = new Book();
        book3.setAuthor("Luis Test");
        book3.setTitle("Test Book 3");
        book3.setPublicationYear(2019);
        book3.setPrice(new BigDecimal(33.33));
        bookDAO.save(book3);

        //when
        List<Book> result = bookDAO.findByAuthor("Luis Test");


        //then
        assertTrue(result.get(0).equals(book1));
        assertTrue(result.get(1).equals(book3));
    }

    @Test
    void findByPublicationYearRangeTest1() {
        //given
        //Entry 1
        Book book1 = new Book();
        book1.setAuthor("Luis Test");
        book1.setTitle("Test Book 1");
        book1.setPublicationYear(2000);
        book1.setPrice(new BigDecimal(70.99));
        bookDAO.save(book1);

        //Entry 2
        Book book2 = new Book();
        book2.setAuthor("Mary Test");
        book2.setTitle("Test Book 2");
        book2.setPublicationYear(2009);
        book2.setPrice(new BigDecimal(11.22));
        bookDAO.save(book2);

        //Entry 3
        Book book3 = new Book();
        book3.setAuthor("Luis Test");
        book3.setTitle("Test Book 3");
        book3.setPublicationYear(2015);
        book3.setPrice(new BigDecimal(33.33));
        bookDAO.save(book3);

        //when
        List<Book> result = bookDAO.findByPublicationYearRange(2000, 2009);
        //only books 1 and 2 have date between 2000 and 2009 (inclusive)
        assertTrue(result.size() == 2);
        assertTrue(result.get(0).equals(book1));
        assertTrue(result.get(1).equals(book2));
    }

    @Test
    void findByPublicationYearRangeTest2() {
        //given
        //Entry 1
        Book book1 = new Book();
        book1.setAuthor("Luis Test");
        book1.setTitle("Test Book 1");
        book1.setPublicationYear(2005);
        book1.setPrice(new BigDecimal(70.99));
        bookDAO.save(book1);

        //Entry 2
        Book book2 = new Book();
        book2.setAuthor("Mary Test");
        book2.setTitle("Test Book 2");
        book2.setPublicationYear(2006);
        book2.setPrice(new BigDecimal(11.22));
        bookDAO.save(book2);

        //Entry 3
        Book book3 = new Book();
        book3.setAuthor("Luis Test");
        book3.setTitle("Test Book 3");
        book3.setPublicationYear(2014);
        book3.setPrice(new BigDecimal(33.33));
        bookDAO.save(book3);

        //when
        List<Book> result = bookDAO.findByPublicationYearRange(2010, 2015);
        //only books date is between 2000 and 2009 (inclusive)
        assertTrue(result.size() == 1);
        assertTrue(result.get(0).equals(book3));
    }

    @Test
    void findByPublicationYearRangeTest3() {
        //given
        //Entry 1
        Book book1 = new Book();
        book1.setAuthor("Luis Test");
        book1.setTitle("Test Book 1");
        book1.setPublicationYear(2005);
        book1.setPrice(new BigDecimal(70.99));
        bookDAO.save(book1);

        //Entry 2
        Book book2 = new Book();
        book2.setAuthor("Mary Test");
        book2.setTitle("Test Book 2");
        book2.setPublicationYear(2006);
        book2.setPrice(new BigDecimal(11.22));
        bookDAO.save(book2);

        //Entry 3
        Book book3 = new Book();
        book3.setAuthor("Luis Test");
        book3.setTitle("Test Book 3");
        book3.setPublicationYear(2014);
        book3.setPrice(new BigDecimal(33.33));
        bookDAO.save(book3);

        //when
        List<Book> result = bookDAO.findByPublicationYearRange(2020, 2023);
        //result should be empty, no book between 2020 and 2023
        assertTrue(result.size() == 0);
    }
}