package com.example.blackboard.controller;

import com.example.blackboard.domain.Post;
import com.example.blackboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * Create
     * */
    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    /**
     * Read - All
     * */
    @GetMapping
    public Page<Post> getPosts(Pageable pageable) {
        return postService.getPosts(pageable);
    }

    /**
     * Read - Each
     * */
    @GetMapping("/{idx}")
    public ResponseEntity<Post> getPost(@PathVariable Long idx) {
        return postService.getPost(idx)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Update
     * */
    @PutMapping("/{idx}")
    public ResponseEntity<Post> updatePost(@PathVariable Long idx, @RequestBody Post updatedPost) {
        return ResponseEntity.ok(postService.updatePost(idx, updatedPost));
    }

    /**
     * Delete
     * */
    @DeleteMapping("/posts{idx}")
    public void deletePost(@PathVariable Long idx) {
        postService.deletePost(idx);
    }
}
