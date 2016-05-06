package com.excilys.computerDatabase.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.model.UserInfo;

@Component
public class UserDAO {
	
	@Autowired 
	private SessionFactory factory;
	
	public UserInfo getUserInfo(String username) {
		Query query = factory.getCurrentSession().createQuery(SqlQueries.FIND_USER);
		query.setParameter("username", username);
		UserInfo user = (UserInfo) query.list().get(0);
		
		return user;
	}
	
	public UserInfo addUserInfo(UserInfo user) {
		factory.getCurrentSession().save(user);
		
		return user;
	}
	
}
