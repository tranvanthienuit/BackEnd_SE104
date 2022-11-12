package com.example.backend_se104.Entity;


import com.example.backend_se104.Entity.Model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSelect {
    private Book book;
    private Long total;
}
