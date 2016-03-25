package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.connection.ConnectionMySQL;
import com.excilys.computerDatabase.exceptions.DAOConfigurationException;
import com.excilys.computerDatabase.mapper.ComputerMapper;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;

/**
 * The Class ComputerDAO.
 */
public class ComputerDAO implements ICrudService<Computer> {

	private String order = "name";
	private String type = "ASC";
	
	private static final String INSERT_COMPUTER = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES(?,?,?,?)";
	private static final String UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ?  WHERE id = ?";
	private static final String DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";
	private static final String DELETE_COMPUTER_BY_COMPANY = "DELETE FROM computer WHERE company_id = ?";
	private static final String FIND_COMPUTER = "SELECT * FROM computer WHERE id = ?";
	private static final String FIND_ALL = "SELECT * FROM computer";
	private static final String COUNT_COMPUTERS = "SELECT COUNT(*) FROM computer";
	
	/**
	 * add a computer in the DB
	 * 
	 * @param a computer
	 * @return the added computer
	 * @throws DAOConfigurationException
	 * @throws SQLException
	 */
	@Override
	public Computer add(Computer obj) throws DAOConfigurationException {

		Connection connection = null;
		ResultSet resultId = null;
		PreparedStatement prepare = null;

		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			prepare = connection.prepareStatement(INSERT_COMPUTER, Statement.RETURN_GENERATED_KEYS);
			prepare.setString(1, obj.getName());
			if (obj.getIntroducedTime() == null) {
				prepare.setString(2, null);
			} else {
				prepare.setDate(2, Date.valueOf(obj.getIntroducedTime()));
			}
			if (obj.getDiscontinuedTime() == null) {
				prepare.setString(3, null);
			} else {
				prepare.setDate(3, Date.valueOf(obj.getDiscontinuedTime()));
			}

			if (obj.getCompany() == null) {
				Company company = new Company();
				obj.setCompany(company);
				prepare.setString(4, null);
			} else {
				prepare.setLong(4, obj.getCompany().getId());
			}
			prepare.executeUpdate();
			resultId = prepare.getGeneratedKeys();

			if (resultId.next()) {
				obj.setId(resultId.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionMySQL.CloseConnection(connection, resultId, null, prepare);
		}

		return obj;
	}

	/**
	 * update a computer in the DB
	 * 
	 * @param the new computer
	 * @throws DAOConfigurationException
	 */
	@Override
	public Computer update(Computer obj) throws DAOConfigurationException {


		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			ps = connection.prepareStatement(UPDATE_COMPUTER);
			ps.setString(1, obj.getName());
			if (obj.getIntroducedTime() == null) {
				ps.setString(2, null);
			} else {
				ps.setDate(2, Date.valueOf(obj.getIntroducedTime()));
			}
			if (obj.getDiscontinuedTime() == null) {
				ps.setString(3, null);
			} else {
				ps.setDate(3, Date.valueOf(obj.getDiscontinuedTime()));
			}

			if (obj.getCompany() == null) {
				Company company = new Company();
				obj.setCompany(company);
				ps.setString(4, null);
			} else {
				ps.setLong(4, obj.getCompany().getId());
			}
			ps.setLong(5, obj.getId());
			
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionMySQL.CloseConnection(connection, null, null, ps);
		}

		return obj;
	}

	/**
	 * delete a computer in the DB
	 * 
	 * @param the id of the computer
	 * @throws DAOConfigurationException
	 * 
	 */
	@Override
	public int delete(long id) throws DAOConfigurationException {

		int result = 0;

		PreparedStatement prstmt = null;

		try {
			Connection connection = ConnectionMySQL.getInstance().getConnection();
			prstmt = connection.prepareStatement(DELETE_COMPUTER);
			prstmt.setLong(1, id);
			result = prstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionMySQL.CloseConnection(null, null, null, prstmt);
		}
		
		return result;

	}
	
