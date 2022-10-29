package com.example.backend_se104.repository;

import com.example.backend_se104.entity.book_category;
import com.example.backend_se104.entity.model.OrderssDetail;
import com.example.backend_se104.entity.month_price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderssDeRepository extends JpaRepository<OrderssDetail, String> {
    @Query("select new com.example.backend_se104.entity.book_category(sum(u.count),u.book.category.nameCate) from OrderssDetail u group by u.book.category.nameCate")
    List<book_category> getBookAndCategory();

    @Query("select new com.example.backend_se104.entity.month_price(month(u.orderss.orderssDate),sum(u.total)) from OrderssDetail u group by month(u.orderss.orderssDate)")
    List<month_price> getPriceAndMonth();
}
