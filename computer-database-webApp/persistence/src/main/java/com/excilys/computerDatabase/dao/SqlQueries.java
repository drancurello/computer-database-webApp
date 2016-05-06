package com.excilys.computerDatabase.dao;

public class SqlQueries {

	public static final String INSERT_COMPUTER = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES(?,?,?,?)";
	public static final String UPDATE_COMPUTER = "UPDATE Computer SET name = :name, introduced = :introduced, discontinued = :discontinued, company_id = :company_id  WHERE id = :id";
	public static final String DELETE_COMPUTER = "DELETE FROM Computer WHERE id = :id";
	public static final String DELETE_COMPUTER_BY_COMPANY = "DELETE FROM Computer WHERE company_id = :id";
	public static final String FIND_COMPUTER = "from Computer WHERE id = :id";
	public static final String FIND_ALL = "from Computer";
	public static final String COUNT_COMPUTERS = "SELECT COUNT(*) FROM Computer";
	
	public static final String DELETE_COMPANY = "DELETE FROM Company WHERE id = :id";
	public static final String FIND_ALL_COMPANIES = "from Company";
	public static final String FIND_COMPANY = "from Company WHERE id = :id";
	
	public static final String FIND_USER = "from UserInfo WHERE username = :username";
	
	public static String getNbComputerSearch(String search) {
		return "SELECT COUNT(*) FROM Computer WHERE name LIKE '%" + search + "%' OR company_id IN ( SELECT id FROM Company WHERE name LIKE '%" + search + "%')";
	}
	
	public static String findPage(String column, String order) {
		if (order.equals("company")) {
			return "from Computer as computer LEFT OUTER JOIN fetch computer.company as company ORDER BY company.name " + order + ", computer.name ASC";
		}
		else {
			return "from Computer ORDER BY " + column + " " + order;
		}
	}
	
	public static String search(String search, String column, String order) {
		if (order.equals("company")) {
			return "FROM Computer as computer LEFT OUTER JOIN fetch computer.company as company WHERE computer.name LIKE '%" + search
				+ "%' OR company_id IN ( SELECT id FROM Company WHERE name LIKE '%" + search + "%') ORDER BY company.name " + order + ", computer.name ASC";
		} else {
		return "FROM Computer WHERE name LIKE '%" + search + "%'"
				+ " OR company_id IN ( SELECT id FROM Company WHERE name LIKE '%" + search + "%') ORDER BY " + column + " " + order;
		}
	}
	
}
