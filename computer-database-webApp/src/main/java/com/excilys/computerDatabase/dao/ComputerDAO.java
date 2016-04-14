package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.excilys.computerDatabase.connection.ConnectionMySQL;
import com.excilys.computerDatabase.exceptions.ConnectionException;
import com.excilys.computerDatabase.exceptions.DAOException;
import com.excilys.computerDatabase.mapper.ComputerMapper;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.page.Page;

/**
 * The Class ComputerDAO.
 */
public class ComputerDAO implements ICrudService<Computer> {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class);
	
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
	 * @throws ConnectionException
	 * @throws SQLException
	 */
	@Override
	public Computer add(Computer obj) throws DAOException, ConnectionException {

		LOGGER.info("add a computer");
		
		Connection connection = null;
		ResultSet resultId = null;
		PreparedStatement prepare = null;

		try {
			connection = dataSource.getConnection();
			prepare = connection.prepareStatement(INSERT_COMPUTER, Statement.RETURN_GENERATED_KEYS);
			
			ComputerMapper.fillPreparedStatement(obj, prepare);
			
			prepare.executeUpdate();
			resultId = prepare.getGeneratedKeys();

			if (resultId.next()) {
				obj.setId(resultId.getInt(1));
			}

		} catch (SQLException e) {
			LOGGER.error("failure to create a computer caused by " + e.getMessage());
			throw new DAOException("create the computer failed", e);
		} finally {
			ConnectionMySQL.CloseConnection(connection, resultId, null, prepare);
		}

		return obj;
	}

	/**
	 * update a computer in the DB
	 * 
	 * @param the new computer
	 * @throws ConnectionException
	 */
	@Override
	public Computer update(Computer obj) throws DAOException, ConnectionException {

		LOGGER.info("update a computer");
		
		Connection connection = null;
		PreparedStatement prepare = null;

		try {
			connection = dataSource.getConnection();
			prepare = connection.prepareStatement(UPDATE_COMPUTER);
			ComputerMapper.fillPreparedStatement(obj, prepare);
			prepare.setLong(5, obj.getId());		
			prepare.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failure to update a computer caused by " + e.getMessage());
			throw new DAOException("update the computer failed", e);
		} finally {
			ConnectionMySQL.CloseConnection(connection, null, null, prepare);
		}

		return obj;
	}

	/**
	 * delete a computer in the DB
	 * 
	 * @param the id of the computer
	 * @throws ConnectionException
	 * 
	 */
	@Override
	public int delete(long id) throws DAOException, ConnectionException {

		int result = 0;

		PreparedStatement prstmt = null;

		try {
			Connection connection = dataSource.getConnection();
			prstmt = connection.prepareStatement(DELETE_COMPUTER);
			prstmt.setLong(1, id);
			result = prstmt.executeUpdate();
			
		} catch (SQLException e) {
			LOGGER.error("failure to delete a computer caused by " + e.getMessage());
			throw new DAOException("delete the computer failed", e);
		} finally {
			ConnectionMySQL.CloseConnection(null, null, null, prstmt);
		}
		
		return result;

	}
	
	/**
	 * delete computers by its company id 
	 * 
	 * @param the id of the company
	 * @throws ConnectionException
	 * 
	 */
	public int deleteByCompany(long id) throws DAOException, ConnectionException {

		int result = 0;

		PreparedStatement prstmt = null;

		try {
			Connection connection = dataSource.getConnection();
			prstmt = connection.prepareStatement(DELETE_COMPUTER_BY_COMPANY);
			prstmt.setLong(1, id);
			result = prstmt.executeUpdate();
			
		} catch (SQLException e) {
			LOGGER.error("failure to delete a computer by its company id caused by " + e.getMessage());
			throw new DAOException("delete the computer by the compny failed", e);
		} 
		
		return result;

	}

	/**
	 * find a computer in the DB by its id
	 * 
	 * @param the id of the computer
	 * @return the computer found
	 * @throws ConnectionException
	 */
	@Override
	public Computer find(long id) throws DAOException, ConnectionException {

		ResultSet rs = null;
		PreparedStatement prstmt = null;
		Connection connection = null;
		Computer computer = null;

		try {
			connection = dataSource.getConnection();
			prstmt = connection.prepareStatement(FIND_COMPUTER);
			prstmt.setLong(1, id);
			rs = prstmt.executeQuery();
			if (rs.next()) {
				computer = ComputerMapper.resultToComputer(rs);
			}
		} catch (SQLException e) {
			LOGGER.error("failure to find the computer caused by " + e.getMessage());
			throw new DAOException("find the computer failed", e);
		} finally {
			ConnectionMySQL.CloseConnection(connection, rs, null, prstmt);
		}
		return computer;
	}

	/**
	 * Find by name.
	 *
	 * @param the name of the search, the page number and the number of result by page
	 * @return the list of all computers which contains the search in their name
	 * @throws ConnectionException
	 */
	public Page search(String search, Page page) throws DAOException, ConnectionException {

		int debut = page.getNbEntriesByPage() * (page.getPageNumber() - 1);
		String query = null;
		if (page.getOrder().equals("company")) {
			query = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE '%" + search
				+ "%' OR company_id IN ( SELECT id FROM company WHERE name LIKE '%" + search + "%') ORDER BY company.name " + page.getType() + " LIMIT " + debut + ","
				+ page.getNbEntriesByPage();
		} else {
		query = "SELECT * FROM computer WHERE name LIKE '%" + search + "%'"
				+ " OR company_id IN ( SELECT id FROM company WHERE name LIKE '%" + search + "%') ORDER BY " + page.getOrder() + " " + page.getType() + " LIMIT " + debut + ","
				+ page.getNbEntriesByPage();
		}
		
		ResultSet rs = null;
		Statement stmt = null;
		List<Computer> computers = new ArrayList<>();
		Connection connection = null;

		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				if (!computers.contains(ComputerMapper.resultToComputer(rs))) {
					computers.add(ComputerMapper.resultToComputer(rs));
				}		
			}
		} catch (SQLException e) {
			LOGGER.error("failure to return the search caused by " + e.getMessage());
			throw new DAOException("the search failed", e);
		} finally {
			ConnectionMySQL.CloseConnection(connection, rs, stmt, null);
		}
		
		page.setComputersList(computers);
		page.setNbComputers(getNbComputersSearch(search));
		
		return page;
	}

	/**
	 * 
	 * @param name of the search
	 * @return the number of result
	 * @throws ConnectionException
	 */
	public int getNbComputersSearch(String search) throws DAOException, ConnectionException {

		String query = "SELECT COUNT(*) FROM computer WHERE name LIKE '%" + search
				+ "%' OR company_id IN ( SELECT id FROM company WHERE name LIKE '%" + search + "%')";
		ResultSet rs = null;
		Statement stmt = null;
		Connection connection = null;
		int nbComputers = 0;

		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

			if (rs.next()) {
				nbComputers = rs.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			LOGGER.error("failure to return the number of computers for the search caused by " + e.getMessage());
			throw new DAOException("get the number of computers for the search failed", e);
		} finally {
			ConnectionMySQL.CloseConnection(connection, rs, stmt, null);
		}
		return nbComputers;
	}

	/**
	 * find all computers
	 * 
	 * @throws ConnectionException
	 */
	@Override
	public List<Computer> findAll() throws DAOException, ConnectionException {

		LOGGER.info("get all the computers");
		List<Computer> computerList = new ArrayList<>();

		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;

		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(FIND_ALL);

			computerList = ComputerMapper.resultToComputersList(rs);
			
		} catch (SQLException e) {
			LOGGER.error("failure to find all the computers caused by " + e.getMessage());
			throw new DAOException("find all failed", e);
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
	 * @throws ConnectionException
	 */
	@Override
	public Page findPage(Page page) throws DAOException, ConnectionException {

		int debut = page.getNbEntriesByPage() * (page.getPageNumber() - 1);
		String query = null;
		
		if (page.getOrder().equals("company")) {
			query = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id ORDER BY company.name " + page.getType() + " LIMIT " + debut + "," + page.getNbEntriesByPage();
		} else {
			query = "SELECT * FROM computer ORDER BY " + page.getOrder() + " " + page.getType() + " LIMIT " + debut + "," + page.getNbEntriesByPage();
		}
		List<Computer> computerList = new ArrayList<Computer>();

		ResultSet rs = null;
		Statement stmt = null;
		Connection connection = null;

		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

			computerList = ComputerMapper.resultToComputersList(rs);
			page.setComputersList(computerList);
			page.setNbComputers(getNbComputers());
		} catch (SQLException e) {
			LOGGER.error("failure to find the page caused by " + e.getMessage());
			throw new DAOException("find the page failed", e);
		} finally {
			ConnectionMySQL.CloseConnection(connection, rs, stmt, null);
		}
		return page;
	}

	/**
	 * Gets the number of computers.
	 *
	 * @return the number of computers
	 * @throws ConnectionException
	 */
	public int getNbComputers() throws DAOException, ConnectionException {

		ResultSet rs = null;
		Statement stmt = null;
		Connection connection = null;
		int nbEntries = 0;

		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(COUNT_COMPUTERS);
			if (rs.next()) {
				nbEntries = rs.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			LOGGER.error("failure to return the number of computers of the DB caused by " + e.getMessage());
			throw new DAOException("get the number of computers failed", e);
		} finally {
			ConnectionMySQL.CloseConnection(connection, rs, stmt, null);
		}
		
		return nbEntries;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

}
