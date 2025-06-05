package com.beearena.controller;

import com.beearena.entity.User;
import com.beearena.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> body) {
        boolean success = userService.register(body.get("email"), body.get("password"), body.getOrDefault("name", ""));
        return Map.of("success", success, "message", success ? "Đăng ký thành công!" : "Email đã tồn tại!");
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        Optional<User> user = userService.login(body.get("email"), body.get("password"));
        if (user.isPresent()) {
            return Map.of("success", true, "message", "Đăng nhập thành công!", "user", user.get());
        } else {
            return Map.of("success", false, "message", "Sai email hoặc mật khẩu!");
        }
    }
} 