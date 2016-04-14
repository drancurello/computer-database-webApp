package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDatabase.connection.ConnectionMySQL;
import com.excilys.computerDatabase.exceptions.ConnectionException;
import com.excilys.computerDatabase.exceptions.DAOException;
import com.excilys.computerDatabase.mapper.CompanyMapper;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.page.Page;

/**
 * The Class CompanyDAO.
 */
public class CompanyDAO implements ICrudService<Company> {
	
	private DataSource dataSource;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);
	private static final String DELETE_COMPANY = "DELETE FROM company WHERE id = ?";
	
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
		
		PreparedStatement prstmtCompany = null;
		int cpany = 0;
		
		try {
			Connection connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			
			prstmtCompany = connection.prepareStatement(DELETE_COMPANY);
			prstmtCompany.setLong(1, id);
			cpany = prstmtCompany.executeUpdate();
			
		}catch (SQLException e) {
			LOGGER.error("failure to delete the company caused by " + e.getMessage());
			throw new DAOException("delete failed",e);
		} finally {
			ConnectionMySQL.CloseConnection(null,null,null,prstmtCompany);
		}
		
		return cpany;
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
		String query = "SELECT * FROM company WHERE id = " + id;

		ResultSet rs = null;
		Connection connection = null;
		Statement stmt = null;
		Company company = new Company();

		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

			if (rs.next()) {
				company = CompanyMapper.resultToCompany(rs);
			}
		} catch (SQLException e) {
			LOGGER.error("failure to find the company caused by " + e.getMessage());
			throw new DAOException("find the company failed",e);
		} finally {
			ConnectionMySQL.CloseConnection(connection,rs,stmt,null);
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

		String query = "SELECT * FROM company";

		List<Company> companyList = new ArrayList<Company>();

		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;

		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

			companyList = CompanyMapper.resultToCompanies(rs);
			
		} catch (SQLException e) {
			LOGGER.error("failure to find all the companies caused by " + e.getMessage());
			throw new DAOException("find all companies failed",e);
		}

		ConnectionMySQL.CloseConnection(connection,rs,stmt,null);
		return companyList;
	}

	@Override
	public Page findPage(Page page) {
		return null;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
