package com.example.restblog.service;

import com.example.restblog.data.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UsersRepository usersRepository;
    private final PostsRepository postsRepository;
    private final CategoriesRepository categoriesRepository;

    public UserService(UsersRepository usersRepository, PostsRepository postsRepository, CategoriesRepository categoriesRepository) {
        this.usersRepository = usersRepository;
        this.postsRepository = postsRepository;
        this.categoriesRepository = categoriesRepository;
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

        List<Category> categoriesToAdd = new ArrayList<>();

        for (Category category : newPost.getCategories()) {
            categoriesToAdd.add(categoriesRepository.findCategoryByName(category.getName()));
        }

        newPost.setCategories(categoriesToAdd);

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

    public List<Post> getPostsByTitleKeyword(String keyword) {
        return postsRepository.searchByTitleLike(keyword);
    }
}
