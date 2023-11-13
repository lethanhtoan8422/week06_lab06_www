package vn.edu.iuh.fit.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import vn.edu.iuh.fit.models.Post;
import vn.edu.iuh.fit.models.PostComment;
import vn.edu.iuh.fit.models.User;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

public class ResponsePostComments {
    private Boolean published;
    private String createdAt;
    private Long id;
    private long parentID;
    private long postID;
    private String publishedAt;
    private User user;
    private String title;
    private String content;
    private long quantityCommentChildren;

    public ResponsePostComments() {
    }

    public ResponsePostComments(Boolean published, String createdAt, Long id, long parentID, long postID, String publishedAt, User user, String title, String content, long quantityCommentChildren) {
        this.published = published;
        this.createdAt = createdAt;
        this.id = id;
        this.parentID = parentID;
        this.postID = postID;
        this.publishedAt = publishedAt;
        this.user = user;
        this.title = title;
        this.content = content;
        this.quantityCommentChildren = quantityCommentChildren;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getParentID() {
        return parentID;
    }

    public void setParentID(long parentID) {
        this.parentID = parentID;
    }

    public long getPostID() {
        return postID;
    }

    public void setPostID(long postID) {
        this.postID = postID;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getQuantityCommentChildren() {
        return quantityCommentChildren;
    }

    public void setQuantityCommentChildren(long quantityCommentChildren) {
        this.quantityCommentChildren = quantityCommentChildren;
    }
}
