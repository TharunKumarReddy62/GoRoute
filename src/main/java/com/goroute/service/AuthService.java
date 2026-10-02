package com.goroute.service;

import com.goroute.model.User;

public interface AuthService {

    User register(User user);

    User login(String email, String password);
}