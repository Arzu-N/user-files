package com.example.userfiles.service;

import com.example.userfiles.dao.entity.File;
import com.example.userfiles.dao.entity.User;
import com.example.userfiles.dao.repository.FileRepository;
import com.example.userfiles.dao.repository.UserRepository;
import com.example.userfiles.dto.FileRequestDto;
import com.example.userfiles.dto.FileResponseDto;
import com.example.userfiles.dto.UserRequestDto;
import com.example.userfiles.dto.UserResponseDto;
import com.example.userfiles.exception.UserNotFoundException;
import com.example.userfiles.mapper.AllMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Service@RequiredArgsConstructor
public class AllServices {
    private final AllMapper allMapper;
private static final String PATH="C:\\Workspacee\\spring\\users\\image";
    private final UserRepository userRepository;
    private final FileRepository fileRepository;

    @Transactional
    public void saveUser(UserRequestDto dto) throws IOException {
        User user = allMapper.toUserEntity(dto);
        userRepository.save(user);
        Path path = Files.createDirectories(Path.of(PATH +"/"+ String.valueOf(user.getId())));
        if(dto.getPhotos()!=null&&!dto.getPhotos().isEmpty()){
        for(MultipartFile p:dto.getPhotos()){
        String fileName = UUID.randomUUID() + p.getOriginalFilename();
        java.io.File file = new java.io.File(path +"/"+fileName);
        p.transferTo(file);
        String url="image/"+user.getId()+"/"+fileName;
            File file1 = File.builder()
                    .user(user)
                    .photo(true)
                    .url(url)
                    .build();
            fileRepository.save(file1);
        }}}
@Transactional
    public void addFile(FileRequestDto dto) throws IOException{
    User user = userRepository.findById(dto.getUserId()).orElseThrow(() ->
            new UserNotFoundException("User not found"));
    if(dto.getFiles()!=null&&!dto.getFiles().isEmpty()){
        for(MultipartFile f:dto.getFiles()){

            Path path = Files.createDirectories(Path.of(PATH + "/"+String.valueOf(user.getId())));
        String fileName = UUID.randomUUID() + f.getOriginalFilename();
        java.io.File file1 = new java.io.File(path +"/"+fileName);
        f.transferTo(file1);
        String url="image/"+dto.getUserId()+"/"+fileName;


            File file = File.builder()
                    .photo(false)
                    .url(url)
                    .user(user).build();
        fileRepository.save(file);}
    }}

    public List<UserResponseDto> getUsers(){
        return userRepository.findAll().stream().map(user->{
            List<FileResponseDto> photos = fileRepository.findAllByUserIdAndPhotoIsTrue(user.getId()).stream().
                    map(p ->
                            FileResponseDto.builder()
                                    .id(p.getId())
                                    .url(p.getUrl())
                                    .userId(p.getUser().getId())
                                    .build()).toList();

            List<FileResponseDto> files = fileRepository.findAllByUserIdAndPhotoIsFalse(user.getId()).stream().
                    map(f ->
                            FileResponseDto.builder()
                                    .id(f.getId())
                                    .url(f.getUrl())
                                    .userId(f.getUser().getId())
                                    .build()).toList();
               return UserResponseDto.builder()
                       .id(user.getId())
                       .userName(user.getUserName())
                       .age(user.getAge())
                       .photos(photos)
                       .files(files)
                       .build();

        }).toList();
    }
}
