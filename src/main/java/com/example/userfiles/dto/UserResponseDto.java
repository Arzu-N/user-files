package com.example.userfiles.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor@NoArgsConstructor@Data@Builder
public class UserResponseDto {
    private Long id;
    private String userName;
    private Integer age;
    private List<FileResponseDto> files;
    private List<FileResponseDto>photos;
}
