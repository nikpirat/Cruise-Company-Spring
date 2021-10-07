package com.project.cruisecompany.service.impl;

import com.project.cruisecompany.dao.RoleDao;
import com.project.cruisecompany.dao.UserDao;
import com.project.cruisecompany.model.User;
import com.project.cruisecompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final RoleDao roleDao;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void create(User user) {
        
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }
}
