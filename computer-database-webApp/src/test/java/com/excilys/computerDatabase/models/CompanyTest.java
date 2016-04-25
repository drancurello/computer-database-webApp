package com.excilys.computerDatabase.models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.service.CompanyService;

public class CompanyTest {
	
	@Autowired
	CompanyService companyService;
	
	@Test
	public void companyTest()
	{
		Company company = companyService.findCompany(1);
		
		assertEquals(1,company.getId());
		
		assertEquals("Apple Inc.",company.getName());
		
		int nbCompanies = companyService.findAllCompanies().size();
		
		List<Company> companies = new ArrayList<>();
		
		companies = companyService.findAllCompanies();
		
		assertEquals(nbCompanies,companyService.findAllCompanies().size());
		
		assertEquals(nbCompanies,companies.size());
		
	} 

}
