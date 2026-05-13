package com.doug.socialbooknetwork.models;


import com.doug.socialbooknetwork.common.BaseEntity;
import com.doug.socialbooknetwork.domain.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {


    String firstname;
    String lastname;
    @Column(unique = true)
    String email;
    String password;
    @Enumerated(EnumType.STRING)
    @Column( nullable = false)
    Role roles;
    @OneToMany(mappedBy = "owner")
    List<Book> books;


    @OneToMany(mappedBy = "user")
    List<BookTransactionHistory> histories;



    public   String fullName(){
        return firstname + " " + lastname;
    }
}
