package com.example.restblog.service;

import com.example.restblog.data.Post;
import com.example.restblog.data.PostsRepository;
import com.example.restblog.data.User;
import com.example.restblog.data.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UsersRepository usersRepository;
    private final PostsRepository postsRepository;

    public UserService(UsersRepository usersRepository, PostsRepository postsRepository) {
        this.usersRepository = usersRepository;
        this.postsRepository = postsRepository;
    }

    public List<User> getUsersList() {
        return usersRepository.findAll();
    }

    public List<Post> getPostList() {
        return postsRepository.findAll();
    }

    public void addPost(Post newPost, String username) {

        User user = getUserByUsername(username);

        user.getPosts().add(newPost);

        newPost.setUser(user);

        postsRepository.save(newPost);
    }

    public User getUserById(Long id) {

        return usersRepository.findById(id).orElseThrow();
    }

    public User getUserByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    public void updatePost(long postId, Post post) {
        Post postToUpdate = postsRepository.findById(postId).orElseThrow();

        if (post.getContent() != null && !post.getContent().isEmpty()) {
            postToUpdate.setContent(post.getContent());
        }
        if (post.getTitle() != null && !post.getTitle().isEmpty()) {
            postToUpdate.setTitle(post.getTitle());
        }

        postsRepository.save(postToUpdate);
    }

    public void deletePostById(long id) {
        postsRepository.deleteById(id);
    }

//    private List<User> setUserList() {
//
//        List<User> userList = new ArrayList<>();
//
//        userList.add(new User(1L, "Ace Ventura", "ventura@petdetective.com", "SaveTheAnimal$"));
//        userList.add(new User(2L, "The Dude", "duder@bowling.com", "whiterussian1234"));
//        userList.add(new User(3L, "Franky", "frankyfrank@gmail.com", "60298347"));
//
//        return userList;
//    }
//
//    private List<Post> setPostList() {
//
//        List<Post> postList = new ArrayList<>();
//
//        postList.add(new Post(1L, "Post 1", "Content Displayed Here", userList.get(0)));
//        postList.add(new Post(2L, "Post 2", "Content Displayed Here", userList.get(1)));
//        postList.add(new Post(3L, "Post 3", "Content Displayed Here", userList.get(0)));
//
//        return postList;
//    }
}
