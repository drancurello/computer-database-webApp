package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.util.List;

import com.excilys.computerDatabase.connection.ConnectionMySQL;

public interface CrudService<T> {

	public Connection connection = ConnectionMySQL.getInstance();

	public abstract T add(T obj);

	public abstract T update(T obj);

	public abstract int delete(long id);

	public abstract T find(long id);

	public abstract List<T> findAll();

	public abstract List<T> findPage(int nPage, int nComputer);

}
