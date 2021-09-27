package com.example.PaginationDemo.ServiceImpl;

import com.example.PaginationDemo.CustomException.BadRequestException;
import com.example.PaginationDemo.Repository.UserRepository;
import com.example.PaginationDemo.Service.UserService;
import com.example.PaginationDemo.dto.UserDto;
import com.example.PaginationDemo.dto.UserResponseDto;
import com.example.PaginationDemo.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto addUser(UserDto userDto) {
        User user = new User();
        UserResponseDto userResponseDto = new UserResponseDto();
        if(userDto.getUserName() == null || userDto.getPassword() == null){
            if(userDto.getUserName() == null){
                throw new BadRequestException("UserName can't be null");
            }
            if(userDto.getPassword() == null){
                throw new BadRequestException("Password can't be null");
            }
        }else{
            if(!userRepository.existsById(userDto.getUserName())){
                BeanUtils.copyProperties(userDto, user);
                userRepository.save(user);
                BeanUtils.copyProperties(user, userResponseDto);
                return userResponseDto;
            }
            else{
                throw new BadRequestException("User with userName= " +userDto.getUserName() +
                        "  already exists.");
            }
        }

        return userResponseDto;
    }
}
