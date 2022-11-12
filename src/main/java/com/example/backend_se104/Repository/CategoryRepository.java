package com.example.backend_se104.Repository;


import com.example.backend_se104.Entity.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query("select u from Category u where u.categoryId=:categoryid")
    Category findByCategoryId(@Param("categoryid") String categoryid);
}
