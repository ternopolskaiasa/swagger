package org.example.mapper;

import org.example.entity.User;
import org.example.dto.UserRequestDTO;
import org.example.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class responsible for defining the bean for mapping between domain models and DTOs using ModelMapper library.
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Configures and creates a {@code ModelMapper} bean which maps between different types.
     * 
     * @return configured instance of {@code ModelMapper}.
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        
        /*
         * Set strict matching strategy to ensure only exact field names match,
         * skip null values to avoid overwriting non-null fields, enable field-level matching
         * so nested fields can also be mapped automatically.
         */
        mapper.getConfiguration()
              .setMatchingStrategy(MatchingStrategies.STRICT)
              .setSkipNullEnabled(true)
              .setFieldMatchingEnabled(true);

        /* Map from User entity to UserResponseDTO */
        TypeMap<User, UserResponseDTO> userToDto = mapper.createTypeMap(User.class, UserResponseDTO.class);
        userToDto.addMapping(User::getId, UserResponseDTO::setId);
        userToDto.addMapping(User::getName, UserResponseDTO::setName);
        userToDto.addMapping(User::getEmail, UserResponseDTO::setEmail);

        /* Map from UserRequestDTO back to User entity */
        TypeMap<UserRequestDTO, User> dtoToUser = mapper.createTypeMap(UserRequestDTO.class, User.class);
        dtoToUser.addMapping(UserRequestDTO::getName, User::setName);
        dtoToUser.addMapping(UserRequestDTO::getEmail, User::setEmail);

        return mapper;
    }
}
