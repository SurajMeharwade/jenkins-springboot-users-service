package com.sony.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sony.model.User;

@Service
public class UserManager {

	private Map<String, User> users;
	public UserManager()
	{
		users = new LinkedHashMap<>();
		User user;
		
		user = new User("suraj","meharwade","suraj@xmpl.com","hubli");
		users.put(user.getId(),user);
		
		user = new User("adarsh","shanbag","suraj@xmpl.com","karwar");
		users.put(user.getId(),user);
		
		user = new User("gagan","ramgiri","gagan@xmpl.com","hubli");
		users.put(user.getId(),user);
	}
	
	public Iterable<User> getAllUsers()
	{
		return this.users.values();
	}
	
	public User getUserById(String id)
	{
		return this.users.get(id);
	}
}
