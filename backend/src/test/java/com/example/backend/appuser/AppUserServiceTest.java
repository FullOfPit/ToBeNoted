package com.example.backend.appuser;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppUserServiceTest {

    private static final AppUserRepository appUserRepository = mock(AppUserRepository.class);
    private static final PasswordEncoder encoder = mock(PasswordEncoder.class);

    private final AppUserService appUserService = new AppUserService(appUserRepository, encoder);

    private static final AppUser TEST_USER = new AppUser(
            "testId",
            "testUsername",
            "testPassword",
            "testInstitution",
            "testRole",
            true
    );

    @Test
    void findByUsernameWithoutPassword_ReturnsNoPassword_ReturnsAllOtherFields_WhenUserRegistered() {
        //Given
        when(appUserRepository.findByUsername("testUsername")).thenReturn(Optional.of(TEST_USER));
        //When
        AppUser actual = appUserService.findByUsernameWithoutPassword("testUsername");
        //Then
        Assertions.assertEquals(TEST_USER.getId(), actual.getId());
        Assertions.assertEquals(TEST_USER.getUsername(), actual.getUsername());
        Assertions.assertEquals("", actual.getPassword());
        Assertions.assertEquals(TEST_USER.getInstitution(), actual.getInstitution());
        Assertions.assertEquals(TEST_USER.getRole(), actual.getRole());
        Assertions.assertEquals(TEST_USER.isEighteenYears(), actual.isEighteenYears());

        verify(appUserRepository).findByUsername("testUsername");
    }

    @Test
    void findByUsername_ReturnsAllFields_WhenUserRegistered() {
        //Given
        when(appUserRepository.findByUsername("testUsername")).thenReturn(Optional.of(TEST_USER));
        //When
        AppUser actual = appUserService.findByUsernameWithoutPassword(TEST_USER.getUsername());
        //Then
        Assertions.assertEquals(TEST_USER.getId(), actual.getId());
        Assertions.assertEquals(TEST_USER.getUsername(), actual.getUsername());
        Assertions.assertEquals(TEST_USER.getPassword(), actual.getPassword());
        Assertions.assertEquals(TEST_USER.getInstitution(), actual.getInstitution());
        Assertions.assertEquals(TEST_USER.getRole(), actual.getRole());
        Assertions.assertEquals(TEST_USER.isEighteenYears(), actual.isEighteenYears());

        verify(appUserRepository).findByUsername("testUsername");
    }

    @Test
    void findByUsernameWithoutPassword_ThrowsResponseStatusException_WhenUsernameNotFound() {
        //Given
        when(appUserRepository.findByUsername("testUsername")).thenReturn(Optional.empty());
        //When - Then
        Assertions.assertThrows(ResponseStatusException.class, () -> appUserService.findByUsernameWithoutPassword("testUsername"));
    }
    @Test
    void findByUsername_ThrowsResponseStatusException_WhenUsernameNotFound() {
        //Given
        when(appUserRepository.findByUsername("testUsername")).thenReturn(Optional.empty());
        //When - Then
        Assertions.assertThrows(ResponseStatusException.class, () -> appUserService.findByUsername("testUsername"));
    }

    @Test
    void create_ThrowsResponseStatusException_WhenSavingUser_WhenUserAlreadyRegistered() {
        //Given
        when(appUserRepository.findByUsername(TEST_USER.getUsername())).thenReturn(Optional.of(TEST_USER));
        //When - Then
        Assertions.assertThrows(ResponseStatusException.class, () -> appUserService.create(TEST_USER));
    }

}