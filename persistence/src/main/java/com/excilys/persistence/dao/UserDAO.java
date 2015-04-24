package com.excilys.persistence.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.exception.DAOException;
import com.excilys.model.QUser;
import com.excilys.model.User;
import com.mysema.query.jpa.hibernate.HibernateQuery;

@Repository
public class UserDAO implements IDAO<User, Long> {
	
	@Autowired
	private SessionFactory sessionFactory;

//	@Override
//	public User getbyId(Long id) {	
//		
//		if (id == null || id < 0) {
//			throw new DAOException("UserDAO_getById - Invalid user id!");
//		}
//		
//		HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
//		QUser user = QUser.user;
//		User result = query
//						.from(user)
//						.where(user.id.eq(id))
//						.uniqueResult(user);
//		return result;
//	}

	
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

}
