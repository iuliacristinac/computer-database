package com.excilys.persistance.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.mapper.IMapper;
import com.excilys.model.Company;

@Repository
public class CompanyDAO implements IDAO<Company, Long> {

	@Autowired
	private IMapper<Company> companyMapper;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);
	
	private static final String GET_BY_ID_SQL = "SELECT * FROM company WHERE id = ?";
	private static final String GET_ALL_SQL = "SELECT * FROM company";
	private static final String DELETE_SQL = "DELETE FROM company WHERE id = ?";
	
	@Override
	public Company getbyId(Long id) {	
		return jdbcTemplate.queryForObject(GET_BY_ID_SQL, new Object[] { id }, companyMapper);
	}

	@Override
	public List<Company> getAll() {		
		return jdbcTemplate.query(GET_ALL_SQL, companyMapper);
	}
	
	@Override
	public void delete(Long id) {	
	
		jdbcTemplate.update(DELETE_SQL, id);
		LOGGER.info("Company {} successfully deleted", id);	
	}
}
