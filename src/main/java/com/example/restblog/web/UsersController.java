package com.example.restblog.web;

import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/users", headers = "Accept=application/json")
public class UsersController {

    private List<User> userList = setUserList();

    @GetMapping
    public List<User> getAll() {
        return userList;
    }

    @GetMapping("{id}")
    public User getById(@PathVariable Long id) {

        for (User user : userList) {
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    @GetMapping("username")
    public User getByUsername(@RequestParam String username) {

        System.out.println("Getting user with username: " + username);
        return null;
    }

    @GetMapping("email")
    public User getByEmail(@RequestParam String email) {
        System.out.println("Getting user with email: " + email);
        return null;
    }

    @PostMapping
    public void create(@RequestBody User newUser) {
        userList.add(newUser);
    }


    @PutMapping("{id}/updatePassword")
    public void updatePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword) {

        User userToUpdate = getById(id);
        userToUpdate.setPassword(newPassword);
        System.out.println(userToUpdate.getPassword());
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable Long id) {
        System.out.println("Deleting post with id: " + id);
    }

    private List<User> setUserList() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "Ace Ventura", "ventura@petdetective.com", "SaveTheAnimal$"));
        userList.add(new User(2L, "The Dude", "duder@bowling.com", "whiterussian1234"));
        userList.add(new User(3L, "Franky", "frankyfrank@gmail.com", "60298347"));
        return userList;
    }
}
