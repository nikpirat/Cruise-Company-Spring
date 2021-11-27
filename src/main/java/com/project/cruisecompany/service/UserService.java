package com.project.cruisecompany.service;


import com.project.cruisecompany.model.User;

import java.util.List;

/**
 * Service class for {@link com.project.cruisecompany.model.User}
 *
 */

public interface UserService {

    void save(User user);

    User findByUsername(String username);

    User findById(Long id);

    List<User> findAll();

    void update(User user);
}
