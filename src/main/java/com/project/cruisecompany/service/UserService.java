package com.project.cruisecompany.service;

import com.project.cruisecompany.model.User;

public interface UserService {
    void create(User user);
    User findByUsername(String username);
}
