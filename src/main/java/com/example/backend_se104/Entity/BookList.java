package com.example.backend_se104.Entity;


import com.example.backend_se104.Entity.Model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookList {
    List<Book> bookList;
    int count;
}
