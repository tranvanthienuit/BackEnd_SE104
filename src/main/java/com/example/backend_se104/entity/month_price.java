package com.example.backend_se104.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class month_price {
    private Integer month;
    private Double price;
}
