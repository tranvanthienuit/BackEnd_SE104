package com.example.backend_se104.Controller.Admin_Seller;


import com.example.backend_se104.Entity.CateList;
import com.example.backend_se104.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Category {
    @Autowired
    CategoryService categoryService;

    @GetMapping(value = {"api/seller/category/page/{number}", "api/admin/category/page/{number}"})
    public ResponseEntity<CateList> getAllCate(
            @PathVariable(name = "number", required = false) Integer page) throws Exception {
        CateList cateList = new CateList();
        if (page == null) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, 12);
        Page<com.example.backend_se104.Entity.Model.Category> categoriesList = categoryService.getAllCategory(pageable);
        List<com.example.backend_se104.Entity.Model.Category> categoryListContent = categoriesList.getContent();
        if (categoryListContent.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            cateList.setCategoryList(categoryListContent);
            cateList.setCount(categoryService.getAllCategory().size());
            return new ResponseEntity<>(cateList, HttpStatus.OK);
        }
    }
}
