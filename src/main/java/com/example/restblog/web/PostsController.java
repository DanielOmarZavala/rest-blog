package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.dto.CreatePostDto;
import com.example.restblog.service.EmailService;
import com.example.restblog.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    private final PostService postService;
    private final EmailService emailService;

    public PostsController(PostService postService, EmailService emailService) {
        this.postService = postService;
        this.emailService = emailService;
    }

    @GetMapping() // /api/posts/
    private List<Post> getAll() {
        return postService.getPostList();
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable long id) {

        for (Post post : postService.getPostList()) {
            if (Objects.equals(post.getId(), id)) {
                return post;
            }
        }
        return null;
    }

    @PostMapping()
    private void createPost(@RequestBody Post postToAdd) {
        System.out.println(postToAdd);
    }

    @PostMapping("{username}")
    public void createByUsername(@PathVariable String username, @RequestBody CreatePostDto dto) {
        Post newPost = new Post();
        postService.addPost(dto, newPost, username);
        emailService.prepareAndSend(newPost, "New Post Created", "You've created a new post.");
    }

    @PutMapping("{id}")
    private void updatePost(@PathVariable long id, @RequestBody Post updatedPost) {
        postService.updatePost(id, updatedPost);
    }

    @DeleteMapping("{id}")
    private void deletePost(@PathVariable long id) {
        postService.deletePostById(id);
    }

    @GetMapping("search")
    public List<Post> searchPosts(@RequestParam String keyword) {
        return postService.getPostsByTitleKeyword(keyword);
    }
}
