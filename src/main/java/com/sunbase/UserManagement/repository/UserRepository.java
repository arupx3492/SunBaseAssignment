package com.sunbase.UserManagement.repository;

import com.sunbase.UserManagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    User findByEmail(String email);
}
