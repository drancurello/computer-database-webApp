package com.excilys.computerDatabase.dao;

import com.excilys.computerDatabase.page.Page;

public class SqlQueries {

	public static final String INSERT_COMPUTER = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES(?,?,?,?)";
	public static final String UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ?  WHERE id = ?";
	public static final String DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";
	public static final String DELETE_COMPUTER_BY_COMPANY = "DELETE FROM computer WHERE company_id = ?";
	public static final String FIND_COMPUTER = "SELECT * FROM computer WHERE id = ?";
	public static final String FIND_ALL = "SELECT * FROM computer";
	public static final String COUNT_COMPUTERS = "SELECT COUNT(*) FROM computer";
	public static final String GETNBCOMPUTERSEARCH = "SELECT COUNT(*) FROM computer WHERE name LIKE '%" + "?" + "%' OR company_id IN ( SELECT id FROM company WHERE name LIKE '%" +"?"+"%')";
	public static final String FINDPAGEORDERCOMP = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id ORDER BY company.name ? LIMIT ?, ?"; 
	public static final String FINDPAGE = "SELECT * FROM computer ORDER BY ? ?";
	public static final String SEARCHORDERCOMP = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE '%?%' OR company_id IN ( SELECT id FROM company WHERE name LIKE '%?%') ORDER BY company.name ? LIMIT ?, ?";
	
	public static final String DELETE_COMPANY = "DELETE FROM company WHERE id = ?";
	public static final String FIND_ALL_COMPANIES = "SELECT * FROM company";
	public static final String FIND_COMPANY = "SELECT * FROM company WHERE id = ?";
	
	public static String getNbComputerSearch(String search) {
		return "SELECT COUNT(*) FROM computer WHERE name LIKE '%" + search + "%' OR company_id IN ( SELECT id FROM company WHERE name LIKE '%" + search + "%')";
	}
	
	
	public static String findPage(Page page) {
		if (page.getOrder().equals("company")) {
			return "from Computer LEFT JOIN company ON computer.company_id = company.id ORDER BY company.name " + page.getType();
		}
		else {
			return "from Computer ORDER BY " + page.getOrder() + " " + page.getType();
		}
	}
	
	public static String search(Page page, String search, int debut) {
		if (page.getOrder().equals("company")) {
			return "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE '%" + search
				+ "%' OR company_id IN ( SELECT id FROM company WHERE name LIKE '%" + search + "%') ORDER BY company.name " + page.getType() + " LIMIT " + debut + ","
				+ page.getNbEntriesByPage();
		} else {
		return "SELECT * FROM computer WHERE name LIKE '%" + search + "%'"
				+ " OR company_id IN ( SELECT id FROM company WHERE name LIKE '%" + search + "%') ORDER BY " + page.getOrder() + " " + page.getType() + " LIMIT " + debut + ","
				+ page.getNbEntriesByPage();
		}
	}
	
}
