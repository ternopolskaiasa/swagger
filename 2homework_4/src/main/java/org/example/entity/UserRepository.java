package org.example.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link User} entities.
 * Extends JPA's standard repository capabilities for CRUD operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their email address.
     *
     * @param email The email address to search for.
     * @return An {@link Optional} containing the user if found, otherwise empty.
     */
    Optional<User> findByEmail(String email);
}
