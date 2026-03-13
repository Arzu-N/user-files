package com.example.userfiles.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor@NoArgsConstructor@Data@Builder
public class FileResponseDto {
    private long id;
    private String url;
    private Long userId;

}
