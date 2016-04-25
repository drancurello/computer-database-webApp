package com.excilys.computerDatabase.models;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.service.ComputerService;

public class ComputerTest {

	@Autowired
	ComputerService computerService;
	
	@Test
	public void computerTest()
	{
		Computer computer = new Computer();
		
		computer.setName("iphone 6s");
		
		computer.setIntroducedTime(LocalDate.parse("2015-02-26"));
		
		Company company = new Company();
		company.setId(1);
		company.setName("Apple Inc.");
		
		computer.setCompany(company);
		
		int nbComputers = computerService.getNbComputers();
		
		assertEquals(nbComputers,computerService.findAllComputers().size());
		
		//computer = computerService.addComputer(computer);
		
		assertEquals(nbComputers+1,computerService.getNbComputers());
		
		computerService.deleteComputer(600);
		
		assertEquals(nbComputers+1,computerService.getNbComputers());
		
		assertEquals(LocalDate.parse("2015-02-26"),computer.getIntroducedTime());
		
		assertNull(computer.getDiscontinuedTime());
		
		computerService.findComputer(computer.getId());
		
		computer.setName("iphone 6");
		
		//computerService.updateComputer(computer);
		
		computerService.deleteComputer(computer.getId());
		
		assertEquals(nbComputers,computerService.getNbComputers());
		
	}
	
}
