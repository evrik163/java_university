package com.example.unik;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository repo;

    public void registerUser(Users user) throws Exception {
        Users existingUser = repo.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new Exception("Пользователь с таким логином уже существует");
        }
        user.setPassword(PasswordHashing.hashPassword(user.getPassword()));
        user.setRole("Viewer");
        repo.save(user);
    }

    public boolean authenticate(String username, String password) {
        Users user = repo.findByUsername(username);
        return user != null && PasswordHashing.verifyPassword(password, user.getPassword());
    }

    public Users get_current_user(HttpServletRequest request) {
        Users current_user = (Users) request.getSession().getAttribute("user");
        return repo.findByUsername(current_user.getUsername());
    }

    public List<String> get_all_users() {
        List<String> userNames = new ArrayList<>();
        repo.findAll().forEach(user -> userNames.add(user.getUsername()));
        return userNames;
    }

}


