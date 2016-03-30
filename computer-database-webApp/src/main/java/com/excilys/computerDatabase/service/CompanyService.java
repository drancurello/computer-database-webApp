package com.excilys.computerDatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDatabase.connection.ConnectionMySQL;
import com.excilys.computerDatabase.dao.CompanyDAO;
import com.excilys.computerDatabase.dao.ComputerDAO;
import com.excilys.computerDatabase.exceptions.ConnectionException;
import com.excilys.computerDatabase.exceptions.DAOException;
import com.excilys.computerDatabase.model.Company;

public class CompanyService { 
	
	private static CompanyDAO companyDAO = new CompanyDAO();
	private static ComputerDAO computerDAO = new ComputerDAO();
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);
	
	public static int delete(long id) throws SQLException{
		int cpany = 0, cputer = 0;
		Connection connection = null;
		
		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			connection.setAutoCommit(false);
			
			cputer = computerDAO.deleteByCompany(id);
			cpany = companyDAO.delete(id);
			
			connection.commit();
			
		} catch (SQLException | ConnectionException | DAOException e) {
			LOGGER.error("delete the company " + id + " failed cause " + e.getMessage());
			connection.rollback();
		} finally {
			ConnectionMySQL.CloseConnection(connection, null, null, null);
		}
		
		return cpany;
		
	}
	
	public static List<Company> findAllCompanies() {
		List<Company> companies = new ArrayList<>();
		try {
			companies = companyDAO.findAll();
		} catch (DAOException | ConnectionException e) {
			LOGGER.error("find all the companies failed cause " + e.getMessage());
		}
		return companies;
	}
	
	public static Company findCompany(long id) {
		Company company = null;
		try {
			company = companyDAO.find(id);
		} catch (DAOException | ConnectionException e) {
			LOGGER.error("find the company " + id + " failed cause " + e.getMessage());
		}
		return company;
	}
}
