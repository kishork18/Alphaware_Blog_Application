package com.blog.service.Impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exception.CommentException;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
	private CommentRepository commentRepo;
    @Autowired
    private PostRepository postRepo;

	@Override
	public Comment createComment(Comment comment, long postId) throws CommentException {
		Optional<Post> post=postRepo.findById(postId);
		if(!post.isPresent()) {
			throw new CommentException("Post not present");
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Post p=post.get();
		comment.setCreatedAt(LocalDateTime.now());
		comment.setPost(p);
		comment.setUser(user);
		Comment com= commentRepo.save(comment);
		return com;
	}

	@Override
	public Comment editComment(Comment comment) throws CommentException {
		Optional<Comment> com=commentRepo.findById(comment.getId());
		if(!com.isPresent()) {
			throw new CommentException("comment not found");
		}
		Comment c=com.get();
		c.setContent(comment.getContent());
		
		return commentRepo.save(c);
	}

	@Override
	public Comment deleteComment(long id) throws CommentException {
		Optional<Comment> com=commentRepo.findById(id);
		if(!com.isPresent()) {
			throw new CommentException("comment not present");
		}
		commentRepo.delete(com.get());
		return com.get();
	}

}
