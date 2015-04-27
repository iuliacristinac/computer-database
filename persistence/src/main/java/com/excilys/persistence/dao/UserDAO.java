package com.excilys.persistence.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.exception.DAOException;
import com.excilys.model.QRole;
import com.excilys.model.QUser;
import com.excilys.model.Role;
import com.excilys.model.User;
import com.mysema.query.jpa.hibernate.HibernateQuery;

@Repository
public class UserDAO implements IDAO<User, Long> {
	
	@Autowired
	private SessionFactory sessionFactory;

	public User getByUsername(String username) {
		
		if (username == null || username.trim().isEmpty()) {
			throw new DAOException("UserDAO_getByUsername - Invalid username!");
		}
		
		HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		QUser user = QUser.user;
		User result = query
						.from(user)
						.where(user.username.eq(username))
						.uniqueResult(user);
		return result;
	}

	public List<Role> getRoles(String username){
		
		if (username == null || username.trim().isEmpty()) {
			throw new DAOException("UserDAO_getRoles - Invalid username!");
		}
		
		HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		QRole role = QRole.role1;
		List<Role> result = query
						.from(role)
						.where(role.user.username.eq(username))
						.list(role);
						
		return result;		
	}
}
