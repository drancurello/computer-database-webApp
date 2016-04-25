package com.excilys.computerDatabase.dao.interfaceDAO;

import java.util.List;

import com.excilys.computerDatabase.exceptions.ConnectionException;
import com.excilys.computerDatabase.exceptions.DAOException;
import com.excilys.computerDatabase.page.Page;

/**
 *
 * this interface provides CRUD operations
 */
public interface IGenericDAO<T> {

	/**
	 * add an object in a database
	 * @return the added object
	 * @throws DAOException
	 * @throws ConnectionException
	 */
	default T add(T obj){
		throw new UnsupportedOperationException();
	}

	/**
	 * update an object in a database
	 * @return the updated object
	 * @throws DAOException
	 * @throws ConnectionException
	 */
	default T update(T obj){
		throw new UnsupportedOperationException();
	}

	/**
	 * delete an object in a database
	 * @param the id of the object
	 * @return 1 or 0 if the object has been deleted or not
	 * @throws DAOException
	 * @throws ConnectionException
	 */
	default int delete(long id){
		throw new UnsupportedOperationException();
	}

	/**
	 * find an object with its id
	 * @param the id of the object
	 * @return the object from the database
	 * @throws DAOException
	 * @throws ConnectionException
	 */
	default T find(long id){
		throw new UnsupportedOperationException();
	}

	/**
	 * find all the objects in the database
	 * @return a list with all the objects
	 * @throws DAOException
	 * @throws ConnectionException
	 */
	default List<T> findAll(){
		throw new UnsupportedOperationException();
	}

}
