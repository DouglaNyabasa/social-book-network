package com.doug.socialbooknetwork.service.impl;


import com.doug.socialbooknetwork.models.User;
import com.doug.socialbooknetwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User existingUser = userRepository.existsByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found: " + email));

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + existingUser.getRoles().name());
        Collection<GrantedAuthority> authorities = Collections.singletonList(authority);

        return new org.springframework.security.core.userdetails.User(
                existingUser.getEmail(),
                existingUser.getPassword(),
                authorities
        );
    }
}
