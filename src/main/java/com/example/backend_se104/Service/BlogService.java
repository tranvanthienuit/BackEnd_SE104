package com.example.backend_se104.Service;


import com.example.backend_se104.Entity.Model.Blog;
import com.example.backend_se104.Repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    public void saveBlog(Blog blog) {
        blogRepository.save(blog);
    }

    public void findAndDeleteBlog(String blogId) {
        Blog blog = blogRepository.findBlogByBlogId(blogId);
        blogRepository.delete(blog);
    }

    public List<Blog> findAllBlog() {
        return blogRepository.findAll();
    }

    public Blog findBlog(String blogId) {
        return blogRepository.findBlogByBlogId(blogId);
    }
}
