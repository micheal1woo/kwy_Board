package com.example.kwy_board.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostTest {
    @Autowired
    private PostRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void createPostEntity() {
        String title = "this is title";
        String content = "this is content";
        Post newPost = new Post(title, content);
        assertEquals("this is title", newPost.getTitle());
        assertEquals("this is content", newPost.getContent());
    }

    @Test
    void savePost() {
        Post post = new Post("title", "content");
        repository.save(post);
        entityManager.flush();
        Optional<Post> repoPost = repository.findById(post.getId());
        assertEquals(repoPost.get(), post);

    }

    @Test
    void findAllPost() {
        Post savePost = new Post("title", "content");
        Post savePost1 = new Post("title1", "content");
        repository.save(savePost);
        repository.save(savePost1);
        entityManager.flush();
        List<Post> posts = repository.findAll();
        List<PostResponse> responses = new ArrayList<>();
        for (Post post : posts){
            responses.add(new PostResponse(post));
        }

        assertEquals(posts.get(0).getTitle(), responses.get(0).getTitle());
        assertEquals(posts.get(1).getTitle(), responses.get(1).getTitle());
    }

    @Test
    void emptyPost() {
        Long id = 1L;
        Optional<Post> findPost;
        findPost = repository.findById(id);
        assertThrows(NullPointerException.class, () -> {
            if (findPost.isEmpty()) {
                throw new NullPointerException("게시글이 없습니다.");
            }
        });
    }

    @Test
    void getPost() {
        Post savePost = new Post("title", "content");
        Post savePost1 = new Post("title1", "content");
        repository.save(savePost);
        repository.save(savePost1);
        entityManager.flush();
        Optional<Post> findPost;
        findPost = repository.findById(savePost1.getId());

        assertEquals(findPost.get().getContent(), savePost1.getContent());
        assertEquals(findPost.get().getTitle(), savePost1.getTitle());

    }
}
