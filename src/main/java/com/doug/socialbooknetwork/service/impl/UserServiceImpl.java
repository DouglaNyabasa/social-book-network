package com.doug.socialbooknetwork.service.impl;

import com.doug.socialbooknetwork.domain.EmailTemplate;
import com.doug.socialbooknetwork.exceptionHandling.UserAlreadyExistsException;
import com.doug.socialbooknetwork.models.Token;
import com.doug.socialbooknetwork.models.User;
import com.doug.socialbooknetwork.payload.request.AuthRequest;
import com.doug.socialbooknetwork.payload.request.RegistrationRequest;
import com.doug.socialbooknetwork.payload.request.UserMapper;
import com.doug.socialbooknetwork.payload.response.AuthResponse;
import com.doug.socialbooknetwork.repository.TokenRepository;
import com.doug.socialbooknetwork.repository.UserRepository;
import com.doug.socialbooknetwork.service.UserService;
import com.doug.socialbooknetwork.utils.JwtUtil;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;




    @Override
    public void register(@Valid RegistrationRequest request) throws MessagingException {

        if (userRepository.existsByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User: %s already exists".formatted(request.getEmail()));
        }
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(request.getRoles())
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
    }

    @Override
    public void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);
        String activationUrl = "http://localhost:5173/activate-account";
        emailService.sendEmail(
                user.getEmail(),
                user.fullName(),
                EmailTemplate.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,"Account Activation"
        );

    }

    @Override
    public String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    @Override
    public String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i= 0; i< length; i++){
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }

    @Override
    public AuthResponse login(AuthRequest request) throws Exception {
        String email = request.getEmail();
        String password = request.getPassword();
        Authentication authentication = authenticate(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.iterator().next().getAuthority();
        String jwt = jwtUtil.generateToken(authentication);
        User user = userRepository.existsByEmail(email)
                .orElseThrow(() -> new Exception("User not found with email: " + email));
        userRepository.save(user);


        return AuthResponse.builder()
                .token(jwt)
                .message("Login Successful")
                .user(UserMapper.toDTO(user))
                .build();
    }

    public Authentication authenticate(String email, String password) {
        try {
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        }catch (Exception e){
            throw new BadCredentialsException("Invalid email or password");
        }
    }


}
