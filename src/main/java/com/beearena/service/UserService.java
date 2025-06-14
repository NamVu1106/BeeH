package com.beearena.service;

import com.beearena.entity.User;
import com.beearena.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean register(String email, String password, String name) {
        if (userRepository.findByEmail(email).isPresent()) return false;
        User user = new User();
        user.setEmail(email);
        user.setPassword(password); // Plain text, chỉ demo
        user.setName(name);
        userRepository.save(user);
        return true;
    }

    public Optional<User> login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent() && password.equals(userOpt.get().getPassword())) {
            return userOpt;
        }
        return Optional.empty();
    }
} 