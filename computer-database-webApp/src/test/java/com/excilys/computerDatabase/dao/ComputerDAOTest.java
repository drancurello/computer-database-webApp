package com.excilys.computerDatabase.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import com.excilys.computerDatabase.service.ComputerService;

public class ComputerDAOTest {

	@Test
	public void findById() {
		assertEquals(ComputerService.findComputer(574).getName(),"iPhone 4S");
	}
	
	@Test
	public void findAll() {
		assertEquals(ComputerService.findAllComputers().size(),577);
	}
	
	
}
