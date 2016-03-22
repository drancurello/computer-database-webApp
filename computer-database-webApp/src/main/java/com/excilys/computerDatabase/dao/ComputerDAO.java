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
public class ComputerDAO implements CrudService<Computer> {

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

		String query = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES(?,?,?,?)";

		Connection connection = null;
		ResultSet resultId = null;
		PreparedStatement prepare = null;

		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			prepare = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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

		String query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ?  WHERE id = "
				+ obj.getId();

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			ps = connection.prepareStatement(query);
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

		String query = "DELETE FROM computer WHERE id = " + id;
		int result = 0;

		Statement stmt = null;
		Connection connection = null;

		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			stmt = connection.createStatement();
			result = stmt.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionMySQL.CloseConnection(connection, null, stmt, null);
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

		String query = "SELECT * FROM computer WHERE id = " + id;

		ResultSet rs = null;
		Statement stmt = null;
		Connection connection = null;

		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

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
			ConnectionMySQL.CloseConnection(connection, rs, stmt, null);
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
		String query = "SELECT * FROM computer WHERE name LIKE '%" + name
				+ "%' OR company_id IN ( SELECT id FROM company WHERE name LIKE '%" + name + "%') LIMIT " + debut + ","
				+ nComputer;

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

		String query = "SELECT * FROM computer";

		List<Computer> computerList = new ArrayList<Computer>();

		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;

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
	 * find the computers in a page
	 * 
	 * @param the page number and the number of computers by page
	 * @return the list of computers
	 * @throws DAOConfigurationException
	 */
	@Override
	public List<Computer> findPage(int nPage, int nComputer) throws DAOConfigurationException {

		int debut = nComputer * (nPage - 1);

		String query = "SELECT * FROM computer ORDER BY id ASC LIMIT " + debut + "," + nComputer;

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
		String query = "SELECT COUNT(*) FROM computer";

		ResultSet rs = null;
		Statement stmt = null;
		Connection connection = null;
		int nbEntries = 0;

		try {
			connection = ConnectionMySQL.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
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
}
