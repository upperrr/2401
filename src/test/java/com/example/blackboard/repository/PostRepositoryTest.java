package com.example.blackboard.repository;

import com.example.blackboard.domain.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    /**
     * CREATE
     */
    private Post createPost(String title, String contents, String created_by) {
        Post post = new Post();
        post.setTitle(title);
        post.setContents(contents);
        post.setCreated_by(created_by);
        post.setUpdated_by(created_by);
        return post;
    }

    @Test
    public void testSavePost() {
        Post post = createPost("Test Title", "Test Contents", "User");

        Post savedPost = postRepository.save(post);
        assertNotNull(savedPost);

        System.out.println("생성된 게시글: " + savedPost);
        System.out.println("전체 게시글: ");
        List<Post> allPosts = postRepository.findAll();
        allPosts.forEach(p -> System.out.printf("%s\n", p));
    }

    /**
     * READ
     */
    @Test
    public void testGetPost() {
        Long idx = 1L;

        Optional<Post> optionalPost = postRepository.findById(idx);
        assertTrue(optionalPost.isPresent());

        Post post = optionalPost.get();
        System.out.println(idx+"번 게시글: " + post);
    }


    @Test
    public void testGetAllTitles() {
        List<Post> posts = postRepository.findAll();
        System.out.println("전체 게시글 제목: ");
        for (Post post : posts) {
            System.out.println(post.getTitle());
        }
    }

    @Test
    public void testGetAllPosts() {
        List<Post> posts = postRepository.findAll();

        System.out.println("전체 게시물의 개수: " + posts.size());
        System.out.println("전체 게시글: ");
        posts.stream().forEach(System.out::println);
    }

    /**
     * UPDATE
     * */
    private Post updatePost(Long idx, String user, String newTitle, String newContents) {
        Post post = postRepository.findById(idx).orElse(null);
        if(post != null && user.equals(post.getCreated_by())) {
            post.setTitle(newTitle);
            post.setContents(newContents);
            return postRepository.save(post);
        }
        return null;
    }

    @Test
    public void testUpdatePost() {
        // 이미 저장되어 있는 Post 객체의 idx와 created_by
        Long existingIdx = 6L;
        String existingUser = "User";

        // 저장되어 있는 Post 객체를 찾아서 제목과 내용을 수정하기 전에 기존 정보를 복사
        Post existingPost = postRepository.findById(existingIdx).orElse(null);
        assertNotNull(existingPost);
        System.out.println("수정 전의 게시글: " + existingPost);
        Post oldPost = new Post(existingPost.getIdx(), existingPost.getTitle(), existingPost.getContents(), existingPost.getCreated_by());

        // 수정 작업
        updatePost(existingIdx, existingUser, "수정된 제목", "수정된 내용");

        // 데이터베이스에서 수정된 Post 객체를 다시 가져오기
        Post retrievedPost = postRepository.findById(existingIdx).orElse(null);

        // 가져온 Post 객체가 존재하는지 확인
        assertNotNull(retrievedPost);
        // 가져온 Post 객체의 제목과 내용이 수정된 것과 일치하는지 확인
        assertEquals("수정된 제목", retrievedPost.getTitle());
        assertEquals("수정된 내용", retrievedPost.getContents());
        // 가져온 Post 객체의 created_by가 "User"인지 확인
        assertEquals(existingUser, retrievedPost.getCreated_by());

        // 수정된 Post 객체를 출력
        System.out.println("수정 후의 게시글: " + retrievedPost);
    }



    /**
     * DELETE
     * */
    @Test
    public void testDeletePost() {
        Long idx = 8L;  // 삭제하려는 Post 객체의 idx 값을 정의

        // idx에 해당하는 Post 객체가 데이터베이스에 존재하는지 확인
        Optional<Post> optionalPost = postRepository.findById(idx);
        assertTrue(optionalPost.isPresent());

        System.out.println("삭제할 게시글: \n" + optionalPost);

        // idx에 해당하는 Post 객체를 데이터베이스에서 삭제
        postRepository.deleteById(idx);

        // 삭제된 Post 객체가 정말로 데이터베이스에서 사라졌는지 확인
        Optional<Post> deletedPost = postRepository.findById(idx);
        assertFalse(deletedPost.isPresent());

        System.out.println("삭제 확인: " + deletedPost);
    }



}
