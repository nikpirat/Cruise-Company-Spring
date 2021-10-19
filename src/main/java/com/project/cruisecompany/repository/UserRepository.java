package com.project.cruisecompany.repository;


import com.project.cruisecompany.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByName(String username);


}
