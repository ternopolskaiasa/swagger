package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Data transfer object (DTO) used for receiving requests related to user creation or modification.
 * Includes validation constraints for the incoming data.
 */
@Getter
@Setter
public class UserRequestDTO {

    /**
     * Full name of the user.
     * Should have at least two characters and maximum fifty characters.
     */
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    /**
     * Email address of the user.
     * Must conform to the correct email format.
     */
    @NotBlank
    @Email(message = "Invalid email format")
    private String email;
}

