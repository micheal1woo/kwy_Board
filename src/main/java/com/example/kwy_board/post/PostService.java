package com.example.kwy_board.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;

    // 게시글 생성
    @Transactional
    public void creatPost(String title, String content) {
        Post post = new Post(title, content);
        repository.save(post);
    }

    @Transactional
    public List<PostResponse> findPost(){
        List<Post> posts = repository.findAll();
        List<PostResponse> responses = new ArrayList<>();
        for (Post post : posts){
            responses.add(new PostResponse(post));
        }
        return responses;
    }

    @Transactional
    public Post getPost(Long postId){
        Optional<Post> findPost;
        findPost = repository.findById(postId);
        if (findPost.isEmpty()){
            throw new NullPointerException("게시글이 없습니다.");
        }
        return findPost.get();
    }

}
