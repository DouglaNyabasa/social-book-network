package com.doug.socialbooknetwork.service.impl;

import com.doug.socialbooknetwork.mapper.BookMapper;
import com.doug.socialbooknetwork.models.Book;
import com.doug.socialbooknetwork.models.User;
import com.doug.socialbooknetwork.payload.request.BookRequest;
import com.doug.socialbooknetwork.repository.BookRepository;
import com.doug.socialbooknetwork.repository.UserRepository;
import com.doug.socialbooknetwork.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final UserRepository userRepository;
    @Override
    public Book save(BookRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookMapper.toBook(request);
        book.setOwner(user);
        return bookRepository.save(book);
    }
}
