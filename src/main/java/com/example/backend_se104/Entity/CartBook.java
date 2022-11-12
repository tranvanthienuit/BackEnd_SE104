package com.example.backend_se104.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartBook {
    private Integer quantity;
    private String books;
    private Double total;
}
