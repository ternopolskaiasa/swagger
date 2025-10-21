package org.example.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Response Data Transfer Object (DTO) used for sending user information in API responses.
 * Contains minimal but essential user details.
 */
@Getter
@Setter
public class UserResponseDTO {

    /**
     * Unique identifier of the user.
     */
    private Long id;

    /**
     * Full name of the user.
     */
    private String name;

    /**
     * Email address of the user.
     */
    private String email;
}
