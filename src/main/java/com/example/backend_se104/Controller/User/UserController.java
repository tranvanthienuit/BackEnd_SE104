package com.example.backend_se104.Controller.User;


import com.example.backend_se104.Entity.Mail;
import com.example.backend_se104.Entity.Model.Orderss;
import com.example.backend_se104.Entity.Model.OrderssDetail;
import com.example.backend_se104.Entity.Model.User;
import com.example.backend_se104.JWT.JwtTokenProvider;
import com.example.backend_se104.Repository.MailService;
import com.example.backend_se104.Repository.UserRepository;
import com.example.backend_se104.Sercurity.userDetail;
import com.example.backend_se104.Service.OrderssDeSevice;
import com.example.backend_se104.Service.OrderssSevice;
import com.example.backend_se104.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@RestController
@Transactional
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MailService mailService;
    @Autowired
    AuthenticationManager manager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    OrderssSevice orderssSevice;
    @Autowired
    OrderssDeSevice orderssDeSevice;

    @PostMapping(value = {"/user/sua-thong-tin", "/admin/sua-thong-tin", "/seller/sua-thong-tin"})
    public ResponseEntity<User> editInfo(@RequestBody(required = false) User user) throws Exception {
        userDetail userDetail = (userDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user1 = userService.findUserByUserId(user.getUserId());
        if (user.getFullName() != null) {
            user1.setFullName(user.getFullName());
//            userService.editUserFullname(user.getFullName(), userDetail.getUserId());
        }
        if (user.getNameUser() != null) {
            user1.setNameUser(user.getNameUser());
//            userService.editUserName(user.getNameUser(), userDetail.getUserId());
        }
        if (user.getAddress() != null) {
            user1.setAddress(user.getAddress());
//            userService.editUserAdress(user.getAddress(), userDetail.getUserId());
        }
        if (user.getTelephone() != null) {
            user1.setTelephone(user.getTelephone());
//            userService.editUserTelephone(user.getTelephone(), userDetail.getUserId());
        }
        if (user.getEmail() != null) {
            user1.setEmail(user.getEmail());
//            userService.editUserEmail(user.getEmail(), userDetail.getUserId());
        }
        if (user.getSex() != null) {
            user1.setSex(user.getSex());
//            userService.editUserSex(user.getSex(), userDetail.getUserId());
        }
        userService.saveUser(user1);
        user1 = userService.findUserByUserId(user.getUserId());
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }

    @PostMapping(value = {"/user/cap-nhat-anh", "/admin/cap-nhat-anh", "/seller/cap-nhat-anh"})
    public ResponseEntity<User> editImg(@RequestBody Map<String, Object> image) throws Exception {
        userDetail userDetail = (userDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUserByUserId(userDetail.getUserId());
        user.setImage(image.get("image").toString());
        userService.saveUser(user);
//        userService.editImage(getImageByte(), user.getUserId());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = {"/user/sua-mat-khau", "/admin/sua-mat-khau", "/seller/sua-mat-khau"})
    public ResponseEntity<?> editPassword(@RequestBody Map<String, Object> password) {
        userDetail userDetail = (userDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUserByUserId(userDetail.getUserId());
        if (passwordEncoder.matches(password.get("oldPassword").toString(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(password.get("newPassword").toString()));
            userService.saveUser(user);
//            userService.editUserPass(passwordEncoder.encode(newPassword), user.getUserId());
            return new ResponseEntity<>("thanh cong roi ban ey", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("mat khau cu sai", HttpStatus.OK);
        }
    }

    public byte[] getImageByte() {
        userDetail userDetail = (userDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUserByUserId(userDetail.getUserId());
        if (user.getImage() == null)
            return null;
        return user.getImage().getBytes();
    }

    @PostMapping(value = {"/quen-mat-khau"})
    public ResponseEntity<?> forgetPass(@RequestBody Map<String, Object> email) {
        String Email = email.get("email").toString();
        if (mailService.checkMail(Email)) {
            User user = userRepository.findByEmail(Email);
            String token = jwtTokenProvider.generateAccessToken(user.getUserId(), user.getNameUser());
            Mail mail = new Mail();
            mail.setMailFrom("uitsneaker@gmail.com");
            mail.setMailTo(Email);
            mail.setMailSubject("Quên password");
            mail.setMailContent("<h1>Reset Password</h1></br></br>\n" +
                    "<h2>Xin chào quý khách mật khẩu của bạn đang được reset.</br>\n" +
                    "\tHãy nhấp vào link dưới đây để cài đặt mật khẩu lại. Cảm ơn quý khách\n</h2>\n" +
//                    "<h3>Link: </h3>" + "<a href=https://uitbook.netlify.app/cai-dat-mat-khau-moi/"+Email+"/" + token + ">" + email + "</a>");
                    "<h3>Link: </h3>" + "<a href=http://localhost:3000/cai-dat-mat-khau-moi/" + Email + "/" + token + ">" + email + "</a>");

            mailService.sendEmail(mail);
            return new ResponseEntity<>("successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("không có mail nào trong tài khoản", HttpStatus.OK);
    }

    @PostMapping(value = {"/cai-dat-mat-khau-moi/{token}"})
    public ResponseEntity<?> setPassword(@PathVariable("token") String token, @RequestBody Map<String, Object> emailAndPass) {
        if (jwtTokenProvider.validateToken(token)) {
            userService.setPassword(passwordEncoder.encode(emailAndPass.get("password").toString()), emailAndPass.get("email").toString());
            return new ResponseEntity<>("successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("error", HttpStatus.OK);
    }

    @GetMapping(value = {"/xem-tai-khoan"})
    public ResponseEntity<User> getUser() throws Exception {
        userDetail userDetail = (userDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUserByUserId(userDetail.getUserId());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/tim-user/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable(name = "email") String email) {
        if (email != null)
            return new ResponseEntity<>(userService.findUser(email), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = {"/user/timorderss"})
    public ResponseEntity<List<Orderss>> findOrderss(@RequestBody Map<String, Object> keysearch) {
        if (orderssSevice.findOrder(keysearch.get("keysearch").toString()).size() == 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            List<Orderss> orderssList = orderssSevice.findOrder(keysearch.get("keysearch").toString());
            return new ResponseEntity<>(orderssList, HttpStatus.OK);
        }
    }

    @PostMapping(value = {"/user/tim-Orderssde/{orderId}", "/user/tim-Orderssde"})
    public ResponseEntity<?> findOrderDe(@PathVariable("orderId") String orderDeId) {
        if (orderDeId == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            List<OrderssDetail> orderssDetail = orderssDeSevice.findOrderssDe(orderDeId);
            return new ResponseEntity<>(orderssDetail, HttpStatus.OK);
        }
    }
}
