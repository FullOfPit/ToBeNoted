package com.example.backend.appuser;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AppUserServiceTest {

    private static final AppUserRepository appUserRepository = mock(AppUserRepository.class);

    @AfterEach
    void tearDown () {
        reset(appUserRepository);
    }
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
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String BASIC_ROLE = "STAFF";


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
        try {
            AppUser actual = appUserService.create(TEST_USER);
            Assertions.fail();
        } catch (ResponseStatusException exception) {
            Assertions.assertEquals(HttpStatus.CONFLICT, exception.getStatus());
        }
    }

    @Test
    void create_CreatesBasicUser_IfNotAuthenticationReturnsNull() {
        //Given
        SecurityContext securityContext = mock(SecurityContext.class);

        when(appUserRepository.findByUsername("testUsername")).thenReturn(Optional.empty());
        when(encoder.encode(any())).thenAnswer(invocation -> invocation.getArgument(0));
        when(securityContext.getAuthentication()).thenReturn(null);
        SecurityContextHolder.setContext(securityContext);
        //When
        AppUser appUser = this.appUserService.create(TEST_USER);
        //Then
        Assertions.assertEquals(BASIC_ROLE, appUser.getRole());
        Assertions.assertEquals("", appUser.getPassword());

    }

    @Test
    void create_CreatesBasicUser_IfUserIsNotAuthenticated() {
        //Given
        SecurityContext securityContext = mock(SecurityContext.class);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                " ",
                " ",
                List.of(new SimpleGrantedAuthority("ADMIN"))
        );
        authentication.setAuthenticated(false);

        when(appUserRepository.findByUsername("testUsername")).thenReturn(Optional.empty());
        when(encoder.encode(any())).thenAnswer(invocation -> invocation.getArgument(0));
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        //When
        AppUser appUser = this.appUserService.create(TEST_USER);
        //Then
        Assertions.assertEquals(BASIC_ROLE, appUser.getRole());
        Assertions.assertEquals("", appUser.getPassword());

    }

    @Test
    void create_CreatesBasicUser_IfAuthenticatedUserIsNotAdmin() {
        //Given
        SecurityContext securityContext = mock(SecurityContext.class);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                " ",
                " ",
                List.of(new SimpleGrantedAuthority("NOT_ADMIN"))
        );
        when(appUserRepository.findByUsername("testUsername")).thenReturn(Optional.empty());
        when(encoder.encode(any())).thenAnswer(invocation -> invocation.getArgument(0));
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        //When
        AppUser appUser = this.appUserService.create(TEST_USER);
        //Then
        Assertions.assertEquals(BASIC_ROLE, appUser.getRole());
        Assertions.assertEquals("", appUser.getPassword());

    }

    @Test
    void create_CanCreateAdminUser_IfAdminIsUsed() {
        //Given
        SecurityContext securityContext = mock(SecurityContext.class);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                TEST_USER.getUsername(),
                TEST_USER.getPassword(),
                List.of(new SimpleGrantedAuthority(ADMIN_ROLE))
        );

        when(appUserRepository.findByUsername("testUsername")).thenReturn(Optional.empty());
        when(encoder.encode(any())).thenAnswer(invocation -> invocation.getArgument(0));
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        //When
        AppUser appUser = this.appUserService.create(TEST_USER);
        //Then
        Assertions.assertEquals(ADMIN_ROLE, appUser.getRole());
        Assertions.assertEquals("", appUser.getPassword());
    }


}