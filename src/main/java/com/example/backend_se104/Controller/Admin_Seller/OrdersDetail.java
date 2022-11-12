package com.example.backend_se104.Controller.Admin_Seller;

import com.example.backend_se104.entity.model.Orderss;
import com.example.backend_se104.entity.model.OrderssDetail;
import com.example.backend_se104.service.OrderssDeSevice;
import com.example.backend_se104.service.OrderssSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.Service.BookService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = {"/api/seller/order-detail", "/api/admin/order-detail"})
public class OrdersDetail {
    @Autowired
    OrderssDeSevice orderssDeSevice;
    @Autowired
    OrderssSevice orderssSevice;
    @Autowired
    BookService bookService;

    @DeleteMapping(value = {"/delete/{id}"})
    public ResponseEntity<String> removeOrderssDe(@PathVariable(value = "id", required = false) String OrderssDeId) throws Exception {
        if (orderssDeSevice.findOrderssDe(OrderssDeId) != null) {
            orderssDeSevice.removeByOrderssDeId(OrderssDeId);
            return new ResponseEntity<>("successful", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = {"page/{number}"})
    public ResponseEntity<OrderssDelist> getAllOrderssDe(
            @PathVariable(name = "number", required = false) Integer page) throws Exception {
        OrderssDelist orderssDelist = new OrderssDelist();
        if (page == null) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, 12);
        Page<OrderssDetail> OrderssDetailPage = orderssDeSevice.getAllOrderssDe(pageable);
        List<OrderssDetail> orderssDetailPageContent = OrderssDetailPage.getContent();
        if (orderssDetailPageContent.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            orderssDelist.setOrderssDelists(orderssDetailPageContent);
            orderssDelist.setCount(orderssDeSevice.getAllOrderssDe().size());
            return new ResponseEntity<>(orderssDelist, HttpStatus.OK);
        }
    }

    @GetMapping(value = {"search/{userName}"})
    public ResponseEntity<List<OrderssDetail>> findOrderssDe(@PathVariable(name = "userName", required = false) String userName) {
        if (userName == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            List<OrderssDetail> orderssDetailList = new ArrayList<>();
            List<Orderss> orderss = orderssSevice.findOrder(userName);
            for (Orderss orderss1 : orderss) {
                List<OrderssDetail> orderssDetailList1 = orderssDeSevice.findOrderssDetailsByOrderss(orderss1.getOrderssId());
                orderssDetailList.addAll(orderssDetailList1);
            }
            return new ResponseEntity<>(orderssDetailList, HttpStatus.OK);
        }
    }

}
