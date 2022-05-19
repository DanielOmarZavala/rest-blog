package com.example.restblog.web;

import com.example.restblog.data.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    List<Post> postObjectArrList = setPostList();

    @GetMapping() // /api/posts/
    private List<Post> getAll() {

        return postObjectArrList;
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable long id) {

        for (Post post : getAll()) {
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

    @PutMapping("{id}")
    private void updatePost(@PathVariable long id, @RequestBody Post updatedPost) {
        for (Post post : postObjectArrList) {
            if (post.getId().equals(id)) {
                post.setContent(updatedPost.getContent());
                post.setTitle(updatedPost.getTitle());
            }
        }
    }

    @DeleteMapping("{id}")
    private void deletePost(@PathVariable long id) {
        System.out.println("Deleted post with ID: " + id);
    }

    private List<Post> setPostList() {
        List<Post> postList = new ArrayList<>();

        Post objOne = new Post(1L, "Title 1", "Desc 1");
        Post objTwo = new Post(2L, "Title 2", "Desc 2");
        Post objThree = new Post(3L, "Title 3", "Desc 3");

        postList.add(objOne);
        postList.add(objTwo);
        postList.add(objThree);

        return postList;
    }
}
