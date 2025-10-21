package org.example.dao;

import org.example.entity.User;

import java.util.List;

/**
 * Interface defining basic CRUD operations for working with {@link User} entities.
 */
public interface UserDAO {

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id the unique identifier of the user to retrieve
     * @return the user object corresponding to the given ID
     * @throws Exception if an error occurs during retrieval process
     */
    User getUserById(Long id) throws Exception;

    /**
     * Retrieves all users from the storage.
     *
     * @return a list of all users
     * @throws Exception if an error occurs during retrieval process
     */
    List<User> getAllUsers() throws Exception;

    /**
     * Saves a new user to the storage.
     *
     * @param user the user object to be saved
     * @throws Exception if an error occurs during saving process
     */
    void saveUser(User user) throws Exception;

    /**
     * Updates an existing user in the storage.
     *
     * @param user the user object with updated data
     * @throws Exception if an error occurs during update process
     */
    void updateUser(User user) throws Exception;

    /**
     * Deletes a user by their unique identifier.
     *
     * @param id the unique identifier of the user to delete
     * @throws Exception if an error occurs during deletion process
     */
    void deleteUser(Long id) throws Exception;
}