	/**
	 * delete computers by its company id 
	 * 
	 * @param the id of the company
	 * @throws DAOConfigurationException
	 * 
	 */
	public int deleteByCompany(long id) throws DAOConfigurationException {

		int result = 0;

		PreparedStatement prstmt = null;

		try {
			Connection connection = ConnectionMySQL.getInstance().getConnection();
			prstmt = connection.prepareStatement(DELETE_COMPUTER_BY_COMPANY);
			prstmt.setLong(1, id);
			result = prstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return result;

	}

	/**
	 * find a computer in the DB by its id
	 * 
	 * @param the id of the computer
	 * @return the computer found
	 * @throws DAOConfigurationException
	 */
	@Override
	public Computer find(long id) throws DAOConfigurationException {

		ResultSet rs = null;
		PreparedStatement prstmt = null;
		Connection connection = null;

		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			prstmt = connection.prepareStatement(FIND_COMPUTER);
			prstmt.setLong(1, id);
			rs = prstmt.executeQuery();

			if (rs.next()) {
				Computer computer = new Computer();
				computer = ComputerMapper.resultToComputer(rs);
				return computer;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionMySQL.CloseConnection(connection, rs, null, prstmt);
		}
		return null;
	}

	/**
	 * Find by name.
	 *
	 * @param the name of the search, the page number and the number of result by page
	 * @return the list of all computers which contains the search in their name
	 * @throws DAOConfigurationException
	 */
	public List<Computer> search(String name, int pageNumber, int nComputer) throws DAOConfigurationException {

		int debut = nComputer * (pageNumber - 1);
		String query = null;
		if (order.equals("company")) {
			query = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE '%" + name
				+ "%' OR company_id IN ( SELECT id FROM company WHERE name LIKE '%" + name + "%') ORDER BY company.name " + type + " LIMIT " + debut + ","
				+ nComputer;
		} else {
		query = "SELECT * FROM computer WHERE name LIKE '%" + name + "%'"
				+ " OR company_id IN ( SELECT id FROM company WHERE name LIKE '%" + name + "%') ORDER BY " + order + " " + type + " LIMIT " + debut + ","
				+ nComputer;
		}
		
		ResultSet rs = null;
		Statement stmt = null;
		List<Computer> computers = new ArrayList<>();
		Connection connection = null;

		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				computers.add(ComputerMapper.resultToComputer(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionMySQL.CloseConnection(connection, rs, stmt, null);
		}
		return computers;
	}

	/**
	 * 
	 * @param name of the search
	 * @return the number of result
	 * @throws DAOConfigurationException
	 */
	public int getNbComputersSearch(String name) throws DAOConfigurationException {

		String query = "SELECT COUNT(*) FROM computer WHERE name LIKE '%" + name
				+ "%' OR company_id IN ( SELECT id FROM company WHERE name LIKE '%" + name + "%')";
		ResultSet rs = null;
		Statement stmt = null;
		Connection connection = null;
		int nbComputers = 0;

		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

			if (rs.next()) {
				nbComputers = rs.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionMySQL.CloseConnection(connection, rs, stmt, null);
		}
		return nbComputers;
	}

	/**
	 * find all computers
	 * 
	 * @throws DAOConfigurationException
	 */
	@Override
	public List<Computer> findAll() throws DAOConfigurationException {

		List<Computer> computerList = new ArrayList<Computer>();

		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;

		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(FIND_ALL);

			while (rs.next()) {
				Computer computer = new Computer();
				computer = ComputerMapper.resultToComputer(rs);
				computerList.add(computer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionMySQL.CloseConnection(connection, rs, stmt, null);
		}

		return computerList;
	}

	/**
	 * find the computers in a page
	 * 
	 * @param the page number and the number of computers by page
	 * @return the list of computers
	 * @throws DAOConfigurationException
	 */
	@Override
	public List<Computer> findPage(int nPage, int nComputer) throws DAOConfigurationException {

		int debut = nComputer * (nPage - 1);
		String query = null;
		
		if (order.equals("company")) {
			query = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id ORDER BY company.name " + type + " LIMIT " + debut + "," + nComputer;
		} else {
			query = "SELECT * FROM computer ORDER BY " + order + " " + type + " LIMIT " + debut + "," + nComputer;
		}
		List<Computer> computerList = new ArrayList<Computer>();

		ResultSet rs = null;
		Statement stmt = null;
		Connection connection = null;

		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				Computer computer = new Computer();
				computer = ComputerMapper.resultToComputer(rs);
				computerList.add(computer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionMySQL.CloseConnection(connection, rs, stmt, null);
		}
		return computerList;
	}

	/**
	 * Gets the number of computers.
	 *
	 * @return the number of computers
	 * @throws DAOConfigurationException
	 */
	public int getNbComputers() throws DAOConfigurationException {

		ResultSet rs = null;
		Statement stmt = null;
		Connection connection = null;
		int nbEntries = 0;

		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(COUNT_COMPUTERS);
			if (rs.next()) {
				nbEntries = rs.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionMySQL.CloseConnection(connection, rs, stmt, null);
		}

		return nbEntries;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setType(String type) {
		this.type = type;
	}

}
