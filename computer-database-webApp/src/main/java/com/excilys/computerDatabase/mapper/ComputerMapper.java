package com.excilys.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
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
		Computer computer = new Computer();
		computer.setId(rs.getInt("id"));
		computer.setName(rs.getString("name"));
		if (rs.getDate("introduced") != null) {
			String introduced = rs.getDate("introduced").toString();
			computer.setIntroducedTime(LocalDate.parse(introduced));
		}
		if (rs.getDate("discontinued") != null) {
			String discontinued = rs.getDate("discontinued").toString();
			computer.setDiscontinuedTime(LocalDate.parse(discontinued));
		}
		Integer companyId = rs.getInt("company_id"); 

		if (companyId != null) {
			Company company = CompanyService.findCompany(companyId);
			computer.setCompany(company);
		}

		return computer;
	}
	
	/**
	 * Convert an HttpServletRequest to a Computer
	 * 
	 * @param an HttpServletRequest
	 * @return a Computer
	 */
	public static Computer requestToComputer(HttpServletRequest request) {
		Computer computer = new Computer();
		
		computer.setName(request.getParameter("computerName"));
		if (!request.getParameter("introduced").isEmpty()) {
			computer.setIntroducedTime(LocalDate.parse(request.getParameter("introduced")));
		}
		if (!request.getParameter("discontinued").isEmpty()) {
			computer.setDiscontinuedTime(LocalDate.parse(request.getParameter("discontinued")));
		}
		Company company = CompanyService.findCompany(Integer.parseInt(request.getParameter("companyId")));
		computer.setCompany(company);
		return computer;
	}
	

}
