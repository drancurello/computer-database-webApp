package com.excilys.computerDatabase.mapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.parser.ParserInteger;
import com.excilys.computerDatabase.parser.ParserToLocalDate;

/**
 * The Class ComputerMapper.
 */
public class ComputerMapper {

	/**
	 * Convert a ResultSet to a computer.
	 *
	 * @param A resultSet
	 * @return A computer
	 * @throws SQLException
	 */
	public static Computer resultToComputer(ResultSet rs) throws SQLException {
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
	public static Computer requestToComputer(HttpServletRequest request) {
		Company company = new Company();
		company.setId(Integer.parseInt(request.getParameter("companyId")));
				
		return new Computer.Builder()
			   .idComputer(ParserInteger.parserInt(request.getParameter("id")))
			   .nameComputer(request.getParameter("computerName"))
			   .introducedComputer(ParserToLocalDate.parserLocalDate(request.getParameter("introduced")))
			   .discontinuedComputer(ParserToLocalDate.parserLocalDate(request.getParameter("discontinued")))
			   .companyComputer(company).build();
	}
	
	public static List<Computer> resultToComputersList(ResultSet rs) throws SQLException {
		List<Computer> computers = new ArrayList<>();
		
		while (rs.next()) {
			computers.add(resultToComputer(rs));
		}
		
		return computers;	
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
