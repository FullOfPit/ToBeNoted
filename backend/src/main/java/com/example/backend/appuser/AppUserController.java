package com.example.backend.appuser;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/app-users")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @PostMapping("/login")
    public AppUser login() {
        return this.me();
    }

    @GetMapping("/me")
    public AppUser me() {
        return appUserService.findByUsernameWithoutPassword(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );
    }

    @PostMapping
    public AppUser create(@RequestBody AppUser appUser) {
        return this.appUserService.create(appUser);
    }
}
