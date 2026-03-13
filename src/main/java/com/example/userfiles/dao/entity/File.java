package com.example.userfiles.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity@AllArgsConstructor@NoArgsConstructor@Data
@Table(name="files_1")
@Builder
public class File {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private boolean photo;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
