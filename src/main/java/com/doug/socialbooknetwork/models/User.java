package com.doug.socialbooknetwork.models;


import com.doug.socialbooknetwork.domain.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_users")
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User  implements UserDetails, Principal {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String firstname;
    String lastname;
    LocalDate dateOfBirth;
    @Column(unique = true)
    String email;
    String password;
    boolean accountLocked;
    boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER)
    List<Role> roles;
    @CreatedDate
    @Column(nullable = false,updatable = false)
    LocalDateTime createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    LocalDateTime lastModifiedDate;




    @Override
    public String getName() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(r-> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public   String fullName(){
        return firstname + " " + lastname;
    }
}
