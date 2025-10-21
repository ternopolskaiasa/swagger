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

/**
 * Controller for handling CRUD operations on Users.
 */
@Tag(name = "User Management", description = "Operations related to managing users.")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    /**
     * Constructor injection of the {@link UserService}.
     *
     * @param userService Service layer implementation for user management.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a new user based on provided DTO and returns the response with status code 201 (CREATED).
     *
     * @param requestDTO Validated input DTO containing necessary fields for creating a user.
     * @return Created user's details in form of a {@link UserResponseDTO}.
     */
    @Operation(
            summary = "Create a new user.",
            responses = {
                @ApiResponse(responseCode = "201", description = "User was successfully created."),
                @ApiResponse(responseCode = "400", description = "Bad Request due to validation errors.", content = @Content(schema = @Schema(implementation = Exception.class)))
            })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO requestDto) {
        return userService.createUser(requestDto);
    }

    /**
     * Retrieves an existing user by their id.
     *
     * @param id Unique identifier of the user.
     * @return The requested user's information as a {@link UserResponseDTO}, or throws 404 if no such user exists.
     */
    @Operation(
            summary = "Get user by its id.",
            responses = {
                @ApiResponse(responseCode = "200", description = "User retrieved successfully.", content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
                @ApiResponse(responseCode = "404", description = "No user found with given id.")
            })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * Fetches all registered users from the system.
     *
     * @return A list of all users present in the database.
     */
    @Operation(
            summary = "Get all users.",
            responses = {
                @ApiResponse(responseCode = "200", description = "List of users returned successfully.", content = @Content(array = @io.swagger.v3.oas.annotations.media.ArraySchema(schema = @Schema(implementation = User.class))))
            })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Updates an existing user using the specified id and valid DTO payload.
     *
     * @param id       Identifier of the user to be updated.
     * @param requestDTO New values for updating the user record.
     * @return Updated user's information wrapped in a {@link UserResponseDTO}.
     */
    @Operation(
            summary = "Update an existing user by providing its id and new data.",
            responses = {
                @ApiResponse(responseCode = "200", description = "User updated successfully.", content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
                @ApiResponse(responseCode = "404", description = "No user found with given id.")
            })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO requestDto) {
        return userService.updateUser(id, requestDto);
    }

    /**
     * Deletes a user from the system.
     *
     * @param id Unique identifier of the user to be removed.
     */
    @Operation(
            summary = "Remove a user by specifying its id.",
            responses = {
                @ApiResponse(responseCode = "204", description = "User deleted successfully."),
                @ApiResponse(responseCode = "404", description = "No user found with given id.")
            })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
