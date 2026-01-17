package com.streamflix.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "app_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password; // In production, ALWAYS hash passwords (e.g., BCrypt)
    private String role; // "USER" or "ADMIN"
}