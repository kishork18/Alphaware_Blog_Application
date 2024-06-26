package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;

public interface PostRepository extends JpaRepository<Post, Long> {
	public List<Post> findByUser(User user);

	public List<Post> findByTitle(String title);

	public List<Post> findByCategory(Category category);
}
