package com.excilys.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.model.Company;

public class CompanyMapper {
	
	public static Company resultToCompany(ResultSet rs) throws SQLException {
		Company company = new Company();
		company.setId(rs.getInt("id"));
		company.setName(rs.getString("name"));
		
		return company;
	}
	
	public static List<Company> resultToCompanies(ResultSet rs) throws SQLException {
		List<Company> companies	= new ArrayList<>();
		
		while (rs.next()) {
			companies.add(resultToCompany(rs));
		}
		
		return companies;	
	}
	
}
