package com.edu.sena.Petcare.controller;

import com.edu.sena.Petcare.config.JwtAuthenticationFilter;
import com.edu.sena.Petcare.dto.LoginDto;
import com.edu.sena.Petcare.models.User;
import com.edu.sena.Petcare.repository.CustomerRepository;
import com.edu.sena.Petcare.repository.UserRepository;
import com.edu.sena.Petcare.repository.VeterinaryClinicRepository;
import com.edu.sena.Petcare.service.EmailService;
import com.edu.sena.Petcare.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private EmailService emailService;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private VeterinaryClinicRepository clinicRepository;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testLoginSuccess() throws Exception {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("prueba");
        loginDto.setPassword("admin123");

        User user = new User();
        user.setUsername("prueba");
        user.setPassword("encodedPassword");

        when(userRepository.findByUsername("prueba")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("admin123", "encodedPassword")).thenReturn(true);
        when(jwtService.generateToken(user)).thenReturn("mockToken");

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mockToken"));
    }

    @Test
    public void testLoginFailure() throws Exception {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("wrongUser");
        loginDto.setPassword("wrongPass");

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(post("/api/auth/logout"))
                .andExpect(status().isOk())
                .andExpect(content().string("Sesión cerrada exitosamente"));
    }
}
