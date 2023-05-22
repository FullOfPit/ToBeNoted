package com.example.backend.appuser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AppUserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AppUserRepository appUserRepository;

    private static final AppUser TEST_USER = new AppUser(
            "testId",
            "testUsername",
            "testPassword",
            "testInstitution",
            "Staff",
            true);

    @Test
    @WithMockUser(username = "testUsername", password = "testPassword")
    void login_ReturnUsername_WhenUserRegistered() throws Exception {

        appUserRepository.save(TEST_USER);

        this.mvc.perform(post("/api/app-users/login"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """ 
                        {
                        "username": "testUsername"
                        }
                        """
                ));
    }

    @Test
    @WithMockUser(username = "testUsername", password = "testPassword")
    void login_Returns404_WhenUserNotRegistered() throws Exception {

        this.mvc.perform(post("/api/app-users/login"))
                .andExpect(status().isNotFound());
    }
}