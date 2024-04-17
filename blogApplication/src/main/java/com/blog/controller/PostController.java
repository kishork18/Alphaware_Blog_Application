package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	@Autowired
	private PostService postServ;

	@PostMapping("/createpost")
	public ResponseEntity<Post> createPostHandler(@RequestBody Post post) {
		Post p = postServ.createPost(post);
		return new ResponseEntity<Post>(p, HttpStatus.CREATED);
	}
	@PutMapping("/editpost")
	public ResponseEntity<Post> editPostHandler(@RequestBody Post post){
		Post p = postServ.editPost(post);
		return new ResponseEntity<Post>(p, HttpStatus.CREATED);
	}
	@GetMapping("/feed")
	public ResponseEntity<List<Post>> getAllPostHandler(){
		List<Post> list= postServ.findAll();
		return new ResponseEntity<List<Post>>(list, HttpStatus.OK);
	}
	@GetMapping("/search")
	public ResponseEntity<List<Post>> findByNameHandler(@RequestParam("title") String title){
		List<Post> list= postServ.findByTitle(title);
		return new ResponseEntity<List<Post>>(list, HttpStatus.OK);
	}
	@GetMapping("/findbycategory") 
	public ResponseEntity<List<Post>> findByCategoryHandler(@RequestParam("category") String name){
		List<Post> list= postServ.findByCategory(name);
		return new ResponseEntity<List<Post>>(list, HttpStatus.OK);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<Post> deleteHandler(@RequestParam("postId") Long id){
		Post post= postServ.deletePost(id);
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}

}
