package com.example.backend_se104.repository;

import com.example.backend_se104.entity.model.User;
import com.example.backend_se104.entity.month_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByNameUser(String username);

    User findUserByUserId(String userId);

    @Query("select new com.example.backend_se104.entity.month_user(month(u.dayAdd),count(u.userId)) from User u group by month(u.dayAdd)")
    List<month_user> getUserAndMonnth();

}
