package com.doug.socialbooknetwork.payload.request;

import com.doug.socialbooknetwork.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationRequest {


    @NotEmpty(message = "FirstName required")
    @NotBlank(message = "FirstName required")
    String firstname;
    @NotEmpty(message = "LastName required")
    @NotBlank(message = "LastName required")
    String lastname;
    @Email(message = "Email not properly formated")
    @NotEmpty(message = "Email required")
    @NotBlank(message = "Email required")
    String email;
    @Size(min = 6,message = "Password should be at-least 6 characters minimum")
    @NotEmpty(message = "Password required")
    @NotBlank(message = "Password required")
    String password;
    Role roles;

}
