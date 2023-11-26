package vn.edu.iuh.fit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.models.Post;
import vn.edu.iuh.fit.response.ResponseObject;
import vn.edu.iuh.fit.services.PostService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "*")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<ResponseObject> findAllPost(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "success", postService.findAllPost())
        );
    }

    @GetMapping("/current-post-id")
    public long getCurrentPostID(){
        return postService.getCurrentPostID();
    }

    @PostMapping
    public Post createPost(@RequestBody Post post){
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        post.setPublishedAt(LocalDateTime.now());
        return postService.createPost(post).orElse(null);
    }
}
