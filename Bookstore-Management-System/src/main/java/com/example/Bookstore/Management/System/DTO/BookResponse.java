package com.example.Bookstore.Management.System.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookResponse {

    private Long id;
    private String title;
    private String author;
    private BigDecimal price;
    private int publicationYear;
}
