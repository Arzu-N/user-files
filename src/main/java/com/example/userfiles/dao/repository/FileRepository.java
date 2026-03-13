package com.example.userfiles.dao.repository;

import com.example.userfiles.dao.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File,Long> {
    List<File> findAllByUserIdAndPhotoIsTrue(Long userId);
    List<File>findAllByUserIdAndPhotoIsFalse(Long userId);
}
