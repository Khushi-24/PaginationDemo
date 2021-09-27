package com.example.PaginationDemo.Service;

import com.example.PaginationDemo.dto.UserDto;
import com.example.PaginationDemo.dto.UserResponseDto;

public interface UserService {
    UserResponseDto addUser(UserDto userDto);
}
