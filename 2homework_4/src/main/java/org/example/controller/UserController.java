package org.example.controller;

import org.example.dto.UserRequestDTO;
import org.example.dto.UserResponseDTO;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "User Management", description = "Operations related to managing users.")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "New user creation",
            responses = {
                    @ApiResponse(responseCode = "201", description = "New user created successfully"),
                    @ApiResponse(responseCode = "400", description = "Validation data error", content = @Content(schema = @Schema(implementation = Exception.class)))
            })    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO requestDto) {
        return userService.createUser(requestDto);
    }

    @Operation(
            summary = "Get user by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User found", content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Operation(
            summary = "Get all users",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Users list", content = @Content(array = @io.swagger.v3.oas.annotations.media.ArraySchema(schema = @Schema(implementation = User.class))))
            })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(
            summary = "User update",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User has been updated", content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "User is not found")
            })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO requestDto) {
        return userService.updateUser(id, requestDto);
    }

    @Operation(
            summary = "User deletion",
            responses = {
                    @ApiResponse(responseCode = "204", description = "User deleted"),
                    @ApiResponse(responseCode = "404", description = "User hasn't been deleted")
            })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
