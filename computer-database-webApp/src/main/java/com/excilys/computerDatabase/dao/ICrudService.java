package com.excilys.computerDatabase.dao;

import java.util.List;

import com.excilys.computerDatabase.exceptions.ConnectionException;
import com.excilys.computerDatabase.exceptions.DAOException;

public interface ICrudService<T> {

	public abstract T add(T obj)  throws DAOException,ConnectionException;

	public abstract T update(T obj)  throws DAOException,ConnectionException;

	public abstract int delete(long id)  throws DAOException,ConnectionException;

	public abstract T find(long id)  throws DAOException,ConnectionException;

	public abstract List<T> findAll()  throws DAOException,ConnectionException;

	public abstract List<T> findPage(int nPage, int nComputer)  throws DAOException,ConnectionException;

}
