package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRequestDTO {
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank
    @Email(message = "Invalid email format")
    private String email;
}
