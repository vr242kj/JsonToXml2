package com.example.jsontoxml2.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts", indexes = {
        @Index(name = "user_index", columnList = "user_id"),
        @Index(name = "multiIndexUserAndPublished", columnList = "user_id, published"),
        @Index(name = "multiIndexUserAndLikedCount", columnList = "user_id, likes_count"),
        @Index(name = "multiIndexUserAndFilters", columnList = "user_id, published, likes_count")
})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "published")
    private Boolean isPublished;

    @Column(name = "likes_count")
    private Integer likesCount = 0;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public String toCSV() {
        return String.format("%d;%s;%s;%d;%b;%d", id, title, content, likesCount, isPublished, user.getId());
    }

}
