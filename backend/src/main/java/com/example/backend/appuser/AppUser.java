package com.example.backend.appuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    String id;
    String username;
    String password;
    String institution;
    String role;
    boolean eighteenYears;



}
