package com.food.delivery.system.customerservice.service.impl;

import com.food.delivery.system.customerservice.dto.UserDto;
import com.food.delivery.system.customerservice.model.User;
import com.food.delivery.system.customerservice.repository.UserRepository;
import com.food.delivery.system.customerservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.food.delivery.system.customerservice.exception.UserNotFoundException;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    public UserDto getUserById(String id) {
        logger.info("Attempting to retrieve user with ID: {}", id);
        Optional<User> userOptional = userRepository.findById(id);
        logger.info("User found with ID: {}", id);
        // If user not found, throw UserNotFoundException
        return userOptional.map(user -> modelMapper.map(user, UserDto.class))
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }

    public UserDto createUser(UserDto userDto) {
        logger.info("Creating user with data: {}", userDto);
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        logger.info("User created with ID: {}", savedUser.getId());
        return modelMapper.map(savedUser, UserDto.class);
    }

    public UserDto updateUser(String id, UserDto userDto) {
        logger.info("Attempting to update user with ID: {}", id);
        if (userRepository.existsById(id)) {
            User user = modelMapper.map(userDto, User.class);
            user.setId(id);
            User updatedUser = userRepository.save(user);
            logger.info("User updated with ID: {}", updatedUser.getId());
            return modelMapper.map(updatedUser, UserDto.class);
        }
        logger.warn("User not found with ID: {}", id);
        throw new UserNotFoundException("User not found with ID: " + id);
    }

    public boolean deleteUser(String id) {
        logger.info("Attempting to delete user with ID: {}", id);
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            logger.info("User deleted with ID: {}", id);
            return true;
        }
        logger.warn("User not found with ID: {}", id);
        throw new UserNotFoundException("User not found with ID: " + id);
    }

}
