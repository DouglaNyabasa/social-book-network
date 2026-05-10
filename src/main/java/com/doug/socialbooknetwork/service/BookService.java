package com.doug.socialbooknetwork.service;

import com.doug.socialbooknetwork.payload.request.BookRequest;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;

public interface BookService {
    Long save(@Valid BookRequest request, Authentication connectedUser);
}
