package com.excilys.persistance.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.mapper.IMapper;
import com.excilys.model.Company;
import com.excilys.model.QCompany;
import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
public class CompanyDAO implements IDAO<Company, Long> {

	@Autowired
	private IMapper<Company> companyMapper;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);

	@Override
	public Company getbyId(Long id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		JPAQuery query = new JPAQuery(entityManager);
		QCompany company = QCompany.company;
		Company result = query
							.from(company)
							.where(company.id.eq(id))
							.uniqueResult(company);
		
		entityManager.close();
		return result;
	}

	@Override
	public List<Company> getAll() {		

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		JPAQuery query = new JPAQuery(entityManager);
		QCompany company = QCompany.company;
		List<Company> companies = query
								  	.from(company)
								  	.list(company);
		entityManager.close();
		return companies;
	}
	
	@Override
	public void delete(Long id) {	
	
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		QCompany company = QCompany.company;

		new JPADeleteClause(entityManager, company)
			.where(company.id.eq(id)).execute();
		
		LOGGER.info("Company {} successfully deleted", id);	
	}
}
