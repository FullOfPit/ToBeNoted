package com.example.backend.appuser;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String ADMIN_ROLE = "ADMIN";
    private static final String BASIC_ROLE = "STAFF";

    public AppUser findByUsernameWithoutPassword(String username) {
        AppUser appUser = this.findByUsername(username);
        appUser.setPassword("");
        return appUser;
    }

    public AppUser findByUsername(String username) {

        return this.appUserRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /*
    @Todo
    Analyze possible overlap of this create() and createNewStaffMember()
    Streamline into one method, that decided whether to create a StaffMember or not and then calls a method?
    */


    public AppUser create(AppUser appUser) {

        if (this.appUserRepository.findByUsername(appUser.getUsername()).isPresent()) {
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
                        .noneMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_" + ADMIN_ROLE))
        ) {
            appUser.setRole(BASIC_ROLE);
        }

        this.appUserRepository.save(appUser);

        appUser.setPassword("");

        return appUser;
    }

    public List<AppUser> findBasicRoleUserByInstitutionAndRoleWithoutPassword(String institution) {
        List<AppUser> userList = this.appUserRepository.findAllByInstitutionAndRole(institution, BASIC_ROLE);
        userList.forEach(user -> user.setPassword(""));
        return userList;
    }

    public AppUser createNewStaffMember(AppUser newStaffUser, String institution) {
        newStaffUser.setInstitution(institution);
        newStaffUser.setRole(BASIC_ROLE);
        newStaffUser.setPassword("");
        return this.appUserRepository.save(newStaffUser);
    }

    public void deleteStaffMemberById(String id) {

        if (
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getAuthorities()
                        .stream()
                        .noneMatch(authority -> authority.getAuthority().equals(ADMIN_ROLE))
        ) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        if (!this.appUserRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        this.appUserRepository.deleteById(id);
    }
}
