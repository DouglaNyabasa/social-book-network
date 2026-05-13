package com.doug.socialbooknetwork.payload.response;


import com.doug.socialbooknetwork.payload.request.RegistrationRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthResponse {
    String token;
    private String message;
    private RegistrationRequest user;
    private Boolean accountVerified;
}