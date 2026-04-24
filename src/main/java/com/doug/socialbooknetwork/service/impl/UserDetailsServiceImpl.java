package com.doug.socialbooknetwork.service.impl;

import com.doug.socialbooknetwork.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String emailEmail) throws UsernameNotFoundException {
        return userRepository.findByEmail(emailEmail).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}
