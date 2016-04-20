package com.excilys.computerDatabase.dao;

import java.util.List;

import com.excilys.computerDatabase.exceptions.ConnectionException;
import com.excilys.computerDatabase.exceptions.DAOException;
import com.excilys.computerDatabase.page.Page;

/**
 *
 * this interface provides CRUD operations
 */
public interface ICrudService<T> {

	/**
	 * add an object in a database
	 * @return the added object
	 * @throws DAOException
	 * @throws ConnectionException
	 */
	public abstract T add(T obj)  throws DAOException,ConnectionException;

	/**
	 * update an object in a database
	 * @return the updated object
	 * @throws DAOException
	 * @throws ConnectionException
	 */
	public abstract T update(T obj)  throws DAOException,ConnectionException;

	/**
	 * delete an object in a database
	 * @param the id of the object
	 * @return 1 or 0 if the object has been deleted or not
	 * @throws DAOException
	 * @throws ConnectionException
	 */
	public abstract int delete(long id)  throws DAOException,ConnectionException;

	/**
	 * find an object with its id
	 * @param the id of the object
	 * @return the object from the database
	 * @throws DAOException
	 * @throws ConnectionException
	 */
	public abstract T find(long id)  throws DAOException,ConnectionException;

	/**
	 * find all the objects in the database
	 * @return a list with all the objects
	 * @throws DAOException
	 * @throws ConnectionException
	 */
	public abstract List<T> findAll()  throws DAOException,ConnectionException;

	/**
	 * get objects in a page
	 * @param the page number
	 * @param the number of objects in the page
	 * @return the page 
	 * @throws DAOException
	 * @throws ConnectionException
	 */
	public abstract List<T> findPage(Page page)  throws DAOException,ConnectionException;

}
