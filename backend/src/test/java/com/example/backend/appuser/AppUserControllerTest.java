package com.example.backend.appuser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
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

    private static final AppUser TEST_STAFF_USER_DB = new AppUser(
            "testId",
            "testUsername",
            "$2a$10$SyB968cGnwV474QXFOkyEe88fEN8VSw7EIvdefO7sVmj0vJikFjW.",
            //password = "pw1"
            "testInstitution",
            "STAFF",
            true
    );

    private static final AppUser TEST_ADMIN_USER_DB = new AppUser(
            "testId",
            "testUsername",
            "$2a$10$G09nodAof5kOyPSMcTcy2.ebSVoInWHbS1HgpAVlXJYmeHLNBEqk2",
            //password = "password"
            "testInstitution",
            "ADMIN",
            true
    );

    @Test
    void login_returnsUnauthorized_whenNotLoggedIn() throws Exception {
        mvc.perform(post("/api/app-users/login"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void login_returnsAppUser_WithoutPassword_WhenUserIsRegistered() throws Exception {
        this.appUserRepository.save(TEST_STAFF_USER_DB);

        mvc.perform(post("/api/app-users/login")
                        .header("Authorization", "Basic dGVzdFVzZXJuYW1lOnB3MQ=="))
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
                        .header("Authorization", "Basic dGVzdFVzZXJuYW1lOnB3MQ=="))
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

    @Test
    void create_ReturnsUsers_WhenUserOfDifferentRolesCreatedByAdmin() throws Exception {
        this.appUserRepository.save(TEST_ADMIN_USER_DB);

        mvc.perform(post("/api/app-users")
                        .header("Authorization", "Basic dGVzdFVzZXJuYW1lOnRlc3RQYXNzd29yZA==")
                        .contentType("application/json")
                        .content("{\"role\": \"ADMIN\", \"username\": \"NewTestUser\", \"password\": \"NewTestPassword\", \"institution\": \"testInstitution\", \"eighteenYears\": \"true\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("NewTestUser"))
                .andExpect(jsonPath("$.password").value(""))
                .andExpect(jsonPath("$.institution").value("testInstitution"))
                .andExpect(jsonPath("$.role").value("ADMIN"))
                .andExpect(jsonPath("$.eighteenYears").value("true"));

        mvc.perform(post("/api/app-users")
                        .header("Authorization", "Basic dGVzdFVzZXJuYW1lOnRlc3RQYXNzd29yZA==")
                        .contentType("application/json")
                        .content("{ \"role\": \"STAFF\", \"username\": \"NewTestUser2\", \"password\": \"NewTestPassword2\", \"institution\": \"testInstitution\", \"eighteenYears\": \"true\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("NewTestUser2"))
                .andExpect(jsonPath("$.password").value(""))
                .andExpect(jsonPath("$.institution").value("testInstitution"))
                .andExpect(jsonPath("$.role").value("STAFF"))
                .andExpect(jsonPath("$.eighteenYears").value("true"));
    }

    //TODO: GetAllStaffMembersWithoutPassword - check for roles
    //TODO: DeleteById - check for admin, check for staff (FORBIDDEN), check for non-existing user (NOT_FOUND)
    /*
    @Test
    @WithMockUser(roles = {"ADMIN"})
    void create_ReturnsUsers_WhenUserOfDifferentRolesCreatedByAdmin_WithMockUser() throws Exception {

        mvc.perform(post("/api/app-users")
                        .contentType("application/json")
                        .content("{\"role\": \"ADMIN\", \"username\": \"NewTestUser\", \"password\": \"NewTestPassword\", \"institution\": \"testInstitution\", \"eighteenYears\": \"true\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("NewTestUser"))
                .andExpect(jsonPath("$.password").value(""))
                .andExpect(jsonPath("$.institution").value("testInstitution"))
                .andExpect(jsonPath("$.role").value("ADMIN"))
                .andExpect(jsonPath("$.eighteenYears").value("true"));
    }

     */

    @Test
    void create_ReturnsConflict_WhenUserAlreadyRegistered() throws Exception {
        this.appUserRepository.save(TEST_STAFF_USER_DB);
        this.appUserRepository.save(TEST_ADMIN_USER_DB);

        mvc.perform(post("/api/app-users")
                        .contentType("application/json")
                        .content("{\"role\": \"STAFF\", \"username\": \"testUsername\", \"password\": \"testPassword\", \"institution\": \"testInstitution\", \"eighteenYears\": \"true\"}"))
                .andExpect(status().isConflict());

        mvc.perform(post("/api/app-users")
                        .header("Authorization", "Basic dGVzdFVzZXJuYW1lOnB3MQ==")
                        .contentType("application/json")
                        .content("{\"role\": \"STAFF\", \"username\": \"testUsername\", \"password\": \"testPassword\", \"institution\": \"testInstitution\", \"eighteenYears\": \"true\"}"))
                .andExpect(status().isConflict());

        mvc.perform(post("/api/app-users")
                        .header("Authorization", "Basic dGVzdFVzZXJuYW1lOnRlc3RQYXNzd29yZA==")
                        .contentType("application/json")
                        .content("{\"role\": \"ADMIN\", \"username\": \"testUsername\", \"password\": \"testPassword\", \"institution\": \"testInstitution\", \"eighteenYears\": \"true\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    void getAllStaffMembersWithoutPassword_ReturnsListOfStaffMembersByInstitution_WhenRequestedByManager() throws Exception {
        AppUser testStaffOne = new AppUser("1", "testStaffOne", "testPassword", "testInstitution", "STAFF", true);
        AppUser testStaffTwo = new AppUser("2", "testStaffTwo", "testPassword", "testInstitutionTwo", "STAFF", true);

        this.appUserRepository.save(testStaffOne);
        this.appUserRepository.save(testStaffTwo);
        this.appUserRepository.save(TEST_ADMIN_USER_DB);

        mvc.perform(get("/api/app-users/staff")
                .header("Authorization", "Basic dGVzdFVzZXJuYW1lOnRlc3RQYXNzd29yZA=="))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].username").value("testStaffOne"))
                .andExpect(jsonPath("$[0].password").value(""));
    }

    @Test
    void deleteById_ReturnsNoContent_WhenUserIsDeleted() throws Exception {
        AppUser testStaffOne = new AppUser("1", "testStaffOne", "testPassword", "testInstitution", "STAFF", true);

        this.appUserRepository.save(testStaffOne);
        this.appUserRepository.save(TEST_ADMIN_USER_DB);

        //Delete by admin
        mvc.perform(delete("/api/app-users/1")
                        .header("Authorization", "Basic dGVzdFVzZXJuYW1lOnRlc3RQYXNzd29yZA=="))
                .andExpect(status().isOk());

        mvc.perform(get("/api/app-users/staff")
                        .header("Authorization", "Basic dGVzdFVzZXJuYW1lOnRlc3RQYXNzd29yZA=="))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        mvc.perform(delete("/api/app-users/2")
                        .header("Authorization", "Basic dGVzdFVzZXJuYW1lOnRlc3RQYXNzd29yZA=="))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteById_ReturnsForbidden_WhenStaffUserUsed() throws Exception {
        AppUser testStaffOne = new AppUser("1", "testStaffOne", "testPassword", "testInstitution", "STAFF", true);

        this.appUserRepository.save(testStaffOne);
        this.appUserRepository.save(TEST_STAFF_USER_DB);

        mvc.perform(delete("/api/app-users/1")
                        .header("Authorization", "Basic dGVzdFVzZXJuYW1lOnB3MQ=="))
                .andExpect(status().isForbidden());
    }

}