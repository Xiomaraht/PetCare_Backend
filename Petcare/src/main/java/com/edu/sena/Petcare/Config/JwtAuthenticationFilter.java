package com.edu.sena.Petcare.config;

import com.edu.sena.Petcare.models.User;
import com.edu.sena.Petcare.repository.UserRepository;
import com.edu.sena.Petcare.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            final String token = authHeader.substring(7);
            try {
                String username = jwtService.extractUsername(token);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    User user = userRepository.findByUsername(username).orElse(null);

                    if (user != null && jwtService.isTokenValid(token, user)) {

                        String roleName = user.getAuthority() != null ? user.getAuthority().getName() : "ROLE_USER";
                        List<SimpleGrantedAuthority> authorities = Collections
                                .singletonList(new SimpleGrantedAuthority(roleName));

                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                authorities);

                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            } catch (Exception ex) {
                System.out.println("JWT ERROR: " + ex.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}
