package com.excilys.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.service.CompanyService;

public class ComputerMapper {

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

}
