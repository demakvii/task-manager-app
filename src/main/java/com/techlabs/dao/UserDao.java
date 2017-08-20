package com.techlabs.dao;

import org.springframework.stereotype.Repository;

import com.techlabs.entity.User;

@Repository
public interface UserDao {

	public User getUserByUserName(String userName);
	public void saveUser(User user);
}
