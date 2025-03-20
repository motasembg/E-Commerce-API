package org.mystore.ecommerceapi.service;
import lombok.RequiredArgsConstructor;
import org.mystore.ecommerceapi.DTO.AuthResponse;
import org.mystore.ecommerceapi.DTO.LoginRequest;
import org.mystore.ecommerceapi.DTO.RegisterRequest;
import org.mystore.ecommerceapi.DatabaseTables.userRole;
import org.mystore.ecommerceapi.DatabaseTables.Users;
import org.mystore.ecommerceapi.Repositories.usersRepository;
import org.mystore.ecommerceapi.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;


import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final usersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public String registerUser(RegisterRequest registerRequest) {
        Optional<Users> existingUser = usersRepository.findByEmail(registerRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        Users user = Users.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(userRole.valueOf(registerRequest.getRole().toUpperCase()))
                .build();
        usersRepository.save(user);
        return "User registered successfully!";
    }
    public AuthResponse loginUser(LoginRequest loginRequest) {
        log.info("Login attempt for email: {}", loginRequest.getEmail());

        Users user = usersRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> {
                    log.error("Login failed: User not found with email {}", loginRequest.getEmail());
                    return new RuntimeException("Invalid credentials");
                });
        log.info("User found in database: {}", user.getEmail());

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            log.error("Login failed: Incorrect password for email {}", loginRequest.getEmail());
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtUtil.generateJwtToken(user.getEmail());
        log.info("User {} logged in successfully. Token generated.", user.getEmail());
        return new AuthResponse(token);
    }
}
