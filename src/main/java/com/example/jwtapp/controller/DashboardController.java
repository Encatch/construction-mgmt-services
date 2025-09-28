package com.example.jwtapp.controller;

import com.example.jwtapp.model.User;
import com.example.jwtapp.repository.UserRepository;
import com.example.jwtapp.security.PermissionChecker;
import com.example.jwtapp.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<?> getDashboard(Authentication auth) {
        User user = userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!PermissionChecker.hasPermission(user, "ProjectDashboard", "view")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }

        return ResponseEntity.ok(dashboardService.getData());
    }
}

