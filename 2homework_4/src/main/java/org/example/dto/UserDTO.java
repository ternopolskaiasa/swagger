package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) class for transferring user-related data between layers of the application.
 */
@Getter
@Setter
public class UserDTO {

    /**
     * Unique identifier of the user.
     * Generated automatically by the persistence mechanism.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Full name of the user.
     * Must not be blank.
     */
    @NotBlank
    private String name;

    /**
     * Email address of the user.
     * Must be a valid email format and must not be blank.
     */
    @NotBlank
    @Email
    private String email;

    /**
     * Age of the user.
     * Must not be blank.
     */
    @NotBlank
    private Integer age;

    /**
     * Creation date and time of the user.
     * Must not be blank.
     */
    @NotBlank
    private LocalDateTime createdAt;

    /**
     * Constructor for instantiating a new UserDTO object.
     *
     * @param name  Full name of the user.
     * @param email Email address of the user.
     * @param age   Age of the user.
     */
    UserDTO(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
