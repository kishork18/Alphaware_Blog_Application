package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entity.User;
import com.blog.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userServ;

	@PutMapping("/update")
	public ResponseEntity<User> updateUserHandler(@RequestBody User user) {
        User us= userServ.updateUser(user);
        return new ResponseEntity<User>(us, HttpStatus.ACCEPTED);
	}
	@GetMapping("/getAll")
	public ResponseEntity<List<User>> findAllUserHandler(){
		List<User> list= userServ.findAllUser();
		return new ResponseEntity<List<User>>(list, HttpStatus.ACCEPTED);
	}
	@GetMapping("/findbyemail")
	public ResponseEntity<User> findByEmailHandler(@RequestParam("email") String email){
		User user= userServ.findByEmail(email);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<User> deleteUserHandler(@RequestParam("email") String email){
		User user= userServ.deleteUser(email);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
