package com.excilys.computerDatabase.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.dao.CompanyDAO;
import com.excilys.computerDatabase.exceptions.DAOConfigurationException;
import com.excilys.computerDatabase.model.Company;

public class CompanyService { 
	
	private static CompanyDAO companyDAO = new CompanyDAO();
	
	public static int delete(long id) {
		int del = 0;
		try {
			del = companyDAO.delete(id);
		} catch (DAOConfigurationException e) {
			e.printStackTrace();
		}
		
		return del;
	}
	
	public static List<Company> findAllCompanies() {
		List<Company> companies = new ArrayList<>();
		try {
			companies = companyDAO.findAll();
		} catch (DAOConfigurationException e) {
			System.err.println(e.getMessage());
		}
		return companies;
	}
	
	public static Company findCompany(long id) {
		Company company = null;
		try {
			company = companyDAO.find(id);
		} catch (DAOConfigurationException e) {
			System.err.println(e.getMessage());
		}
		return company;
	}
}
