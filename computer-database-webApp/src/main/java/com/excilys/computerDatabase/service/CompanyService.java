package com.excilys.computerDatabase.service;

import java.util.List;

import com.excilys.computerDatabase.dao.CompanyDAO;
import com.excilys.computerDatabase.model.Company;

public class CompanyService { 
	
	private static CompanyDAO companyDAO = new CompanyDAO();
	
	public static List<Company> findAllCompanies()
	{
		return companyDAO.findAll();
	}
	
	public static Company findCompany(long id)
	{
		return companyDAO.find(id);
	}

}
