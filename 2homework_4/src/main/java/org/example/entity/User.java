package org.example.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * Entity class representing a user in the system.
 * Maps directly to the "users" table in the database.
 */
@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    /**
     * Auto-generated unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Full name of the user.
     * Cannot be null.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Email address of the user.
     * Must be unique and cannot be null.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Age of the user.
     * Cannot be null.
     */
    @Column(nullable = false)
    private Integer age;

    /**
     * Date and time when the user account was created.
     * Once set, cannot be changed (updatable=false).
     */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Private constructor for building a new user entity.
     *
     * @param name  Full name of the user.
     * @param email Email address of the user.
     * @param age   Age of the user.
     */
    User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
