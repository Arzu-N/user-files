package com.example.userfiles.dao.repository;

import com.example.userfiles.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
