package com.example.backend.appuser;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/app-users")
public class AppUserController {

    @PostMapping("/login")
    public Optional<AppUser> login() {
        return appUserService.findByUsername(
                SecurityContextHolder
        );
    }
}
