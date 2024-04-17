package com.blog.service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exception.PostException;
import com.blog.repository.CategoryRepository;
import com.blog.repository.PostRepository;

import com.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepo;
	@Autowired
	private CategoryRepository catServ;

	@Override
	public Post createPost(Post post) throws PostException {

		Optional<Category> cat = catServ.findByName(post.getCategory().getName());
		if (!cat.isPresent()) {
			throw new PostException("Selected Category is not found");
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		post.setUser(user);
		post.setCreatedAt(LocalDateTime.now());
		post.setCategory(cat.get());
		post.setComments(new ArrayList<>());

		return postRepo.save(post);
	}

	@Override
	public Post editPost(Post post) throws PostException {
		Optional<Post> postOpt = postRepo.findById(post.getId());
		if (!postOpt.isPresent()) {
			throw new PostException("Post Id is not Matching");
		}
		Post p = postOpt.get();
		p.setContent(post.getContent());
		p.setTitle(post.getTitle());
		return postRepo.save(p);
	}

	@Override
	public List<Post> findAll() throws PostException {
		List<Post> posts = postRepo.findAll();
		if (posts.isEmpty()) {
			throw new PostException("No Post Available");
		}
		return posts;
	}

	@Override
	public List<Post> findByUser(User user) throws PostException {
		List<Post> posts = postRepo.findByUser(user);
		if (posts.isEmpty()) {
			throw new PostException("No Post Available");
		}
		return posts;
	}

	@Override
	public List<Post> findByTitle(String title) throws PostException {
		List<Post> posts = postRepo.findByTitle(title);
		if (posts.isEmpty()) {
			throw new PostException("No Post Available");
		}
		return posts;
	}

	@Override
	public List<Post> findByCategory(String name) throws PostException {
		Optional<Category> cat= catServ.findByName(name);
		if(!cat.isPresent()) {
			throw new PostException("Category Not found");
		}
		return cat.get().getPosts();
	}

	@Override
	public Post deletePost(Long id) throws PostException {
		Optional<Post> post = postRepo.findById(id);
		if (!post.isPresent()) {
			throw new PostException("Post Not Found");
		}
		
		postRepo.deleteById(id);
		return post.get();
	}

}
