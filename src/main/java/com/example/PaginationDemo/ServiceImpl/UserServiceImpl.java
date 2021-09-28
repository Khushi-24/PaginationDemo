package com.example.PaginationDemo.ServiceImpl;

import com.example.PaginationDemo.CustomException.BadRequestException;
import com.example.PaginationDemo.Repository.UserRepository;
import com.example.PaginationDemo.Service.UserService;
import com.example.PaginationDemo.dto.UserDto;
import com.example.PaginationDemo.dto.UserResponseDto;
import com.example.PaginationDemo.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto addUser(UserDto userDto) {
        User user = new User();
        UserResponseDto userResponseDto = new UserResponseDto();
        if(userDto.getUserName() == null || userDto.getPassword() == null){
            if(userDto.getUserName() == null){
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "UserName can't be null");
            }
            if(userDto.getPassword() == null){
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "Password can't be null");
            }
        }else{
            if(!userRepository.existsById(userDto.getUserName())){
                user.setUserName(userDto.getUserName());
                user.setPassword(getEncodedPassword(userDto.getPassword()));
                userRepository.save(user);
                userResponseDto.setUserName(user.getUserName());
                return userResponseDto;
            }
            else{
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "User with userName= " +userDto.getUserName() +
                        "  already exists.");
            }
        }

        return userResponseDto;
    }

    public String getEncodedPassword(String password){
        return  passwordEncoder.encode(password);
    }
}
