package com.doug.socialbooknetwork.payload.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ApiResponse {

    int status;
    String message;
    String timeStamp;
}
