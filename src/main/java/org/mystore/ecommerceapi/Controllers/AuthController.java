package org.mystore.ecommerceapi.Controllers;

import lombok.RequiredArgsConstructor;
import org.mystore.ecommerceapi.DTO.RegisterRequest;
import org.mystore.ecommerceapi.DTO.AuthResponse;
import org.mystore.ecommerceapi.DTO.LoginRequest;
import org.mystore.ecommerceapi.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/auth"})
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

//    @GetMapping
//    public ResponseEntity<?> getProducts() {
//        return ResponseEntity.ok("Access Granted: You can view products!");
//    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.registerUser(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.loginUser(loginRequest));
    }
}
