package com.example.backend.appuser;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/app-users")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @PostMapping("/login")
    public ResponseEntity<AppUser> login() {
        try {
            return new ResponseEntity<>(
                    this.appUserService.findByUsernameWithoutPassword(
                            SecurityContextHolder.getContext().getAuthentication().getName()), HttpStatus.OK);
        } catch (ResponseStatusException exception) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping("/me")
    public AppUser me() {
        return this.appUserService.findByUsernameWithoutPassword(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );
    }

    @PostMapping
    public AppUser create(@RequestBody AppUser appUser) {
        return this.appUserService.create(appUser);
    }

    @GetMapping("/staff")
    public List<AppUser> getAllStaffMemberWithoutPassword () {
        AppUser currentUser = this.me();
        return this.appUserService.findStaffByInstitutionAndRoleWithoutPassword(currentUser.getInstitution());
    }

    @PostMapping("/staff")
    public AppUser createStaffMember(@RequestBody AppUser newStaffUser) {
        AppUser currentManagerUser = this.me();
        return this.appUserService.createNewStaffMember(newStaffUser, currentManagerUser.getInstitution());
    }

    @DeleteMapping("/staff/{id}")
    public void deleteStaffMemberById(@PathVariable String id) {
        AppUser currentManagerUser = this.me();
        this.appUserService.deleteStaffMemberById(currentManagerUser, id);
    }

    @GetMapping("/logout")
    public void logout (HttpSession httpSession) {
        httpSession.invalidate();
    }
}
