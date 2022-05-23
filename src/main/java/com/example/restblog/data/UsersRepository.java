package com.example.restblog.data;

import org.springframework.data.jpa.repository.JpaRepository;

// TODO: need UsersRepository
public interface UsersRepository extends JpaRepository<User,Long> {

    User findByUsername(String username); // select * from users u where u.username = ?
}
