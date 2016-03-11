package com.excilys.computerDatabase.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.connection.ConnectionMySQL;
import com.excilys.computerDatabase.model.Company;

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

	@Override
	public Company find(long id) {
		String query = "SELECT * FROM company WHERE id = " + id;

		ResultSet rs = null;
		;
		Statement stmt = null;
		Company company = new Company();

		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

			if (rs.next()) {
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionMySQL.CloseConnection(rs, stmt, null);
		}
		return company;
	}

	@Override
	public List<Company> findAll() {

		String query = "SELECT * FROM company";

		List<Company> companyList = new ArrayList<Company>();

		ResultSet rs = null;
		Statement stmt = null;

		try {
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

		ConnectionMySQL.CloseConnection(rs, stmt, null);
		return companyList;
	}

	@Override
	public List<Company> findPage(int nPage, int nComputer) {
		return null;
	}

}
