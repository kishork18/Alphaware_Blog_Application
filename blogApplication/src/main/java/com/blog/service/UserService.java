package com.blog.service;

import java.util.List;

import com.blog.entity.User;
import com.blog.exception.UserException;

public interface UserService {
	public User updateUser(User user) throws UserException;

	public List<User> findAllUser() throws UserException;

	public User findByEmail(String email) throws UserException;

	public User deleteUser(String email) throws UserException;
}
