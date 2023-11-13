package vn.edu.iuh.fit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "post_comment")
@NoArgsConstructor
@ToString
public class PostComment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "postId", nullable = false)
    @JsonIgnore
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    @JsonIgnore
    private PostComment parent;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(name = "published", nullable = false)
    private Boolean published = false;

    @Column(name = "createdAt", nullable = false)
    private Instant createdAt;

    @Column(name = "publishedAt")
    private Instant publishedAt;

    @Lob //large object {clob, blob}
    @Column(name = "content")
    private String content;

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private Set<PostComment> postComments = new LinkedHashSet<>();


    public PostComment(Post post, PostComment parent, String title, User user, Boolean published, Instant createdAt, Instant publishedAt, String content, Set<PostComment> postComments) {
        this.post = post;
        this.parent = parent;
        this.title = title;
        this.user = user;
        this.published = published;
        this.createdAt = createdAt;
        this.publishedAt = publishedAt;
        this.content = content;
        this.postComments = postComments;
    }
}