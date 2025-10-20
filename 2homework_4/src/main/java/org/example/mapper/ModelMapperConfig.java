package org.example.mapper;

import org.example.entity.User;
import org.example.dto.UserRequestDTO;
import org.example.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true)
                .setFieldMatchingEnabled(true);

        TypeMap<User, UserResponseDTO> userToDto = mapper.createTypeMap(User.class, UserResponseDTO.class);
        TypeMap<UserRequestDTO, User> dtoToUser = mapper.createTypeMap(UserRequestDTO.class, User.class);

        userToDto.addMapping(User::getId, UserResponseDTO::setId);
        userToDto.addMapping(User::getName, UserResponseDTO::setName);
        userToDto.addMapping(User::getEmail, UserResponseDTO::setEmail);

        dtoToUser.addMapping(UserRequestDTO::getName, User::setName);
        dtoToUser.addMapping(UserRequestDTO::getEmail, User::setEmail);

        return mapper;
    }
}
