package com.excilys.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.model.Company;

@Component
public class CompanyMapper implements RowMapper<Company> {
	
	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		Company company = new Company();
		company.setId(rs.getInt("id"));
		company.setName(rs.getString("name"));
		
		return company;
	}
}
