package com.excilys.computerDatabase.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.dao.interfaceDAO.IComputerDAO;
import com.excilys.computerDatabase.exceptions.ConnectionException;
import com.excilys.computerDatabase.model.Computer;

/**
 * The Class ComputerDAO.
 */
@Component
@SuppressWarnings("unchecked")
public class ComputerDAO implements IComputerDAO {
	
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
	public Computer add(Computer obj) {		
		factory.getCurrentSession().save(obj);		
		return obj;
	}

	/**
	 * update a computer in the DB
	 * @param the new computer
	 * @throws ConnectionException
	 */
	@Override
	public Computer update(Computer obj) {
		Query query = factory.getCurrentSession().createQuery(SqlQueries.UPDATE_COMPUTER);
		query.setParameter("name", obj.getName()).setParameter("introduced", obj.getIntroducedTime()).setParameter("discontinued", obj.getDiscontinuedTime())
		.setParameter("company_id", obj.getCompany().getId()).setParameter("id", obj.getId());
		query.executeUpdate();
		
		return obj;
	}

	/**
	 * delete a computer in the DB
	 * @param the id of the computer
	 * @throws ConnectionException
	 */
	@Override
	public int delete(long id) {
		Query query = factory.getCurrentSession().createQuery(SqlQueries.DELETE_COMPUTER);
		query.setParameter("id", id);
		return query.executeUpdate();
	}
	
	/**
	 * delete computers by its company id 
	 * @param the id of the company
	 * @throws ConnectionException
	 */
	public int deleteByCompany(long id) {
		Query query = factory.getCurrentSession().createQuery(SqlQueries.DELETE_COMPUTER_BY_COMPANY);
		query.setParameter("id", id);
		return query.executeUpdate();
	}

	/**
	 * find a computer in the DB by its id
	 * @param the id of the computer
	 * @return the computer found
	 * @throws ConnectionException
	 */
	@Override
	public Computer find(long id) {	
		Computer computer = null;
		
		Query query = factory.getCurrentSession().createQuery(SqlQueries.FIND_COMPUTER);
		query.setParameter("id", id);
		List<Computer> computers = query.list();
		
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
	public List<Computer> search(String search, int nbEntriesByPage, int pageNumber, String column, String order) {

		int debut = nbEntriesByPage * (pageNumber - 1);	
		List<Computer> computers = new ArrayList<>();
		
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(SqlQueries.search(search, column, order));
		query.setFirstResult(debut);
		query.setMaxResults(nbEntriesByPage);
		computers = query.list();

		return computers;
	}

	/**
	 * @param name of the search
	 * @return the number of result
	 * @throws ConnectionException
	 */
	public int getNbComputersSearch(String search) {	
		
		List<Long> result = factory.getCurrentSession().createQuery(SqlQueries.getNbComputerSearch(search)).list();
		return (int) (long) result.get(0);
	}

	/**
	 * find all computers
	 * @throws ConnectionException
	 */
	@Override
	public List<Computer> findAll() {
		return factory.getCurrentSession().createQuery(SqlQueries.FIND_ALL).list();
	}

	/**
	 * find the computers in a page
	 * @param the page number and the number of computers by page
	 * @return the list of computers
	 * @throws ConnectionException
	 */
	@Override
	public List<Computer> findPage(int nbEntriesByPage, int pageNumber, String column, String order) {
		
		int debut = nbEntriesByPage * (pageNumber - 1);	
		List<Computer> computerList = new ArrayList<Computer>();
		
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(SqlQueries.findPage(column, order));
		query.setFirstResult(debut);
		query.setMaxResults(nbEntriesByPage);
		computerList = query.list();
				
		return computerList;
	}

	/**
	 * Gets the number of computers.
	 * @return the number of computers
	 * @throws ConnectionException
	 */
	public int getNbComputers() {
		
		Query query = factory.getCurrentSession().createQuery(SqlQueries.COUNT_COMPUTERS);
		query.setMaxResults(1);
		return (int) (long) query.uniqueResult();
	}
}