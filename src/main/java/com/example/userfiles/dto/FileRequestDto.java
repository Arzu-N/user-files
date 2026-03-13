package com.example.userfiles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor@NoArgsConstructor@Data
public class FileRequestDto {
    private List<MultipartFile> files;
    private Long userId;
}
