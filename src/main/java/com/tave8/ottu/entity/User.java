package com.tave8.ottu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(name = "user_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    @JsonIgnore
    @Column(nullable = false)
    private String email;

    @Column(name = "kakaotalk_id")
    private String kakaotalkId;

    @Column
    private String nickname;

    @Column
    @ColumnDefault("10")
    private Integer reliability;

    @Column(name = "is_first", columnDefinition = "boolean default true")
    private Boolean isFirst;

    @JsonIgnore
    @Column(name = "notice_token")
    private String noticeToken;

    @ManyToMany
    @JoinTable(name = "user_genre", joinColumns = @JoinColumn(name = "user_idx"), inverseJoinColumns = @JoinColumn(name = "genre_idx"))
    private List<Genre> genres = new ArrayList<>();

    @PrePersist
    public void prePersist() {  //기본 값 설정함
        this.reliability = this.reliability == null ? 10 : this.reliability;
        this.isFirst = this.isFirst == null ? true : this.isFirst;
    }

    @JsonIgnore
    public boolean isNoticeTokenNull() {    //DB에 저장된 회원의 토큰 값이 null인지 여부
        if (noticeToken == null)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "userIdx=" + userIdx +
                ", email='" + email + '\'' +
                ", kakaotalkId='" + kakaotalkId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", reliability=" + reliability +
                ", isFirst=" + isFirst +
                ", noticeToken='" + noticeToken + '\'' +
                ", genres=" + genres +
                '}';
    }
}
