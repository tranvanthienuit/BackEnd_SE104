package com.example.backend_se104.service;

import com.example.backend_se104.entity.model.Orderss;
import com.example.backend_se104.entity.month_book;
import com.example.backend_se104.repository.OrderssRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderssSevice {
    @Autowired
    OrderssRepository orderssRepository;

    public List<month_book> getBookAndMonth() {
        return orderssRepository.getBookAndMonth();
    }
}
