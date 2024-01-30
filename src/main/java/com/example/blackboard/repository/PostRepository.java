package com.example.blackboard.repository;

import com.example.blackboard.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Read - ALL
     * */
    Page<Post> findAll(Pageable pageable);

    /**
     * Delete
     * */
    @Modifying
    @Query("UPDATE Post p SET p.use_yn = 0 WHERE p.idx = :idx")
    void setUseYnToZero(@Param("idx") Long idx);

    /**
     * Search
     * */
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% OR p.contents LIKE %:keyword% OR p.created_by LIKE %:keyword%")
    List<Post> search(@Param("keyword") String keyword);

}
