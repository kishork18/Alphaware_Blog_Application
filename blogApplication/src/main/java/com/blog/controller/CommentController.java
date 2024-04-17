package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entity.Comment;
import com.blog.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	@Autowired
	private CommentService comServ;

	@PostMapping("/createcomment/{postId}")
	public ResponseEntity<Comment> createCommentHandler(@RequestBody Comment comment,
			@PathVariable("postId") long postId) {
		Comment com = comServ.createComment(comment, postId);
		return new ResponseEntity<Comment>(com, HttpStatus.CREATED);
	}

	@PutMapping("/editcomment")
	public ResponseEntity<Comment> editCommentHandler(@RequestBody Comment comment) {
		Comment com = comServ.editComment(comment);
		return new ResponseEntity<Comment>(com, HttpStatus.ACCEPTED);
	}
    @DeleteMapping("/delete")
	public ResponseEntity<Comment> deleteComment(long id) {
		Comment com = comServ.deleteComment(id);
		return new ResponseEntity<Comment>(com, HttpStatus.OK);
	}
}
