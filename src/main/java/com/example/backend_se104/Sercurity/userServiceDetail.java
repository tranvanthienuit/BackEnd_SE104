package com.example.backend_se104.Sercurity;


import com.example.backend_se104.Entity.Model.User;
import com.example.backend_se104.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userServiceDetail implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return userDetail.createUserDetail(user);
    }

}