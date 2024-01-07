package com.example.unik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public Users registerUser(Users user) {
        user.setPassword(PasswordHashing.hashPassword(user.getPassword()));
        return usersRepository.save(user);
    }

    public boolean authenticate(String username, String password) {
        Users user = usersRepository.findByUsername(username);
        return user != null && PasswordHashing.verifyPassword(password, user.getPassword());
    }
}

