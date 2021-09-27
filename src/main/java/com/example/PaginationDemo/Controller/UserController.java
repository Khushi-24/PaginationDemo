package com.example.PaginationDemo.Controller;

import com.example.PaginationDemo.Service.UserService;
import com.example.PaginationDemo.dto.UserDto;
import com.example.PaginationDemo.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto){

        UserResponseDto addUser = userService.addUser(userDto);
        return  new ResponseEntity<>(addUser, HttpStatus.CREATED);
    }
}
