package com.food.delivery.system.customerservice.controller;

import com.food.delivery.system.customerservice.dto.UserDto;
import com.food.delivery.system.customerservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger log = LogManager.getLogger(UserService.class);

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        log.info("Received request to get user with ID: {}", id);
        UserDto userDto = userService.getUserById(id);
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
        log.warn("User not found with ID: {}", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        log.info("Received request to create user: {}", userDto);
        UserDto createdUser = userService.createUser(userDto);
        log.info("User created: {}", createdUser);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Update an existing user
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
        log.info("Received request to update user with ID: {}", id);
        UserDto updatedUser = userService.updateUser(id, userDto);
        if (updatedUser != null) {
            log.info("User updated: {}", updatedUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        log.warn("User not found for update with ID: {}", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        log.info("Received request to delete user with ID: {}", id);
        if (userService.deleteUser(id)) {
            log.info("User with ID: {} deleted successfully", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.warn("User not found for deletion with ID: {}", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}