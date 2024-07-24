package com.leucine.collegedirectory.controller;

import java.util.Date;

import javax.crypto.SecretKey;

import com.leucine.collegedirectory.config.SecurityDetails;
import com.leucine.collegedirectory.model.Role;
import com.leucine.collegedirectory.model.Users;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtToken {

    // Generate a JWT token for the given user
    public static String generateToken(Users user) {
        SecretKey key = Keys.hmacShaKeyFor(SecurityDetails.JWT_KEY.getBytes());
        return Jwts.builder()
                .setIssuer("YourAppName")
                .setSubject("JWT_Token")
                .claim("username", user.getUsername())
                .claim("authorities", getAuthorities(user.getRole())) // Adjust based on roles
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000)) // 1 day expiration
                .signWith(key).compact();
    }

    // Convert role to a string representation for JWT claims
    private static String getAuthorities(Role role) {
        return role.name(); // Use name() method for enum
    }
}