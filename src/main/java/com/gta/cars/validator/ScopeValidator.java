package com.gta.cars.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.gta.cars.model.Role;
import com.gta.cars.repository.RoleRepository;

@Component
public class ScopeValidator implements OAuth2TokenValidator<Jwt> {

    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        System.out.println("===============EXECUTANDO ScopeValidator=================");
        List<String> scopes = token.getClaimAsStringList("scope");

        if (scopes != null && !scopes.isEmpty()) {
            for (String string : scopes) {
                Role role = roleRepository.findByNameIgnoreCase(string);
                System.out.println("===============================");
                System.out.println(role);
                System.out.println(string);
                System.out.println("===============================");
                if (role != null) {
                    return OAuth2TokenValidatorResult.success();
                }
            }
        }
        
        return OAuth2TokenValidatorResult.failure(new OAuth2Error("invalid_token", "Invalid scopes", null));
    }
    
}