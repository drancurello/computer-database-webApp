package com.excilys.computerDatabase.dao.interfaceDAO;

import java.util.List;

import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.page.Page;

public interface IComputerDAO extends IGenericDAO<Computer> {
	
	@Override
	Computer add(Computer obj);
	
	@Override
	Computer update(Computer obj);
	
	@Override
	int delete(long id);
	
	@Override 
	Computer find(long id);
	
	@Override 
	List<Computer> findAll();
	
	public abstract int deleteByCompany(long id);
	
	public abstract List<Computer> search(String search, Page page);

	public abstract int getNbComputersSearch(String search);		

	public abstract List<Computer> findPage(Page page);
	
	public abstract int getNbComputers();


}
