package com.doug.socialbooknetwork.models;


import com.doug.socialbooknetwork.domain.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_users")
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String firstname;
    String lastname;
    @Column(unique = true)
    String email;
    String password;
    boolean accountLocked;
    boolean enabled;
    @Enumerated(EnumType.STRING)
    @Column( nullable = false)
    Role roles;
    @CreatedDate
    @Column(nullable = false,updatable = false)
    LocalDateTime createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    LocalDateTime lastModifiedDate;


    public   String fullName(){
        return firstname + " " + lastname;
    }
}
