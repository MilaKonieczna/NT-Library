package com.example.library.service.auth;

import com.example.library.controller.auth.dto.login.LoginDto;
import com.example.library.controller.auth.dto.login.LoginResponseDto;
import com.example.library.controller.auth.dto.register.RegisterDto;
import com.example.library.controller.auth.dto.register.RegisterResponseDto;
import com.example.library.infrastructure.entity.AuthEntity;
import com.example.library.infrastructure.entity.UserEntity;
import com.example.library.infrastructure.repository.AuthRepository;
import com.example.library.infrastructure.repository.UserRepository;
import com.example.library.service.auth.error.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private  final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }
@Transactional
    public RegisterResponseDto register(RegisterDto dto){

        Optional<AuthEntity> existingAuth = authRepository.findByUsername(dto.getUsername());

        if(existingAuth.isPresent()) {
            throw  UserAlreadyExistsException.create(dto.getUsername());}

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());
        userRepository.save(userEntity);

        AuthEntity authEntity = new AuthEntity();
        String hashPassword = passwordEncoder.encode(dto.getPassword());
        authEntity.setPassword(hashPassword);
        authEntity.setUsername(dto.getUsername());
        authEntity.setRole(dto.getRole());
        authEntity.setUser(userEntity);

        authRepository.save(authEntity);
        return new RegisterResponseDto(userEntity.getId(), authEntity.getUsername(), authEntity.getRole());
    }
    public LoginResponseDto logIn(LoginDto dto){
        AuthEntity authEntity = authRepository.findByUsername(dto.getUsername()).orElseThrow(()-> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(dto.getPassword(), authEntity.getPassword())){
            throw new RuntimeException();
        }

        String token = jwtService.generateToken(authEntity);

        return new LoginResponseDto(token);
    }

}
