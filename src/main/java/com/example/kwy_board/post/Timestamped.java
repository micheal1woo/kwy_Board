package com.example.kwy_board.post;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class Timestamped {

    @CreatedDate
    private LocalDateTime createdAt;

}
