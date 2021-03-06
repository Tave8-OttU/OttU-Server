package com.tave8.ottu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "communitypost")
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @Column(name = "post_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postIdx;

    @ManyToOne
    @JoinColumn(name = "platform_idx")
    private Platform platform;   // 플랫폼 정보

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private User writer; // 유저 정보(작성자)

    @Column(name = "content")
    private String content; // 글 내용

    @Transient
    private Long commentNum = 0L;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate; // 작성 날짜, 시간

    @JsonIgnore
    @Column(name = "edited_date")
    private LocalDateTime editedDate; // 수정 날짜, 시간

    @JsonIgnore
    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private Boolean isDeleted; // 삭제 여부

    @PrePersist
    public void prePersist() {
        this.isDeleted = this.isDeleted == null ? false : this.isDeleted;
    }
}
