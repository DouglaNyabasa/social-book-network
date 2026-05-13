package com.doug.socialbooknetwork.mapper;

import com.doug.socialbooknetwork.models.Book;
import com.doug.socialbooknetwork.payload.request.BookRequest;
import org.springframework.stereotype.Component;

@Component

public class BookMapper {


    public Book toBook(BookRequest request) {
       return   Book.builder()
                .title(request.title())
                .authorName(request.authorName())
                .synopsis(request.synopsis())
                .archived(false)
                .shareable(request.shareable())
                .build();
    }
}
