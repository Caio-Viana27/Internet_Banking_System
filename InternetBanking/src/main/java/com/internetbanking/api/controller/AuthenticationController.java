package com.internetbanking.api.controller;

import com.internetbanking.api.model.JWTokenData;
import com.internetbanking.api.model.entity.AuthData;
import com.internetbanking.api.model.entity.User;
import com.internetbanking.api.service.JWTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private AuthenticationManager manager;
    private JWTokenService tokenService;

    public AuthenticationController(JWTokenService tokenService, AuthenticationManager manager) {
        this.tokenService = tokenService;
        this.manager = manager;
    }

    @PostMapping
    public ResponseEntity<JWTokenData> doLogin(@RequestBody AuthData data) {
        var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(token);
        return ResponseEntity.ok(new JWTokenData(tokenService.createToken((User) authentication.getPrincipal())));
    }
}
