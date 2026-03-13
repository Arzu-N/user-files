package com.example.userfiles.controller;

import com.example.userfiles.dto.FileRequestDto;
import com.example.userfiles.dto.UserRequestDto;
import com.example.userfiles.dto.UserResponseDto;
import com.example.userfiles.service.AllServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.multi.MultiPanelUI;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AllController {
    private final AllServices allServices;
@PostMapping("/user")
    public ResponseEntity<Void> addUser(@ModelAttribute UserRequestDto dto) throws IOException {
        allServices.saveUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
@PostMapping("/file")
    public ResponseEntity<Void>addFile(@ModelAttribute FileRequestDto dto)throws IOException{
    allServices.addFile(dto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
}

@GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>>getUsers(){
    return ResponseEntity.ok(allServices.getUsers());
}
}
