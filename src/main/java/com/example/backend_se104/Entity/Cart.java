package com.example.backend_se104.Entity;


import com.example.backend_se104.Entity.Model.User;
import lombok.Data;

import java.util.List;

@Data
public class Cart {
    private List<CartBook> cartBooks;
    private User user;
    private String pay;
}
