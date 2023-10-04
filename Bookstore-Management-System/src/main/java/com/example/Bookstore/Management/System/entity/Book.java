package com.example.Bookstore.Management.System.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name="tbl_books")
@NamedQuery(name="Book.findByAuthor", query = "SELECT b FROM Book b WHERE b.author = :author")
@NamedQuery(name="Book.findByPublicationYearRange", query="SELECT b FROM Book b WHERE b.publicationYear >=:start_year AND b.publicationYear <=:end_year")
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;


    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "publicationYear")
    private int publicationYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", publicationYear=" + publicationYear +
                '}';
    }


    public boolean equals(Book book){
        return this.id.equals(book.getId()) && this.title.equals(book.getTitle())
                && this.author.equals(book.getAuthor())  && this.price.equals(book.getPrice())
                && this.publicationYear == book.getPublicationYear();
    }
}
