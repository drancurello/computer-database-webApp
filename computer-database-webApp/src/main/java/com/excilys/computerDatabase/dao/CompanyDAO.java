package com.excilys.computerDatabase.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.exceptions.ConnectionException;
import com.excilys.computerDatabase.exceptions.DAOException;
import com.excilys.computerDatabase.mapper.CompanyMapper;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.page.Page;

/**
 * The Class CompanyDAO.
 */
@Component
public class CompanyDAO implements ICrudService<Company> {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);
	private static final String DELETE_COMPANY = "DELETE FROM company WHERE id = ?";
	private static final String FIND_ALL = "SELECT * FROM company";
	private static final String FIND = "SELECT * FROM company WHERE id = ?";
	
	@Override
	public Company add(Company obj) {
		return null;
	}

	@Override
	public Company update(Company obj) {
		return null;
	}

	/**
	 * @param the id of the company
	 * @return the number of companies deleted
	 * @throws ConnectionException 
	 */
	@Override
	public int delete(long id) throws DAOException, ConnectionException {
		return jdbcTemplate.update(DELETE_COMPANY, id);
	}

	/**
	 * find a company by its id
	 * 
	 * @param the id of a company
	 * @return a company
	 * @throws ConnectionException 
	 */
	@Override
	public Company find(long id) throws DAOException, ConnectionException {
		
		Company company = new Company();
		List<Company> companies = jdbcTemplate.query(FIND,new Object[]{id}, new CompanyMapper());
		
		if (companies.size() != 0) {
			return companies.get(0);
		}
		return company;
	}
	

	/** 
	 * find all the companies
	 * @return the list of all companies
	 * @throws ConnectionException 
	 */
	@Override
	public List<Company> findAll() throws DAOException, ConnectionException {
		return jdbcTemplate.query(FIND_ALL, new CompanyMapper());
	}

	@Override
	public List<Company> findPage(Page page) {
		return null;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

}
