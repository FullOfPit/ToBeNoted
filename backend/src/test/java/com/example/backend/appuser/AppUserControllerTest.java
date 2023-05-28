package com.example.backend.appuser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AppUserControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    AppUserRepository appUserRepository;

    private static final AppUser TEST_STAFF_USER_DB = new AppUser(
            "testId",
            "testUsername",
            "$2a$10$G09nodAof5kOyPSMcTcy2.ebSVoInWHbS1HgpAVlXJYmeHLNBEqk2",
            "testInstitution",
            "STAFF",
            true
    );
    //$2a$10$G09nodAof5kOyPSMcTcy2.ebSVoInWHbS1HgpAVlXJYmeHLNBEqk2

    @Test
    void login_returnsUnauthorized_whenNotLoggedIn() throws Exception {
        mvc.perform(post("/api/app-users/login"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void login_returnsAppUser_WithoutPassword_WhenUserIsRegistered() throws Exception {
        this.appUserRepository.save(TEST_STAFF_USER_DB);

        mvc.perform(post("/api/app-users/login")
                        .header("Authorization", "Basic dGVzdFVzZXJuYW1lOnRlc3RQYXNzd29yZA=="))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.password").value(""));
    }

    @Test
    @WithMockUser(username = "testUsername", password = "testPassword", roles = "STAFF")
    void login_returnsAppUser_WithoutPassword_WhenUserIsLoggedIn() throws Exception {
        this.appUserRepository.save(TEST_STAFF_USER_DB);

        mvc.perform(post("/api/app-users/login"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.password").value(""));
    }

    @Test
    void me_returnsUnauthorized_whenNotLoggedIn() throws Exception {
        mvc.perform(get("/api/app-users/me"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void me_returnsAppUser_WithoutPassword_WhenUserIsRegistered() throws Exception {
        this.appUserRepository.save(TEST_STAFF_USER_DB);

        mvc.perform(get("/api/app-users/me")
                        .header("Authorization", "Basic dGVzdFVzZXJuYW1lOnRlc3RQYXNzd29yZA=="))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.password").value(""));
    }

    @Test
    void create_returnsAppUser_WithoutPassword_WhenUserIsRegistered() throws Exception {
        mvc.perform(post("/api/app-users")
                        .contentType("application/json")
                        .content("{\"username\": \"testUsername\", \"password\": \"testPassword\", \"institution\": \"testInstitution\", \"role\": \"STAFF\", \"eighteenYears\": \"true\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUsername"))
                .andExpect(jsonPath("$.password").value(""))
                .andExpect(jsonPath("$.institution").value("testInstitution"))
                .andExpect(jsonPath("$.role").value("STAFF"))
                .andExpect(jsonPath("$.eighteenYears").value("true"));
    }
}