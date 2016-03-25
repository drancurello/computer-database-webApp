package com.excilys.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.parser.ParserInteger;
import com.excilys.computerDatabase.parser.ParserToLocalDate;
import com.excilys.computerDatabase.service.CompanyService;

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
			company = CompanyService.findCompany(companyId);
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
		Company company = CompanyService.findCompany(Integer.parseInt(request.getParameter("companyId")));
				
		return new Computer.Builder()
			   .idComputer(ParserInteger.parserInt(request.getParameter("id")))
			   .nameComputer(request.getParameter("computerName"))
			   .introducedComputer(ParserToLocalDate.parserLocalDate(request.getParameter("introduced")))
			   .discontinuedComputer(ParserToLocalDate.parserLocalDate(request.getParameter("discontinued")))
			   .companyComputer(company).build();
	}
	

}
