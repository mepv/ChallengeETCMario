package com.mepv.service;

import io.smallrye.jwt.build.Jwt;

import javax.inject.Singleton;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.NoSuchElementException;

import static com.mepv.util.StaticFunctions.loadUserByUsername;

@Singleton
public class GenerateTokenService {

    public String generateToken(String username) {
        return Jwt.issuer(username)
                .subject("etc-challenge")
                .groups(new HashSet<>(Collections.singletonList(getRole(username))))
                .expiresAt(Instant.now().plus(Duration.ofDays(3)))
                .sign();
    }

    private String getRole(String username) {
        return loadUserByUsername(username)
                .orElseThrow(NoSuchElementException::new)
                .getRole();
    }
}
