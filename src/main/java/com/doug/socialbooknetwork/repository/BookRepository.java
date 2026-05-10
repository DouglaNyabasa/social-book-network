package com.doug.socialbooknetwork.repository;

import com.doug.socialbooknetwork.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
