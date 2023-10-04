package com.example.Bookstore.Management.System.controller;

import com.example.Bookstore.Management.System.DTO.BookResponse;
import com.example.Bookstore.Management.System.entity.Book;
import com.example.Bookstore.Management.System.repository.BookDAO;
import com.example.Bookstore.Management.System.service.BookService;
import com.example.Bookstore.Management.System.service.BookServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//help from here https://www.youtube.com/watch?v=NW8i2gna4qA&list=PLRhHH6sj6xkxp5qxb5g3Rlfj_8lRNK2Qi&index=2
@WebMvcTest(BooksController.class)
class BookControllerTest {
    @MockBean
    private BookService bookService;
    private AutoCloseable autoCloseable;

    @Autowired
    private MockMvc mockMvc;




    //help from here too https://stackoverflow.com/questions/50305132/spring-mockmvc-testing-method-get
    @Test
    void findBooksByPublicationYearRangeTest1() throws Exception{

        //first entry
        BookResponse book1 = new BookResponse();
        book1.setId(1L);
        book1.setTitle("Sample Title 1");
        book1.setAuthor("Sample Author 1");
        book1.setPrice(new BigDecimal(9.99));
        book1.setPublicationYear(2005);

        //second entry
        BookResponse book2 = new BookResponse();
        book2.setId(2L);
        book2.setTitle("Sample Title 2");
        book2.setAuthor("Sample Author 2");
        book2.setPrice(new BigDecimal(81.99));
        book2.setPublicationYear(2020);

        //second entry
        BookResponse book3 = new BookResponse();
        book3.setId(3L);
        book3.setTitle("Sample Title 3");
        book3.setAuthor("Sample Author 3");
        book3.setPrice(new BigDecimal(109.99));
        book3.setPublicationYear(2018);

        //set up mock
        List<BookResponse> listFrom2005to2010 = new ArrayList<>();
        listFrom2005to2010.add(book1);
        when(bookService.findBooksByPublicationYearRange(2005, 2010)).thenReturn(listFrom2005to2010);

        //convert list to json, help from here https://www.geeksforgeeks.org/convert-java-object-to-json-string-using-jackson-api/
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(listFrom2005to2010);

        mockMvc.perform(get("/books/byPublicationYearRange?startYear=2005&endYear=2010"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(jsonList));

        verify(bookService).findBooksByPublicationYearRange(2005,2010);
    }


    //help from here too https://stackoverflow.com/questions/50305132/spring-mockmvc-testing-method-get
    @Test
    void findBooksByPublicationYearRangeTest2() throws Exception{

        //first entry
        BookResponse book1 = new BookResponse();
        book1.setId(1L);
        book1.setTitle("Sample Title 1");
        book1.setAuthor("Sample Author 1");
        book1.setPrice(new BigDecimal(9.99));
        book1.setPublicationYear(2005);

        //second entry
        BookResponse book2 = new BookResponse();
        book2.setId(2L);
        book2.setTitle("Sample Title 2");
        book2.setAuthor("Sample Author 2");
        book2.setPrice(new BigDecimal(81.99));
        book2.setPublicationYear(2020);

        //second entry
        BookResponse book3 = new BookResponse();
        book3.setId(3L);
        book3.setTitle("Sample Title 3");
        book3.setAuthor("Sample Author 3");
        book3.setPrice(new BigDecimal(109.99));
        book3.setPublicationYear(2018);

        //set up mock
        List<BookResponse> listFrom2015to2020 = new ArrayList<>();
        listFrom2015to2020.add(book2);
        listFrom2015to2020.add(book3);
        when(bookService.findBooksByPublicationYearRange(2015, 2020)).thenReturn(listFrom2015to2020);

        //convert list to json, help from here https://www.geeksforgeeks.org/convert-java-object-to-json-string-using-jackson-api/
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(listFrom2015to2020);

        mockMvc.perform(get("/books/byPublicationYearRange?startYear=2015&endYear=2020"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(jsonList));

        verify(bookService).findBooksByPublicationYearRange(2015,2020);
    }

    @Test
    void findBooksByAuthorTest1() throws Exception{
        //first entry
        BookResponse book1 = new BookResponse();
        book1.setId(1L);
        book1.setTitle("Sample Title 1");
        book1.setAuthor("Sample Author 1");
        book1.setPrice(new BigDecimal(9.99));
        book1.setPublicationYear(2005);

        //second entry
        BookResponse book2 = new BookResponse();
        book2.setId(2L);
        book2.setTitle("Sample Title 2");
        book2.setAuthor("Sample Author 2");
        book2.setPrice(new BigDecimal(81.99));
        book2.setPublicationYear(2020);

        //third entry
        BookResponse book3 = new BookResponse();
        book3.setId(3L);
        book3.setTitle("Sample Title 3");
        book3.setAuthor("Sample Author 1");
        book3.setPrice(new BigDecimal(109.99));
        book3.setPublicationYear(2018);

        //fourth entry
        BookResponse book4 = new BookResponse();
        book4.setId(3L);
        book4.setTitle("Sample Title 4");
        book4.setAuthor("Sample Author 3");
        book4.setPrice(new BigDecimal(50.99));
        book4.setPublicationYear(2013);

        //test with Sample Author 1
        List<BookResponse> expectedBookResponse1 = new ArrayList<>();
        expectedBookResponse1.add(book1);
        expectedBookResponse1.add(book3);

        //mock expectation
        when(bookService.findByAuthor("Sample Author 1")).thenReturn(expectedBookResponse1);

        //convert list to json, help from here https://www.geeksforgeeks.org/convert-java-object-to-json-string-using-jackson-api/
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(expectedBookResponse1);

        mockMvc.perform(get("/books/byAuthor?author=Sample Author 1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(jsonList));

        verify(bookService).findByAuthor("Sample Author 1");
    }

    @Test
    void findBooksByAuthorTest2() throws Exception{
        //first entry
        BookResponse book1 = new BookResponse();
        book1.setId(1L);
        book1.setTitle("Sample Title 1");
        book1.setAuthor("Sample Author 1");
        book1.setPrice(new BigDecimal(9.99));
        book1.setPublicationYear(2005);

        //second entry
        BookResponse book2 = new BookResponse();
        book2.setId(2L);
        book2.setTitle("Sample Title 2");
        book2.setAuthor("Sample Author 2");
        book2.setPrice(new BigDecimal(81.99));
        book2.setPublicationYear(2020);

        //third entry
        BookResponse book3 = new BookResponse();
        book3.setId(3L);
        book3.setTitle("Sample Title 3");
        book3.setAuthor("Sample Author 1");
        book3.setPrice(new BigDecimal(109.99));
        book3.setPublicationYear(2018);

        //fourth entry
        BookResponse book4 = new BookResponse();
        book4.setId(3L);
        book4.setTitle("Sample Title 4");
        book4.setAuthor("Sample Author 3");
        book4.setPrice(new BigDecimal(50.99));
        book4.setPublicationYear(2013);

        //test with Sample Author 3
        List<BookResponse> expectedBookResponse1 = new ArrayList<>();
        expectedBookResponse1.add(book4);

        //mock expectation
        when(bookService.findByAuthor("Sample Author 3")).thenReturn(expectedBookResponse1);

        //convert list to json, help from here https://www.geeksforgeeks.org/convert-java-object-to-json-string-using-jackson-api/
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(expectedBookResponse1);

        mockMvc.perform(get("/books/byAuthor?author=Sample Author 3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(jsonList));

        verify(bookService).findByAuthor("Sample Author 3");
    }
}