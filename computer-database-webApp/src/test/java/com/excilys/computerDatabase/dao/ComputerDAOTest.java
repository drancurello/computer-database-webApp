package com.excilys.computerDatabase.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computerDatabase.exceptions.ConnectionException;
import com.excilys.computerDatabase.exceptions.DAOException;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring-Module.xml" })
public class ComputerDAOTest {
	
	@Autowired
	ComputerDAO computerDAO;
	
	@Test
	public void findById() throws DAOException, ConnectionException {
		assertEquals(computerDAO.find(574).getName(),"iPhone 4S");
	}
	
	@Test
	public void addUpdateDelete() throws DAOException, ConnectionException {
		Computer computer = new Computer();
		computer.setName("test");
		Company company = new Company();
		computer.setCompany(company);
		company.setId(1);
		company.setName("Apple Inc.");
		computer = computerDAO.add(computer);
		assertTrue(computer.getId() > 0);
		computer.setName("name");
		computer = computerDAO.update(computer);
		assertEquals(computer.getName(),"name");
		computerDAO.delete(computer.getId());
	}
}
