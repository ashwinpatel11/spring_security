package com.example.webosmotic.repository;


import com.example.webosmotic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
   // public Employee findByEmail(String email);
    public User findByEmail(String email);
}
