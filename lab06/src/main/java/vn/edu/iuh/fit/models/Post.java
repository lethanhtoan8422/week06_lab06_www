package vn.edu.iuh.fit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "post")
@NoArgsConstructor
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "authorId", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Post parent;

    @Column(name = "title", nullable = false, length = 275)
    private String title;

    @Column(name = "metaTitle", length = 300)
    private String metaTitle;

    @Lob
    @Column(name = "summary", columnDefinition = "text")
    private String summary;

    @Column(name = "published", nullable = false)
    private Boolean published = false;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @Column(name = "publishedAt")
    private LocalDateTime publishedAt;

    @Lob
    @Column(name = "content", columnDefinition = "text")
    private String content;

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private Set<Post> posts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private Set<PostComment> postComments = new LinkedHashSet<>();

    public Post(User author, Post parent, String title, String metaTitle, String summary, Boolean published, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime publishedAt, String content) {
        this.author = author;
        this.parent = parent;
        this.title = title;
        this.metaTitle = metaTitle;
        this.summary = summary;
        this.published = published;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.publishedAt = publishedAt;
        this.content = content;
    }
}