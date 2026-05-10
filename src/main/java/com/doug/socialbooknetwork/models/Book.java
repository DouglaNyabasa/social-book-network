package com.doug.socialbooknetwork.models;

import com.doug.socialbooknetwork.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book extends BaseEntity {


     String title;
     String authorName;
     String isbn;
     String synopsis;
     String bookCover;
     boolean archived;
     boolean shareable;

     @ManyToOne
     @JoinColumn(name = "owner_id")
     User owner;

     @OneToMany(mappedBy = "book")
     List<Feedback> feedbacks;

     @OneToMany(mappedBy = "book")
     List<BookTransactionHistory> histories;


}
