package vn.edu.iuh.fit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.models.PostComment;
import vn.edu.iuh.fit.repositories.PostRepository;
import vn.edu.iuh.fit.response.ResponsePostComments;
import vn.edu.iuh.fit.services.PostCommentService;
import vn.edu.iuh.fit.services.PostService;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/post-comment")
@CrossOrigin(origins = "*")
public class PostCommentController {
    @Autowired
    private PostCommentService postCommentService;
    @Autowired
    private PostService postService;

    @GetMapping("/by-post-{id}")
    public List<ResponsePostComments> getPostCommentsByPostID(@PathVariable("id") long id){
        return postCommentService.getPostCommentsByPostID(id);
    }

    @GetMapping("/parent-{parentID}")
    public List<ResponsePostComments> getPostCommentsByPostIDAndParentID(@PathVariable("parentID") long parentID){
        return postCommentService.getPostCommentsByPostIDAndParentID(parentID);
    }

    @GetMapping("/current-post-comment-id")
    public long getCurrentPostCommentID(){
        return postCommentService.getCurrentPostCommentID();
    }

    @PostMapping("/{id}")
    public boolean createPostComment(@PathVariable("id") long id, @RequestBody PostComment postComment){
        postComment.setPost(postService.findByID(id));
        postComment.setCreatedAt(Instant.now());
        postComment.setPublishedAt(Instant.now());
        postCommentService.createPostComment(postComment);
        return true;
    }

    @PostMapping("/{id}/{parentID}")
    public boolean createPostCommentChild(@PathVariable("id") long id, @PathVariable("parentID") long parentID, @RequestBody PostComment postComment){
        postComment.setPost(postService.findByID(id));
        postComment.setParent(postCommentService.findByID(parentID).get());
        postComment.setCreatedAt(Instant.now());
        postComment.setPublishedAt(Instant.now());
        postCommentService.createPostComment(postComment);
        return true;
    }
}
