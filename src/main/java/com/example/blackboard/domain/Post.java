package com.example.blackboard.domain;

//import jakarta.persistence.*;
import javax.persistence.*;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@ToString           //문자열 자동생성
@NoArgsConstructor  //기본생성자
@AllArgsConstructor //파라미터가 있는 생성자 자동생성
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String title;
    private String contents;
    private int use_yn = 1;
    @CreatedDate
    @ToString.Exclude
    private LocalDateTime created_at;
    private String created_by;
    @LastModifiedDate
    @ToString.Exclude
    private LocalDateTime updated_at;
    private String updated_by;

    public Post(Long idx, String title, String contents, String created_by) {
        this.idx = idx;
        this.title = title;
        this.contents = contents;
        this.created_by = created_by;
    }

}
