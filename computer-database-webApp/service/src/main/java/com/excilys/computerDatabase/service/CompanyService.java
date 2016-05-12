package com.excilys.computerDatabase.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerDatabase.dao.CompanyDAO;
import com.excilys.computerDatabase.dao.ComputerDAO;

import com.excilys.computerDatabase.model.Company;

@Component
@Transactional
public class CompanyService {
	@Autowired
	private CompanyDAO companyDAO;
	@Autowired
	private ComputerDAO computerDAO;
			
	public int delete(long id) {
		int cpany = 0, cputer = 0;
		
		cputer = computerDAO.deleteByCompany(id);
		cpany = companyDAO.delete(id);	
		
		return cpany;
		
	}
	
	@Cacheable("companiesCache")
	public List<Company> findAllCompanies() {
		List<Company> companies = new ArrayList<>();
		companies = companyDAO.findAll();
		return companies;
	}
	
	@Cacheable("companyCache")
	public Company findCompany(long id) {
		Company company = null;
		company = companyDAO.find(id);
		return company;
	}
}
