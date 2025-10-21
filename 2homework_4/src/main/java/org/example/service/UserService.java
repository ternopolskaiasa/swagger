package org.example.service;

import org.example.entity.User;
import org.example.entity.UserRepository;
import org.example.dto.UserRequestDTO;
import org.example.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer component responsible for business logic concerning user-related operations.
 * Provides functionalities such as creating, retrieving, updating, and deleting users.
 */
@Service
@Transactional
public class UserService {

    /**
     * Repository used for accessing user data in the database.
     */
    private final UserRepository userRepository;

    /**
     * Mapper tool used for converting between DTO and entity classes.
     */
    private final ModelMapper modelMapper;

    /**
     * Default constructor injecting dependencies for repository and mapper.
     *
     * @param userRepository injected repository for user data access
     * @param modelMapper    injected mapper for conversion purposes
     */
    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Creates a new user based on the provided DTO, ensuring the uniqueness of the email address.
     *
     * @param requestDTO contains the details of the new user
     * @return newly created user represented as UserResponseDTO
     * @throws EmailAlreadyExistsException if the email already exists in the database
     */
    public UserResponseDTO createUser(UserRequestDTO requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new EmailAlreadyExistsException(requestDto.getEmail());
        }

        User user = modelMapper.map(requestDto, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id unique identifier of the user to retrieve
     * @return user representation as UserResponseDTO
     * @throws UserNotFoundException if the user with the given ID does not exist
     */
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Updates an existing user based on the provided DTO.
     *
     * @param id          unique identifier of the user to update
     * @param requestDto  updated user details
     * @return updated user representation as UserResponseDTO
     * @throws UserNotFoundException if the user with the given id does not exist
     */
    public UserResponseDTO updateUser(Long id, UserRequestDTO requestDto) {
        return userRepository.findById(id)
                .map(user -> {
                    modelMapper.map(requestDto, user);
                    User updatedUser = userRepository.save(user);
                    return modelMapper.map(updatedUser, UserResponseDTO.class);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Deletes a user by their unique identifier.
     *
     * @param id unique identifier of the user to delete
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Retrieves all users from the database.
     *
     * @return list of all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
