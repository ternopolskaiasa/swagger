package org.example.service;

import org.example.entity.User;
import org.example.entity.UserRepository;
import org.example.dto.UserRequestDTO;
import org.example.dto.UserResponseDTO;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserResponseDTO createUser(UserRequestDTO requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new EmailAlreadyExistsException(requestDto.getEmail());
        }

        User user = modelMapper.map(requestDto, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO requestDto) {
        return userRepository.findById(id)
                .map(user -> {
                    modelMapper.map(requestDto, user);
                    User updatedUser = userRepository.save(user);
                    return modelMapper.map(updatedUser, UserResponseDTO.class);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
