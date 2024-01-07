package com.example.unik;

import com.example.unik.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users user){
        try {
            usersService.registerUser(user);
            return ResponseEntity.ok("Регистрация прошла успешно");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка регистрации");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Users user, HttpServletRequest request){
        boolean isAuthenticated = usersService.authenticate(user.getUsername(), user.getPassword());
        if(isAuthenticated) {
            request.getSession().setAttribute("user", user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверные учетные данные");
        }
    }
}

