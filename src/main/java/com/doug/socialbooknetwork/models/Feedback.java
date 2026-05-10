package com.doug.socialbooknetwork.models;

import com.doug.socialbooknetwork.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@SuperBuilder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "feedback")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Feedback extends BaseEntity {


    Double note;
    String comment;

    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;




}
