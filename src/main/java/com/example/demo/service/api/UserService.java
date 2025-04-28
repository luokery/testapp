package com.example.demo.service.api;

import com.example.demo.model.dto.UserDto;
import java.util.List;

/**
 * UserService interface for managing user-related operations.
 */
public interface UserService {

    /**
     * Retrieves all users from the system.
     *
     * @return A list of UserEntity objects representing all users.
     */
    List<UserDto> getAllUsers();
}