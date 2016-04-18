package com.excilys.computerDatabase.mapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.parser.ParserInteger;
import com.excilys.computerDatabase.parser.ParserToLocalDate;

/**
 * The Class ComputerMapper.
 */
@Component
public class ComputerMapper implements RowMapper<Computer> {

	/**
	 * Convert a ResultSet to a computer.
	 *
	 * @param A resultSet
	 * @return A computer
	 * @throws SQLException
	 */
	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Company company = null;
		Integer companyId = rs.getInt("company_id"); 
		if (companyId != null) {
			company = new Company();
			company.setId(companyId);
		}
		
		return  new Computer.Builder().idComputer(rs.getInt("id"))
							.nameComputer(rs.getString("name"))
							.introducedComputer(ParserToLocalDate.parserLocalDate(rs.getDate("introduced")))
							.discontinuedComputer(ParserToLocalDate.parserLocalDate(rs.getDate("discontinued")))
							.companyComputer(company).build();
	}
	
	/**
	 * Convert an HttpServletRequest to a Computer
	 * 
	 * @param an HttpServletRequest
	 * @return a Computer
	 */
	public static Computer servletRequestToComputer(HttpServletRequest request) {
		Company company = new Company();
		company.setId(Integer.parseInt(request.getParameter("companyId")));
				
		return new Computer.Builder()
			   .idComputer(ParserInteger.parserInt(request.getParameter("id")))
			   .nameComputer(request.getParameter("computerName"))
			   .introducedComputer(ParserToLocalDate.parserLocalDate(request.getParameter("introduced")))
			   .discontinuedComputer(ParserToLocalDate.parserLocalDate(request.getParameter("discontinued")))
			   .companyComputer(company).build();
	}
	
	/**
	 * Convert an HttpServletRequest to a Computer
	 * 
	 * @param an HttpServletRequest
	 * @return a Computer
	 */
	public static Computer requestToComputer(Map<String,String> request) {
		Company company = new Company();
		company.setId(Integer.parseInt(request.get("companyId")));
				
		return new Computer.Builder()
			   .idComputer(ParserInteger.parserInt(request.get("id")))
			   .nameComputer(request.get("computerName"))
			   .introducedComputer(ParserToLocalDate.parserLocalDate(request.get("introduced")))
			   .discontinuedComputer(ParserToLocalDate.parserLocalDate(request.get("discontinued")))
			   .companyComputer(company).build();
	}
	
	public static void fillPreparedStatement(Computer obj, PreparedStatement prepare) throws SQLException {
		
		prepare.setString(1, obj.getName());
		
		if (obj.getIntroducedTime() == null) {
			prepare.setString(2, null);
		} else {
			prepare.setDate(2, Date.valueOf(obj.getIntroducedTime()));
		}
		
		if (obj.getDiscontinuedTime() == null) {
			prepare.setString(3, null);
		} else {
			prepare.setDate(3, Date.valueOf(obj.getDiscontinuedTime()));
		}

		if (obj.getCompany() == null) {
			Company company = new Company();
			obj.setCompany(company);
			prepare.setString(4, null);
		} else {
			prepare.setLong(4, obj.getCompany().getId());
		}
	}	
}
