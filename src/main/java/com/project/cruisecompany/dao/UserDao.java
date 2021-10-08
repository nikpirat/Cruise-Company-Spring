package com.project.cruisecompany.dao;


import com.project.cruisecompany.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findUserByName(String username);


}
