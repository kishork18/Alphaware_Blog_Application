package com.blog.service;

import com.blog.dto.LoginDTO;
import com.blog.entity.User;
import com.blog.exception.UserException;

public interface AuthService {
	public User registerUser(User user) throws UserException;

	public String login(LoginDTO logindto) throws UserException;
}
