package com.excilys.computerDatabase;

import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.exceptions.ConnectionException;
import com.excilys.computerDatabase.exceptions.DAOException;
import com.excilys.computerDatabase.model.UserInfo;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.service.UserService;

/**
 * Unit test for simple App.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:contextService.xml" })
public class serviceTest {

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	ComputerService computerService;
	
	@Autowired
	UserService userService;
	
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
