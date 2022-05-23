package com.example.restblog.service;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> userList = setUserList();
    public List<Post> posts = setPostList();

    public List<User> getUsersList() {
        return userList;
    }

    public List<Post> getPostList() {
        return posts;
    }

    public void addPost(Post newPost, String username) {

        User user = getUserByUsername(username);

        user.getPosts().add(newPost);

        newPost.setUser(user);

        posts.add(newPost);
    }

    public User getUserById(Long id) {

        for (User user : userList) {
            if(user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        ;
        return null;
    }

    public void deletePostById(long id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                posts.remove(post);
                return;
            }
        }
    }

    private List<User> setUserList() {

        List<User> userList = new ArrayList<>();

        userList.add(new User(1L, "Ace Ventura", "ventura@petdetective.com", "SaveTheAnimal$"));
        userList.add(new User(2L, "The Dude", "duder@bowling.com", "whiterussian1234"));
        userList.add(new User(3L, "Franky", "frankyfrank@gmail.com", "60298347"));

        return userList;
    }

    private List<Post> setPostList() {

        List<Post> postList = new ArrayList<>();

        postList.add(new Post(1L, "Post 1", "Content Displayed Here", userList.get(0)));
        postList.add(new Post(2L, "Post 2", "Content Displayed Here", userList.get(1)));
        postList.add(new Post(3L, "Post 3", "Content Displayed Here", userList.get(0)));

        return postList;
    }
}
