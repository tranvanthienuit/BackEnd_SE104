package com.example.backend_se104.service;

import com.example.backend_se104.entity.book_category;
import com.example.backend_se104.entity.month_price;
import com.example.backend_se104.repository.OrderssDeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderssDeSevice {
    @Autowired
    OrderssDeRepository orderssDeRepository;


    public List<book_category> getBookAndCategory() {
        return orderssDeRepository.getBookAndCategory();
    }

    public List<month_price> getPriceAndMonth() {
        return orderssDeRepository.getPriceAndMonth();
    }
}
