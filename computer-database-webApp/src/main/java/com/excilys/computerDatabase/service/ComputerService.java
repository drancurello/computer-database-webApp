package com.excilys.computerDatabase.service;

import java.util.List;

import com.excilys.computerDatabase.dao.ComputerDAO;
import com.excilys.computerDatabase.model.Computer;

public class ComputerService {

	private static ComputerDAO computerDAO = new ComputerDAO();
	
	public static Computer addComputer(Computer computer)
	{
		return computerDAO.add(computer);
	}
	
	public static Computer updateComputer(Computer computer)
	{
		return computerDAO.update(computer);
	}
	
	public static int deleteComputer(long id)
	{
		return computerDAO.delete(id);
	}
	
	public static Computer findComputer(long id)
	{
		return computerDAO.find(id);
	}
	
	public static List<Computer> findAllComputers()
	{
		return computerDAO.findAll();
	}
	
	public static List<Computer> findPageComputers(int pageNumber,int nbEntries)
	{
		return computerDAO.findPage(pageNumber, nbEntries);
	}
	
	public static int getNbComputers()
	{
		return computerDAO.getNbComputers();
	}
	
}
