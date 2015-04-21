package com.excilys.persistance.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.exception.DAOException;
import com.excilys.mapper.IMapper;
import com.excilys.model.Company;
import com.excilys.model.QCompany;
import com.mysema.query.jpa.hibernate.HibernateDeleteClause;
import com.mysema.query.jpa.hibernate.HibernateQuery;

@Repository
public class CompanyDAO implements IDAO<Company, Long> {

	@Autowired
	private IMapper<Company> companyMapper;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);

	@Override
	public Company getbyId(Long id) {
		
		if (id == null || id < 0) {
			throw new DAOException("CompanyDAO_getById - Invalid company id!");
		}
		
		HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		QCompany company = QCompany.company;
		Company result = query
							.from(company)
							.where(company.id.eq(id))
							.uniqueResult(company);
		return result;
	}

	@Override
	public List<Company> getAll() {		

		HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		QCompany company = QCompany.company;
		List<Company> companies = query
								  	.from(company)
								  	.list(company);
		return companies;
	}
	
	@Override
	public void delete(Long id) {	
	
		if (id == null || id < 0) {
			throw new DAOException("CompanyDAO_delete - Invalid company id!");
		}
		
		QCompany company = QCompany.company;

		new HibernateDeleteClause(sessionFactory.getCurrentSession(), company)
			.where(company.id.eq(id)).execute();
		
		LOGGER.info("Company {} successfully deleted", id);	
	}
}
