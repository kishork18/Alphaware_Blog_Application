package com.blog.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.blog.entity.RolesAndAuthority;
import com.blog.entity.User;
import com.blog.exception.UserException;
import com.blog.repository.RolesAndAuthorityRepository;
import com.blog.repository.UserRepository;
import com.blog.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository ur;
	@Autowired
	private RolesAndAuthorityRepository rar;

	@Override
	public User updateUser(User user) throws UserException {
		Optional<User> useropt = ur.findByEmail(user.getEmail());
		if (!useropt.isPresent()) {
			throw new UserException("Provided email is not register!");
		}
		User us=useropt.get();
		Set<RolesAndAuthority> set = user.getAuthSet();
		Set<RolesAndAuthority> managedSet = us.getAuthSet();
		for (RolesAndAuthority roles : set) {
			managedSet.add(rar.findByName(roles.getName()).get());
		}
	
		us.setAuthSet(managedSet);
		return ur.save(us);
	}

	@Override
	public List<User> findAllUser() throws UserException {
		List<User> list= ur.findAll();
		if(list.isEmpty()) {
			throw new UserException("No user any user found register");
		}
		return list;
	}

	@Override
	public User findByEmail(String email) throws UserException {
		Optional<User> user= ur.findByEmail(email);
		if(!user.isPresent()) {
			throw new UserException("user not found with "+email );
		}
		return user.get();
	}

	@Override
	@Transactional
	public User deleteUser(String email) throws UserException {
		Optional<User> user= ur.findByEmail(email);
		if(!user.isPresent()) {
			throw new UserException("user not found with "+email );
		}
		User us=user.get();
		us.getAuthSet().clear();
		ur.delete(us);
		return user.get();
	}

}
