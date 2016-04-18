package com.excilys.computerDatabase.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerDatabase.dao.CompanyDAO;
import com.excilys.computerDatabase.dao.ComputerDAO;
import com.excilys.computerDatabase.exceptions.ConnectionException;
import com.excilys.computerDatabase.exceptions.DAOException;
import com.excilys.computerDatabase.model.Company;

@Component
public class CompanyService {
	@Autowired
	private CompanyDAO companyDAO;
	@Autowired
	private ComputerDAO computerDAO;
	
	private DataSource dataSource;	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);
	
	@Transactional
	public int delete(long id) throws SQLException{
		int cpany = 0, cputer = 0;
		try {
			cputer = computerDAO.deleteByCompany(id);
			cpany = companyDAO.delete(id);	
		} catch (ConnectionException | DAOException e) {
			LOGGER.error("delete the company " + id + " failed cause " + e.getMessage());
		} 
		return cpany;
		
	}
	
	public List<Company> findAllCompanies() {
		List<Company> companies = new ArrayList<>();
		try {
			companies = companyDAO.findAll();
		} catch (DAOException | ConnectionException e) {
			LOGGER.error("find all the companies failed cause " + e.getMessage());
		}
		return companies;
	}
	
	public Company findCompany(long id) {
		Company company = null;
		try {
			company = companyDAO.find(id);
		} catch (DAOException | ConnectionException e) {
			LOGGER.error("find the company " + id + " failed cause " + e.getMessage());
		}
		return company;
	}
	
	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}
		
	public void setComputerDAO(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
