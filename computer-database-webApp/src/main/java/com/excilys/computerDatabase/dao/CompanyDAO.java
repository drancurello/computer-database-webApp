package com.excilys.computerDatabase.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.dao.interfaceDAO.ICompanyDAO;
import com.excilys.computerDatabase.exceptions.ConnectionException;
import com.excilys.computerDatabase.mapper.CompanyMapper;
import com.excilys.computerDatabase.model.Company;

/**
 * The Class CompanyDAO.
 */
@Component
public class CompanyDAO implements ICompanyDAO {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);

	/**
	 * @param the id of the company
	 * @return the number of companies deleted
	 * @throws ConnectionException 
	 */
	@Override
	public int delete(long id) {
		return jdbcTemplate.update(SqlQueries.DELETE_COMPANY, id);
	}

	/**
	 * find a company by its id
	 * 
	 * @param the id of a company
	 * @return a company
	 * @throws ConnectionException 
	 */
	@Override
	public Company find(long id){
		
		Company company = new Company();
		List<Company> companies = jdbcTemplate.query(SqlQueries.FIND_COMPANY,new Object[]{id}, new CompanyMapper());
		
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
	public List<Company> findAll(){
		return jdbcTemplate.query(SqlQueries.FIND_ALL_COMPANIES, new CompanyMapper());
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

}
