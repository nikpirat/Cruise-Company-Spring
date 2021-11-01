package com.project.cruisecompany.service;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
