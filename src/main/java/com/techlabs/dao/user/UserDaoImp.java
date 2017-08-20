package com.techlabs.dao.user;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techlabs.dao.UserDao;
import com.techlabs.entity.User;

@Repository
public class UserDaoImp implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return (User) sessionFactory.getCurrentSession().get(User.class, userName);
	}

	@Override
	public void saveUser(User user) {
		sessionFactory.getCurrentSession().update(user);
		
	}

	
}
