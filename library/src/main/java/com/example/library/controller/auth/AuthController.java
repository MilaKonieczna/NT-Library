package com.example.library.controller.auth;

import com.example.library.controller.auth.dto.login.LoginDto;
import com.example.library.controller.auth.dto.login.LoginResponseDto;
import com.example.library.controller.auth.dto.register.RegisterDto;
import com.example.library.controller.auth.dto.register.RegisterResponseDto;
import com.example.library.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@PreAuthorize("hasRole('ADMIN')")
public class AuthController {
    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@Validated  @RequestBody RegisterDto requestBody){
        RegisterResponseDto dto = authService.register(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto requestBody){
        LoginResponseDto dto =  authService.logIn(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
