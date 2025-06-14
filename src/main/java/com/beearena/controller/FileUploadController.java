package com.beearena.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {
    @PostMapping
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String uploadDir = "uploads/";
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File dest = new File(uploadDir + fileName);
            dest.getParentFile().mkdirs();
            file.transferTo(dest);
            String fileUrl = "/uploads/" + fileName;
            return Map.of("success", true, "url", fileUrl);
        } catch (Exception e) {
            return Map.of("success", false, "message", e.getMessage());
        }
    }
} 