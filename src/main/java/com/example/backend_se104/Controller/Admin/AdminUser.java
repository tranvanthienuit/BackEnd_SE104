package com.example.backend_se104.Controller.Admin;

import com.example.backend_se104.Entity.Model.Role;
import com.example.backend_se104.Entity.Model.User;
import com.example.backend_se104.Service.RoleService;
import com.example.backend_se104.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AdminUser {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;


    @PostMapping(value = {"/api/admin/{id}"})
    public ResponseEntity<?> editeRole(@PathVariable("id") String userId, @RequestBody Map<String, Object> roleName) {
        User user = userService.findUserByUserId(userId);

        Role role = roleService.findRoleByName(roleName.get("roleName").toString());
        user.setRole(role);
        userService.saveUser(user);
        return new ResponseEntity<>("successful", HttpStatus.OK);
    }
}
