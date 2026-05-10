package com.doug.socialbooknetwork.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "feedback")
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    Double note;
    String comment;
    @CreatedDate
    @Column(nullable = false,updatable = false)
    LocalDateTime createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    LocalDateTime lastModifiedDate;
    @CreatedBy
    @Column(nullable = false,updatable = false)
    Long createdBy;
    @LastModifiedBy
    @Column(insertable = false)
    Long lastModifiedBy;



}
