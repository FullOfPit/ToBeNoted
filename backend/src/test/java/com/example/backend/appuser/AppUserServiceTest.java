package com.example.backend.appuser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class AppUserServiceTest {

    private final AppUserRepository appUserRepository = mock(AppUserRepository.class);
    private final PasswordEncoder encoder = mock(PasswordEncoder.class);

    private final AppUserService appUserService = new AppUserService(appUserRepository, encoder);

    private static final AppUser TEST_USER = new AppUser(
            "testId",
            "testUsername",
            "testPassword",
            "testInstitution",
            "Staff",
            true);

    @Test
    void findByUsername_ReturnCorrectUsername_WhenUsernameFound() {
        //Given
        when(appUserRepository.findByUsername("testUsername")).thenReturn(Optional.of(TEST_USER));
        //When
        AppUser actual = appUserService.findByUsername("testUsername");
        //Then
        Assertions.assertEquals(TEST_USER, actual);
    }

    @Test
    void findByUsername_ThrowsResponseStatusException_NotFound_WhenUsernameNotFound() {
        //Given
        when(appUserRepository.findByUsername("testUsername")).thenReturn(Optional.empty());
        //When - Then
        Assertions.assertThrows(ResponseStatusException.class,
                () -> appUserService.findByUsername("testUsername"));
    }
}