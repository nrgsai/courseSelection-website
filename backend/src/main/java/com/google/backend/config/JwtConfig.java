package com.google.backend.config;

import com.google.backend.model.UsersModel;
import com.google.backend.service.UsersService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtConfig {

    private UsersService usersService;

    @Autowired
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    //better way : implementation custom UserDetails by needed column (feature)
    public String generateToken(String username) {
        UserDetails details = usersService.loadUserByUsername(username);
        Optional<UsersModel> model = usersService.findUsersByUsername(username);
        return Jwts.builder()
                .setSubject(username)
                .setId(String.valueOf(model.isPresent() ? model.get().getId() : 0))
                .claim("username", details.getUsername())
                .claim("authorities", details.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 12 * 1000)))
                .signWith(SignatureAlgorithm.HS512, CollageConstant.SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(CollageConstant.SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            e.fillInStackTrace();
        }

        return false;
    }
}
