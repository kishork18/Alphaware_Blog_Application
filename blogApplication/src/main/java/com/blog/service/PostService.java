package com.blog.service;

import java.util.List;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exception.PostException;

public interface PostService {
	public Post createPost(Post post)throws PostException;

	public Post editPost(Post post) throws PostException;

	public List<Post> findAll() throws PostException;

	public List<Post> findByUser(User user) throws PostException;

	public List<Post> findByTitle(String title) throws PostException;

	public List<Post> findByCategory(String name) throws PostException;

	public Post deletePost(Long id) throws PostException;

}
