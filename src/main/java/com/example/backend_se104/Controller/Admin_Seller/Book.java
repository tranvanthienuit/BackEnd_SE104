package com.example.backend_se104.Controller.Admin_Seller;

import com.example.backend_se104.entity.BookList;
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
public class Book {
    @Autowired
    spring.Service.BookService booksService;
    @Autowired
    spring.Service.CategoryService categoryService;

    @GetMapping(value = {"/api/seller/page/{number}", "/api/seller/page","/api/admin/page/{number}", "/api/admin/page"})
    public ResponseEntity<BookList> getAllBook(
            @PathVariable(name = "number", required = false) Integer page) throws Exception {
        BookList bookList = new BookList();
        if (page == null) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, 12);
        Page<com.example.backend_se104.entity.model.Book> bookPage = booksService.getAllBookByAdmin(pageable);
        List<com.example.backend_se104.entity.model.Book> bookPageContent = bookPage.getContent();
        if (bookPageContent.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            bookList.setBookList(bookPageContent);
            bookList.setCount(booksService.getAllBook().size());
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        }
    }
}
