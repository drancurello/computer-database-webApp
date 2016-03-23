package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.connection.ConnectionMySQL;
import com.excilys.computerDatabase.exceptions.DAOConfigurationException;
import com.excilys.computerDatabase.model.Company;

/**
 * The Class CompanyDAO.
 */
public class CompanyDAO implements CrudService<Company> {
	
	@Override
	public Company add(Company obj) {
		return null;
	}

	@Override
	public Company update(Company obj) {
		return null;
	}

	@Override
	public int delete(long id) throws DAOConfigurationException {
		
		Connection connection = null;
		PreparedStatement prstmtComputer = null;
		PreparedStatement prstmtCompany = null;
		int cputer = 0;
		int cpany = 0;
		
		String deleteCoomputers = "DELETE FROM computer WHERE company_id = ?";
		String deleteCompany = "DELETE FROM company WHERE id = ?";
		
		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			connection.setAutoCommit(false);
			
			prstmtComputer = connection.prepareStatement(deleteCoomputers);
			prstmtComputer.setLong(1, id);
			cputer = prstmtComputer.executeUpdate();
			
			prstmtCompany = connection.prepareStatement(deleteCompany);
			prstmtCompany.setLong(1, id);
			cpany = prstmtCompany.executeUpdate();
			
			connection.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			ConnectionMySQL.CloseConnection(connection,null,null,prstmtCompany);
			ConnectionMySQL.CloseConnection(null,null,null,prstmtComputer);
		}
		
		if (cputer > 0 || cpany >0) {
			return 1;
		}
		return 0;
	}

	/**
	 * find a company by its id
	 * 
	 * @param the id of a company
	 * @return a company
	 * @throws DAOConfigurationException 
	 */
	@Override
	public Company find(long id) throws DAOConfigurationException {
		String query = "SELECT * FROM company WHERE id = " + id;

		ResultSet rs = null;
		Connection connection = null;
		Statement stmt = null;
		Company company = new Company();

		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

			if (rs.next()) {
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionMySQL.CloseConnection(connection,rs,stmt,null);
		}
		return company;
	}
	

	/** 
	 * find all the companies
	 * @return the list of all companies
	 * @throws DAOConfigurationException 
	 */
	@Override
	public List<Company> findAll() throws DAOConfigurationException {

		String query = "SELECT * FROM company";

		List<Company> companyList = new ArrayList<Company>();

		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;

		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				Company company = new Company();
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));

				companyList.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ConnectionMySQL.CloseConnection(connection,rs,stmt,null);
		return companyList;
	}

	@Override
	public List<Company> findPage(int nPage, int nComputer) {
		return null;
	}

}
