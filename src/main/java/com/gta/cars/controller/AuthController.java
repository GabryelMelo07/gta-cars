package com.gta.cars.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gta.cars.dto.CreateUserDto;
import com.gta.cars.dto.LoginRequestDto;
import com.gta.cars.dto.LoginResponseDto;
import com.gta.cars.dto.UserResponseDto;
import com.gta.cars.model.Garagem;
import com.gta.cars.model.Role;
import com.gta.cars.model.User;
import com.gta.cars.repository.RoleRepository;
import com.gta.cars.repository.UserRepository;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final JwtEncoder jwtEncoder;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Value("${api.issuer}")
    private String issuer;
    
    public AuthController(JwtEncoder jwtEncoder, UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody CreateUserDto dto) {
        var userFromDb = userRepository.findByUsername(dto.username());

        if (userFromDb.isPresent())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByNameIgnoreCase("basic"));
            
        var user = new User(null, dto.username(), passwordEncoder.encode(dto.password()), dto.email(), dto.nome(), new ArrayList<Garagem>(), roles);

        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<UserResponseDto>> listUsers() {
        var usersFromDb = userRepository.findAll();
        var users = new ArrayList<UserResponseDto>();
        
        for (User user : usersFromDb) {
            users.add(new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getNome(), user.getRoles()));
        }
        
        return ResponseEntity.ok(users);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        var user = userRepository.findByUsername(loginRequest.username());

        if (user.isEmpty() || !user.get().isPasswordCorrect(loginRequest.password(), passwordEncoder))
            throw new BadCredentialsException("Nome de usuário ou senha inválidos.");

        var now = Instant.now();
        var expires = now.plus(7, ChronoUnit.DAYS);

        var scopes = user.get().getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer(issuer)
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(expires)
                .claim("scope", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return ResponseEntity.ok(new LoginResponseDto(jwtValue, LocalDateTime.ofInstant(expires, ZoneId.systemDefault()), user.get()));
    }

    @GetMapping("/validate-token")
    public ResponseEntity<Void> validateToken(JwtAuthenticationToken token) {
        return ResponseEntity.ok().build();
    }
    
}
