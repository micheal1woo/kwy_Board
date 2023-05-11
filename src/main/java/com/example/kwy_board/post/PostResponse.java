package com.example.kwy_board.post;

import java.time.LocalDateTime;


public class PostResponse {
    public String getTitle() {
        return title;
    }

    private String title;
    private LocalDateTime createdAt;

    public PostResponse(Post post){
        this.title = post.getTitle();
    }

    public PostResponse() {
    }
}
