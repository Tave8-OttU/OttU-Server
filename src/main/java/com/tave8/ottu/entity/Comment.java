package com.tave8.ottu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "comment")
@Getter
@Setter
public class Comment {
    @Id
    @Column(name = "comment_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentIdx;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "post_idx")
    private Post post; // 글

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private User writer;    //작성자 정보

    @Column(name = "content")
    private String content; // 댓글 내용

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @JsonIgnore
    @Column(name = "is_deleted",columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @PrePersist
    public void prePersist() {
        this.isDeleted = this.isDeleted == null ? false : this.isDeleted;
    }
}
