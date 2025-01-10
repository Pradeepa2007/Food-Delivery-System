package com.food.delivery.system.customerservice.service;

import com.food.delivery.system.customerservice.dto.UserDto;

public interface UserService {
    UserDto getUserById(String id);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(String id, UserDto userDto);
    boolean deleteUser(String id);
}
