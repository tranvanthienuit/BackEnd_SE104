package com.example.backend_se104.Controller.Admin_Seller;


import com.example.backend_se104.Entity.UserList;
import com.example.backend_se104.Service.RoleService;
import com.example.backend_se104.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = {"/api/seller/user", "/api/admin/user"})
public class User {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;


    @DeleteMapping(value = {"delete/{id}}"})
    public ResponseEntity<String> removeUser(@PathVariable(value = "id", required = false) String userId) throws Exception {
        if (userService.findUserByUserId(userId) != null) {
            if (!userService.countUser().equals(1)) {
                userService.removeUserByUserId(userId);
                return new ResponseEntity<>("successful", HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = {"page/{number}"})
    public ResponseEntity<UserList> getAllUser(
            @PathVariable(name = "number", required = false) Integer page) throws Exception {
        UserList userList = new UserList();
        if (page == null) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, 12);
        Page<com.example.backend_se104.Entity.Model.User> userPage = userService.getAllUser(pageable);
        List<com.example.backend_se104.Entity.Model.User> userPageContent = userPage.getContent();
        if (userPageContent.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            userList.setUserList(userPageContent);
            userList.setCount(userService.getAllUsers().size());
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }
    }

    @PostMapping(value = {"search"})
    public ResponseEntity<?> findUser(@RequestBody Map<String, Object> keyword) {
        if (keyword != null)
            return new ResponseEntity<>(userService.findUser(keyword.get("keyword").toString()), HttpStatus.OK);
        return new ResponseEntity<>("Không có người dùng nào cả", HttpStatus.OK);
    }
}
