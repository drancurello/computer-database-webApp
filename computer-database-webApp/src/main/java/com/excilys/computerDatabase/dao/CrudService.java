package com.excilys.computerDatabase.dao;

import java.util.List;

import com.excilys.computerDatabase.exceptions.DAOConfigurationException;

public interface CrudService<T> {

	public abstract T add(T obj)  throws DAOConfigurationException;

	public abstract T update(T obj)  throws DAOConfigurationException;

	public abstract int delete(long id)  throws DAOConfigurationException;

	public abstract T find(long id)  throws DAOConfigurationException;

	public abstract List<T> findAll()  throws DAOConfigurationException;

	public abstract List<T> findPage(int nPage, int nComputer)  throws DAOConfigurationException;

}
