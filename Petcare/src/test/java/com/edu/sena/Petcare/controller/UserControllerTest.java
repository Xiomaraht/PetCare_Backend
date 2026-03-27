package com.edu.sena.Petcare.controller;

import com.edu.sena.Petcare.config.JwtAuthenticationFilter;
import com.edu.sena.Petcare.dto.UserDTO;
import com.edu.sena.Petcare.service.JwtService;
import com.edu.sena.Petcare.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    public void testGetAllUsers() throws Exception {
        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setUsername("prueba");
        
        when(userService.findAll()).thenReturn(Arrays.asList(user));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("prueba"));
    }

    @Test
    @WithMockUser
    public void testGetUserById() throws Exception {
        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setUsername("prueba");

        when(userService.findOne(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("prueba"));
    }

    @Test
    @WithMockUser
    public void testCreateUser() throws Exception {
        UserDTO user = new UserDTO();
        user.setUsername("newUser");
        user.setPassword("admin123");

        when(userService.save(any(UserDTO.class))).thenReturn(user);

        mockMvc.perform(post("/api/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.mensaje").value("Usuario creado exitosamente"));
    }

    @Test
    @WithMockUser
    public void testUpdateUser() throws Exception {
        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setUsername("updatedUser");

        when(userService.update(any(UserDTO.class))).thenReturn(Optional.of(user));

        mockMvc.perform(put("/api/users/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Usuario actualizado exitosamente"));
    }
}
