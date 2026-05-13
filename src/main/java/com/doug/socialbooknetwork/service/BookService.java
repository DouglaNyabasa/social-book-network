package com.doug.socialbooknetwork.service;

import com.doug.socialbooknetwork.models.Book;
import com.doug.socialbooknetwork.payload.request.BookRequest;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;

public interface BookService {
    Book save(@Valid BookRequest request, Long userId);
}
