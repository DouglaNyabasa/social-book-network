package com.doug.socialbooknetwork.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AuthRequest {
    @Email(message = "Email not properly formated")
    @NotEmpty(message = "Email required")
    @NotBlank(message = "Email required")
    String email;
    @Size(min = 6,message = "Password should be at-least 6 characters minimum")
    @NotEmpty(message = "Password required")
    @NotBlank(message = "Password required")
    String password;
}
