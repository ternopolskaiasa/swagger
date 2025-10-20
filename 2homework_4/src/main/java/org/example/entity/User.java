package org.example.entity;

import javax.persistence.*;

import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
