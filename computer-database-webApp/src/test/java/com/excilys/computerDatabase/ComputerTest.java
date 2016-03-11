package com.excilys.computerDatabase;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.service.ComputerService;

public class ComputerTest {

	@Test
	public void computerTest()
	{
		Computer computer = new Computer("Iphone 6");
		
		computer.setIntroducedTime(LocalDate.parse("2015-02-26"));
		
		int nbComputers = ComputerService.getNbComputers();
		
		assertEquals(nbComputers,ComputerService.findAllComputers().size());
		
		computer = ComputerService.addComputer(computer);
		
		assertEquals(nbComputers+1,ComputerService.getNbComputers());
		
		ComputerService.deleteComputer(600);
		
		assertEquals(nbComputers+1,ComputerService.getNbComputers());
		
		assertEquals(LocalDate.parse("2015-02-26"),computer.getIntroducedTime());
		
		assertNull(computer.getDiscontinuedTime());
		
		ComputerService.findComputer(computer.getId());
		
		Company company = new Company(1);
		
		computer.setCompany(company);
		
		assertEquals(computer,ComputerService.updateComputer(computer));
		
		ComputerService.deleteComputer(computer.getId());
		
		assertEquals(nbComputers,ComputerService.getNbComputers());
		
		
		
	}
	
}
