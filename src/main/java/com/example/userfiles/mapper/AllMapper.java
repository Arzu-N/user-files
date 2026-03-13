package com.example.userfiles.mapper;

import com.example.userfiles.dao.entity.File;
import com.example.userfiles.dao.entity.User;
import com.example.userfiles.dao.repository.UserRepository;
import com.example.userfiles.dto.FileRequestDto;
import com.example.userfiles.dto.FileResponseDto;
import com.example.userfiles.dto.UserRequestDto;
import com.example.userfiles.dto.UserResponseDto;
import com.example.userfiles.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AllMapper {

    private final UserRepository userRepository;

    public AllMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User toUserEntity(UserRequestDto dto){
      return  User.builder().
              userName(dto.getUserName()).
              age(dto.getAge()).
              build();
    }

    public UserResponseDto toUserDto(User user){
       return  UserResponseDto.builder().id(user.getId()).
               userName(user.getUserName()).
               age(user.getAge())
               .build();
    }

    public File toFileEntity(FileRequestDto dto){
        return File.builder().user(userRepository.findById(dto.getUserId()).
                orElseThrow(()->new UserNotFoundException("User not found"))).build();
    }

    public  FileResponseDto toFileDto(File file){
        return FileResponseDto.builder().id(file.getId())
                .url(file.getUrl())
                .userId(file.getUser().getId())
                .build();
    }
}
