package com.excilys.persistence.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.exception.DAOException;
import com.excilys.model.QRole;
import com.excilys.model.Role;
import com.mysema.query.jpa.hibernate.HibernateQuery;

@Repository
public class RoleDAO implements IDAO<Role, Long> {
	
//	@Autowired
//	private SessionFactory sessionFactory;
//	
//	@Override
//	public Role getbyId(Long id) {
//		
//		if (id == null || id < 0) {
//			throw new DAOException("RoleDAO_getById - Invalid role id!");
//		}
//		
//		HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
//		QRole role = QRole.role;
//		Role result = query
//							.from(role)
//							.where(role.id.eq(id))
//							.uniqueResult(role);
//		return result;
//	}
	

}
