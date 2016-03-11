package com.excilys.computerDatabase;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.service.CompanyService;

public class CompanyTest {
	
	@Test
	public void companyTest()
	{
		Company company = CompanyService.findCompany(1);
		
		assertEquals(1,company.getId());
		
		assertEquals(new String("Apple Inc."),company.getName());
		
		int nbCompanies = CompanyService.findAllCompanies().size();
		
		List<Company> companies = new ArrayList<>();
		
		companies = CompanyService.findAllCompanies();
		
		assertEquals(nbCompanies,CompanyService.findAllCompanies().size());
		
		assertEquals(nbCompanies,companies.size());
		
	} 

}
