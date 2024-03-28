package com.gta.cars.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gta.cars.dto.LoginResponseDTO;
import com.gta.cars.dto.RegisterResponseDTO;
import com.gta.cars.dto.UserLoginDTO;
import com.gta.cars.dto.UserRegisterDTO;
import com.gta.cars.model.User;
import com.gta.cars.repository.UserRepository;
import com.gta.cars.security.TokenService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;
    
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserLoginDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody @Valid UserRegisterDTO dto) {
        if (this.userRepository.findByLogin(dto.login()) != null)
            return ResponseEntity.badRequest().build();
        
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        User newUser = new User(dto.login(), encryptedPassword, dto.nome(), dto.email());
        this.userRepository.save(newUser);
            
        return ResponseEntity.ok().body(new RegisterResponseDTO(newUser.getId(), newUser.getNome()));
    }
    
}
