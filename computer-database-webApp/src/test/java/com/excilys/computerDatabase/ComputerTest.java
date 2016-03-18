package com.excilys.computerDatabase;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.service.ComputerService;

public class ComputerTest {

	@Test
	public void computerTest()
	{
		ComputerDTO computer = new ComputerDTO();
		
		computer.setName("iphone 6s");
		
		computer.setIntroduced("2015-02-26");
		
		Company company = new Company();
		company.setId(1);
		company.setName("Apple Inc.");
		
		computer.setCompanyId(company.getId());
		computer.setCompanyName(company.getName());
		
		int nbComputers = ComputerService.getNbComputers();
		
		assertEquals(nbComputers,ComputerService.findAllComputers().size());
		
		computer = ComputerService.addComputer(computer);
		
		assertEquals(nbComputers+1,ComputerService.getNbComputers());
		
		ComputerService.deleteComputer(600);
		
		assertEquals(nbComputers+1,ComputerService.getNbComputers());
		
		assertEquals(LocalDate.parse("2015-02-26"),LocalDate.parse(computer.getIntroduced()));
		
		assertNull(computer.getDiscontinued());
		
		ComputerService.findComputer(computer.getId());
		
		computer.setName("iphone 6");
		
		ComputerService.updateComputer(computer);
		
		ComputerService.deleteComputer(computer.getId());
		
		assertEquals(nbComputers,ComputerService.getNbComputers());
		
	}
	
}
