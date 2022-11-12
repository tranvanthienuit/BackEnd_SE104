package com.example.backend_se104.Controller.Admin;

import com.example.backend_se104.entity.model.Blog;
import com.example.backend_se104.entity.model.User;
import com.example.backend_se104.security.userDetail;
import com.example.backend_se104.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import spring.Service.BlogService;

import java.time.LocalDate;

@RestController
public class AdminBlog {
    @Autowired
    BlogService blogService;
    @Autowired
    UserService userService;

    @PostMapping("/admin/them-blog")
    public ResponseEntity<?> saveBlog(@RequestBody Blog blog) throws Exception {
        userDetail user1 = (userDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUserByUserId(user1.getUserId());a
        blog.setUser(user);
        LocalDate ldate = LocalDate.now();
        java.sql.Date date = java.sql.Date.valueOf(ldate);
        blog.setDayAdd(date);
        blogService.saveBlog(blog);
        return new ResponseEntity<>("successful", HttpStatus.OK);
    }

    @DeleteMapping("/admin/xoa-blog/{blogId}")
    public ResponseEntity<?> deleteBlog(@RequestBody @PathVariable(name = "blogId") String blogId) {
        blogService.findAndDeleteBlog(blogId);
        return new ResponseEntity<>("successful", HttpStatus.OK);
    }

    @PostMapping("/admin/sua-blog")
    public ResponseEntity<?> updateBlog(@RequestBody Blog blog) {
        Blog blog1 = blogService.findBlog(blog.getBlogId());
        if (blog.getContent() != null)
            blog1.setContent(blog.getContent());
        if (blog.getContext() != null)
            blog1.setContext(blog.getContext());
        if (blog.getTitle() != null)
            blog1.setTitle(blog.getTitle());
        if (blog.getImage() != null)
            blog1.setImage(blog.getImage());
        blogService.saveBlog(blog1);
        return new ResponseEntity<>("successful", HttpStatus.OK);
    }

}
