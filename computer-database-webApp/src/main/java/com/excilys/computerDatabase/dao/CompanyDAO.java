package com.excilys.computerDatabase.dao;

import java.sql.Connection;
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
	public int delete(long id) {
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
