package com.excilys.computerDatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.connection.ConnectionMySQL;
import com.excilys.computerDatabase.dao.CompanyDAO;
import com.excilys.computerDatabase.dao.ComputerDAO;
import com.excilys.computerDatabase.exceptions.DAOConfigurationException;
import com.excilys.computerDatabase.model.Company;

public class CompanyService { 
	
	private static CompanyDAO companyDAO = new CompanyDAO();
	private static ComputerDAO computerDAO = new ComputerDAO();
	
	public static int delete(long id) throws DAOConfigurationException, SQLException {
		int cpany = 0, cputer = 0;
		Connection connection = null;
		
		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			connection.setAutoCommit(false);
			
			cputer = computerDAO.deleteByCompany(id);
			cpany = companyDAO.delete(id);
			
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
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
		} catch (DAOConfigurationException e) {
			System.err.println(e.getMessage());
		}
		return companies;
	}
	
	public static Company findCompany(long id) {
		Company company = null;
		try {
			company = companyDAO.find(id);
		} catch (DAOConfigurationException e) {
			System.err.println(e.getMessage());
		}
		return company;
	}
}
