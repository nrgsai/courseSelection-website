package com.google.backend.controller;

import com.google.backend.config.CollageConstant;
import com.google.backend.config.JwtConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<?> getToken(@RequestParam(name = "username") String username,
                                      @RequestParam(name = "password") String password,
                                      HttpServletResponse response) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        response.addHeader(CollageConstant.AUTHORIZATION, jwtConfig.generateToken(username));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
