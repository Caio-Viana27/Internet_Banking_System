package com.internetbanking.api.config.security;

import com.internetbanking.api.repository.UserRepository;
import com.internetbanking.api.service.JWTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final JWTokenService tokenService;
    private final UserRepository userRepository;

    public SecurityFilter(JWTokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Filtro chamado!");

        String token = this.recoverToken(request);
        if (token != null) {
            String login = tokenService.getSubject(token);
            UserDetails user = userRepository.findByCpf(login);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("Login: " + login);
        }
        filterChain.doFilter(request, response);
    }

    public String recoverToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            return  null;
        }
        return token.replace("Bearer", "").trim();
    }
}
