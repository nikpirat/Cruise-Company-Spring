package com.project.cruisecompany.service.impl;

import com.project.cruisecompany.dao.UserDao;
import com.project.cruisecompany.model.Role;
import com.project.cruisecompany.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findUserByName(s);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role: user.getRoles())
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(), grantedAuthorities);
    }
}
