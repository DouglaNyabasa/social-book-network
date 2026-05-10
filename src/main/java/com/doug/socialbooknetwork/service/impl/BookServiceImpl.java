package com.doug.socialbooknetwork.service.impl;

import com.doug.socialbooknetwork.payload.request.BookRequest;
import com.doug.socialbooknetwork.service.BookService;
import org.springframework.security.core.Authentication;

public class BookServiceImpl implements BookService {
    @Override
    public Long save(BookRequest request, Authentication connectedUser) {
        return 0L;
    }
}
