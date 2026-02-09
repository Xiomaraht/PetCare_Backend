package com.edu.sena.Petcare.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
    name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
)
    private Set<Role> roles;
   
}
