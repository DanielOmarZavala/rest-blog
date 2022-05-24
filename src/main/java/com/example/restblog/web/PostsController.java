package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    private final UserService userService;

    public PostsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping() // /api/posts/
    private List<Post> getAll() {
        return userService.getPostList();
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable long id) {

        for (Post post : userService.getPostList()) {
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
    public void createByUsername(@PathVariable String username, @RequestBody Post newPost) {
        userService.addPost(newPost, username);
    }

    @PutMapping("{id}")
    private void updatePost(@PathVariable long id, @RequestBody Post updatedPost) {
        userService.updatePost(id, updatedPost);
    }

    @DeleteMapping("{id}")
    private void deletePost(@PathVariable long id) {
        userService.deletePostById(id);
    }

}
