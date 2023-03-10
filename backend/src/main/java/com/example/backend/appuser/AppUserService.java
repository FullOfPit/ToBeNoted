package com.example.backend.appuser;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<AppUser> findByUsername(String username) {

        return this.appUserRepository.findByUsername(username);
    }

    public AppUser create(AppUser appUser) {

        Optional<AppUser> existingUser = findByUsername(
                appUser.getUsername()
        );

        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));

        if (
                SecurityContextHolder
                        .getContext()
                        .getAuthentication() == null
                        ||
                !SecurityContextHolder.getContext()
                        .getAuthentication()
                        .isAuthenticated()
                        ||
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getAuthorities()
                        .stream()
                        .noneMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN"))
        ) {
            appUser.setRole("BASIC");
        }

        this.appUserRepository.save(appUser);

        appUser.setPassword("");

        return appUser;
    }

    
}
