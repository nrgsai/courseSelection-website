package com.google.backend.controller;

import com.google.backend.config.CollageConstant;
import com.google.backend.config.JwtConfig;
import com.google.backend.model.UsersModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = CollageConstant.JWT_CONTEXT)
@RequiredArgsConstructor
public class JwtController {

    private final AuthenticationManager authenticationManager;
    private JwtConfig jwtConfig;

    @Autowired
    public void setJwtConfig(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @PostMapping(value = CollageConstant.TOKEN, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getToken(@RequestBody UsersModel model, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    
        String token = jwtConfig.generateToken(model.getUsername());
        // Assuming UsersModel has a role attribute, otherwise, adjust accordingly
        String role = model.getRole();
    
        // Return the token and user details in the response body
        return ResponseEntity.ok().body(new HashMap<String, Object>() {{
            put("accessToken", token);
            put("username", model.getUsername());
            put("role", role);
        }});
    }
    
