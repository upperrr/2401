package com.example.blackboard.service;

import com.example.blackboard.domain.Post;
import com.example.blackboard.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    /**
     * Create
     * */
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    /**
     * Read - All
     * */
    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    /**
     * Read - Each
     * */
    public Optional<Post> getPost(Long idx) {
        return postRepository.findById(idx);
    }

    /**
     * Update
     * */
    @Transactional
    public Post updatePost(Long idx, Post updatedPost) {
        return postRepository.findById(idx)
                .map(post -> {
                    post.setTitle(updatedPost.getTitle());
                    post.setContents(updatedPost.getContents());
                    return postRepository.save(post);
                })
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + idx));
    }

    /**
     * Delete
     * */
    @Transactional //@Modifying 사용을 위해
    public void deletePost(Long idx) {
        postRepository.setUseYnToZero(idx);
    }

    /**
     * Search
     * */
    public List<Post> searchPosts(String keyword) {
        return postRepository.search(keyword);
    }
}
