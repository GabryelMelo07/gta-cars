package com.gta.cars.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gta.cars.dto.UserInfoDTO;
import com.gta.cars.model.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/info")
    public ResponseEntity<UserInfoDTO> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if (authentication != null && authentication.isAuthenticated())
            return ResponseEntity.ok().body(new UserInfoDTO(user.getId(), user.getNome()));

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    
    
}
