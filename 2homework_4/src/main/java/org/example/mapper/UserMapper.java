package org.example.mapper;

import org.example.entity.User;
import org.example.dto.UserDTO;
import org.springframework.stereotype.Component;

/**
 * Mapper class responsible for converting between User entity and UserDTO.
 */
@Component
public class UserMapper {

    /**
     * Converts a User entity to a UserDTO.
     *
     * @param user Source User entity to convert.
     * @return Corresponding UserDTO object, or null if source user is null.
     */
    public UserDTO toDto(User user) {
        if (user == null) return null;
        return new UserDTO(user.getName(), user.getEmail(), user.getAge());
    }

    /**
     * Converts a UserDTO to a User entity.
     *
     * @param dto Source UserDTO to convert.
     * @return Corresponding User entity, or null if source DTO is null.
     */
    public User toEntity(UserDTO dto) {
        if (dto == null) return null;
        return new User(dto.getName(), dto.getEmail(), dto.getAge());
    }

    /**
     * Updates an existing User entity with data from a UserDTO.
     *
     * @param dto      Source UserDTO containing updated data.
     * @param user     Target User entity to update.
     */
    public void updateEntityFromDto(UserDTO dto, User user) {
        if (dto == null || user == null) return;
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
    }
}
