package com.excilys.computerDatabase.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.exceptions.ConnectionException;
import com.excilys.computerDatabase.exceptions.DAOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring-Module.xml" })
public class computerServiceTest {
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	ComputerService computerService;
	
	@Test
	public void addUpdateDelete() throws DAOException, ConnectionException {
		ComputerDTO.Builder builder = new ComputerDTO.Builder();
		builder.nameComputer("test");
		builder.companyComputer(1, "Apple Inc.");
		ComputerDTO computerDTO = builder.build();
		computerDTO = computerService.addComputer(computerDTO);
		assertTrue(computerDTO.getId() > 0);
		computerDTO.setName("name");
		computerDTO = computerService.updateComputer(computerDTO);
		assertEquals(computerDTO.getName(),"name");
		computerService.deleteComputer(computerDTO.getId());
	}
	
	@Test
	public void getNBComputers() throws DAOException, ConnectionException {
		assertEquals(computerService.getNbComputers(),574);
	}
	
	@Test
	public void findComputer() throws DAOException, ConnectionException {
		assertEquals(computerService.findComputer(574).getName(),"iPhone 4S");
	}
	
	
}
