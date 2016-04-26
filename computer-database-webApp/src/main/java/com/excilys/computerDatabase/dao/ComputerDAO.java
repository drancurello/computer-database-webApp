package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.dao.interfaceDAO.IComputerDAO;
import com.excilys.computerDatabase.exceptions.ConnectionException;
import com.excilys.computerDatabase.mapper.ComputerMapper;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.page.Page;

/**
 * The Class ComputerDAO.
 */
@Component
@SuppressWarnings("unchecked")
public class ComputerDAO implements IComputerDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired 
	private SessionFactory factory;
	
	/**
	 * add a computer in the DB
	 * @param a computer
	 * @return the added computer
	 * @throws ConnectionException
	 * @throws SQLException
	 */
	@Override
	public Computer add(Computer obj){

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
	    	    new PreparedStatementCreator() {
	    	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
	    	            PreparedStatement pstmt = con.prepareStatement(SqlQueries.INSERT_COMPUTER, new String[] {"id"});
	    	            ComputerMapper.fillPreparedStatement(obj, pstmt);
	    	            return pstmt;
	    	        }
	    	    },
	    	    keyHolder);
		obj.setId((long)keyHolder.getKey());			
		return obj;
	}

	/**
	 * update a computer in the DB
	 * @param the new computer
	 * @throws ConnectionException
	 */
	@Override
	public Computer update(Computer obj){
		jdbcTemplate.update(SqlQueries.UPDATE_COMPUTER,new Object[]{obj.getName(),obj.getIntroducedTime(),obj.getDiscontinuedTime(),obj.getCompany().getId(),obj.getId()});
		return obj;
	}

	/**
	 * delete a computer in the DB
	 * @param the id of the computer
	 * @throws ConnectionException
	 */
	@Override
	public int delete(long id){
		return jdbcTemplate.update(SqlQueries.DELETE_COMPUTER, id);
	}
	
	/**
	 * delete computers by its company id 
	 * @param the id of the company
	 * @throws ConnectionException
	 */
	public int deleteByCompany(long id){
		return jdbcTemplate.update(SqlQueries.DELETE_COMPUTER_BY_COMPANY, id);
	}

	/**
	 * find a computer in the DB by its id
	 * @param the id of the computer
	 * @return the computer found
	 * @throws ConnectionException
	 */
	@Override
	public Computer find(long id){	
		Computer computer = null;
		
		List<Computer> computers = jdbcTemplate.query(SqlQueries.FIND_COMPUTER,new Object[]{id}, new ComputerMapper());
		
		if (!computers.isEmpty()) {
			return computers.get(0);
		}
		
		return computer;
	}

	/**
	 * Find by name.
	 * @param the name of the search, the page number and the number of result by page
	 * @return the list of all computers which contains the search in their name
	 * @throws ConnectionException
	 */
	public List<Computer> search(String search, Page page){

		int debut = page.getNbEntriesByPage() * (page.getPageNumber() - 1);	
		List<Computer> computers = new ArrayList<>();

		computers = jdbcTemplate.query(SqlQueries.search(page, search, debut), new ComputerMapper());
				
		return computers;
	}

	/**
	 * @param name of the search
	 * @return the number of result
	 * @throws ConnectionException
	 */
	public int getNbComputersSearch(String search){		
		return jdbcTemplate.queryForObject(SqlQueries.getNbComputerSearch(search), Integer.class);
	}

	/**
	 * find all computers
	 * @throws ConnectionException
	 */
	@Override
	public List<Computer> findAll(){
		return jdbcTemplate.query(SqlQueries.FIND_ALL, new ComputerMapper());
	}

	/**
	 * find the computers in a page
	 * @param the page number and the number of computers by page
	 * @return the list of computers
	 * @throws ConnectionException
	 */
	@Override
	public List<Computer> findPage(Page page){
		
		int debut = page.getNbEntriesByPage() * (page.getPageNumber() - 1);	
		List<Computer> computerList = new ArrayList<Computer>();
		
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(SqlQueries.findPage(page));
		query.setFirstResult(debut);
		query.setMaxResults(debut+page.getNbEntriesByPage());
		computerList = query.list();
		
//		computerList = jdbcTemplate.query(SqlQueries.findPage(page), new ComputerMapper());
		
		return computerList;
	}

	/**
	 * Gets the number of computers.
	 * @return the number of computers
	 * @throws ConnectionException
	 */
	public int getNbComputers(){
		return jdbcTemplate.queryForObject(SqlQueries.COUNT_COMPUTERS, Integer.class);
	}
}