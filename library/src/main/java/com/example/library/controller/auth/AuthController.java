package com.example.library.controller.auth;

import com.example.library.controller.auth.dto.login.LoginDto;
import com.example.library.controller.auth.dto.login.LoginResponseDto;
import com.example.library.controller.auth.dto.register.RegisterDto;
import com.example.library.controller.auth.dto.register.RegisterResponseDto;
import com.example.library.service.auth.AuthService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Register Succeeded"),
            @ApiResponse(responseCode = "401", description = "Register Failed", content = @Content)})
    public ResponseEntity<RegisterResponseDto> register(@Validated @RequestBody RegisterDto requestBody) {
        RegisterResponseDto dto = authService.register(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Login Succeeded"),
            @ApiResponse(responseCode = "401", description = "Login Failed", content = @Content)})
    @SecurityRequirements
    public ResponseEntity<LoginResponseDto> login(@Validated@RequestBody LoginDto requestBody){
        LoginResponseDto dto = authService.login(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);}
}

