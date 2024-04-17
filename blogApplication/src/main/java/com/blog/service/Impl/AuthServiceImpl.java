package com.blog.service.Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blog.config.JwtTokenProvider;
import com.blog.dto.LoginDTO;
import com.blog.entity.RolesAndAuthority;
import com.blog.entity.User;
import com.blog.exception.UserException;
import com.blog.repository.RolesAndAuthorityRepository;
import com.blog.repository.UserRepository;
import com.blog.service.AuthService;
@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
    private UserRepository userRepo;
	@Autowired
    private RolesAndAuthorityRepository rar;
	@Autowired
	private AuthenticationManager authmanager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Override
	public User registerUser(User user) throws UserException {
		Optional<User> us= userRepo.findByEmail(user.getEmail());
		if(us.isPresent()) {
			throw new UserException("user already present with same email please try different email");
		}
		user.setPosts(new ArrayList<>());
	
		Set<RolesAndAuthority> managedSet = new HashSet<>();

		managedSet.add(rar.findByName("ROLE_USER").get());

		user.setAuthSet(managedSet);
		return userRepo.save(user);
	}

	@Override
	public String login(LoginDTO logindto) throws UserException {
		 Optional<User> user= userRepo.findByEmail(logindto.getEmail());
	        if(!user.isPresent()) {
	        	throw new UserException("Wrong Email please provide correct email");
	        }
	        if(user.isPresent()) {
	        	User u= user.get();
	        	
	        	if(!encoder.matches(logindto.getPassword(), u.getPassword())) {
	        		throw new UserException("Wrong password please provide currect password");
	        	}
	        }
	   
	        
			Authentication authentication = authmanager
					.authenticate(new UsernamePasswordAuthenticationToken(logindto.getEmail(), logindto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = jwtTokenProvider.generateToken(authentication);
			return token;
		}
	

}
