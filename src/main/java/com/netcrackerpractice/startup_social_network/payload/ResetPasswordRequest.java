package com.netcrackerpractice.startup_social_network.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;


public class ResetPasswordRequest {

        @NotBlank
        private UUID id;

        @NotBlank
        @Size(max = 40)
        private String token;

        @NotBlank
        @Size(min = 6, max = 20)
        private String password;

    public UUID getId() {  return id; }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
