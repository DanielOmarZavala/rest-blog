package com.example.restblog.web;

import com.example.restblog.data.Post;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    List<Post> postObjectArrList = new ArrayList<>();

    @GetMapping() // /api/posts/
    private List<Post> getAll() {

        Post objOne = new Post(1L, "Title 1", "Desc 1");
        Post objTwo = new Post(2L, "Title 2", "Desc 2");
        Post objThree = new Post(3L, "Title 3", "Desc 3");

        postObjectArrList.add(objOne);
        postObjectArrList.add(objTwo);
        postObjectArrList.add(objThree);

        return postObjectArrList;
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable long id) {

        return postObjectArrList.get((int) id);
    }

    @PostMapping()
    private void createPost(@RequestBody Post postToAdd) {
        System.out.println(postToAdd);
    }

    @PutMapping("{id}")
    private void updatePost(@PathVariable long id, @RequestBody Post updatedPost) {
        System.out.println(id);
    }

    @DeleteMapping("{id}")
    private void deletePost(@PathVariable long id) {
        System.out.println("Deleted id: " + id);
    }
}
