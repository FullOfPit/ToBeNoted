package com.example.backend.appuser;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @GetMapping("/staff")
    public List<AppUser> getAllStaffMemberWithoutPassword () {
        AppUser currentUser = this.me();
        return appUserService.findStaffByInstitutionAndRoleWithoutPassword(currentUser.getInstitution());
    }

    @PostMapping("/staff")
    public AppUser createStaffMember(@RequestBody AppUser newStaffUser) {
        AppUser currentManagerUser = this.me();
        return appUserService.createNewStaffMember(newStaffUser, currentManagerUser.getInstitution());
    }

    @GetMapping("/logout")
    public void logout (HttpSession httpSession) {
        httpSession.invalidate();
    }
}
