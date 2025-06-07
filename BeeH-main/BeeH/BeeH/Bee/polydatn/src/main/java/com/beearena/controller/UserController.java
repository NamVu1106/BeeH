package com.beearena.controller;

import com.beearena.entity.User;
import com.beearena.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/update")
    public Map<String, Object> updateUser(@RequestBody Map<String, String> body) {
        Long id = null;
        try { id = Long.parseLong(body.get("id")); } catch (Exception e) { return Map.of("success", false, "message", "ID không hợp lệ!"); }
        Optional<User> opt = userRepository.findById(id);
        if (opt.isEmpty()) return Map.of("success", false, "message", "Không tìm thấy user!");
        User user = opt.get();
        String newName = body.getOrDefault("name", user.getName());
        String newEmail = body.getOrDefault("email", user.getEmail());
        String newPassword = body.getOrDefault("password", "");
        // Kiểm tra email đã tồn tại cho user khác chưa
        Optional<User> emailUser = userRepository.findByEmail(newEmail);
        if (emailUser.isPresent() && !emailUser.get().getMaND().equals(user.getMaND())) {
            return Map.of("success", false, "message", "Email đã được sử dụng!");
        }
        user.setName(newName);
        user.setEmail(newEmail);
        if (newPassword != null && !newPassword.isBlank()) user.setPassword(newPassword);
        userRepository.save(user);
        return Map.of("success", true, "user", user);
    }
} 