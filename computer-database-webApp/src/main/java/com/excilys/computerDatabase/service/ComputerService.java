package com.excilys.computerDatabase.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.dao.ComputerDAO;
import com.excilys.computerDatabase.exceptions.DAOConfigurationException;
import com.excilys.computerDatabase.model.Computer;

/**
 * The Class ComputerService.
 */
public class ComputerService {

	/** The computer dao. */
	private static ComputerDAO computerDAO = new ComputerDAO();
	
	/**
	 * Adds the computer.
	 *
	 * @param computer the computer
	 * @return the computer
	 * @throws DAOConfigurationException 
	 */
	public static Computer addComputer(Computer computer)
	{		
		Computer c = null;
		try {
			c = computerDAO.add(computer);
		} catch (DAOConfigurationException e) {
			System.err.println(e.getMessage());
		}
		return c;
	}
	
	/**
	 * Update computer.
	 *
	 * @param a computer to update
	 * @return the computer which has been update
	 * @throws DAOConfigurationException 
	 */
	public static Computer updateComputer(Computer computer) 
	{
		Computer c = null;
		try {
			c = computerDAO.update(computer);
		} catch (DAOConfigurationException e) {
			System.err.println(e.getMessage());
		}
		return c;
	}
	
	/**
	 * Delete computer.
	 *
	 * @param the id of the computer
	 * @return the result of the delete 
	 * @throws DAOConfigurationException 
	 */
	public static int deleteComputer(long id)
	{
		int i =0;
		try {
			i = computerDAO.delete(id);
		} catch (DAOConfigurationException e) {
			System.err.println(e.getMessage());
		}
		return i;
	}
	
	/**
	 * Find computer.
	 *
	 * @param the id of the computer that we want to find
	 * @return the found computer
	 * @throws DAOConfigurationException 
	 */
	public static Computer findComputer(long id)
	{
		Computer c = null;
		try {
			c = computerDAO.find(id);
		} catch (DAOConfigurationException e) {
			System.err.println(e.getMessage());
		}
		return c;
	}
	
	/**
	 * Find computer.
	 *
	 * @param the name of the computer that we want to find
	 * @return the found computer
	 * @throws DAOConfigurationException 
	 */
	public static List<Computer> findByName(String name) {
		List<Computer> computers = new ArrayList<>();
		try {
			computers = computerDAO.findByName(name);
		} catch (DAOConfigurationException e) {
			System.err.println(e.getMessage());
		}
		
		return computers;
	}
	
	/**
	 * 
	 * @param id of a company
	 * @return the list of all computers from the company
	 * @throws DAOConfigurationException 
	 */
	public static List<Computer> findComputersByCompanyId(int id) {
		List<Computer> computers = new ArrayList<>();
		try {
			computers = computerDAO.findComputersByCompanyId(id);
		} catch (DAOConfigurationException e) {
			System.err.println(e.getMessage());
		}
		return computers;
	}
	
	/**
	 * Find all computers.
	 *
	 * @return the list of all computers
	 * @throws DAOConfigurationException 
	 */
	public static List<Computer> findAllComputers() {
		 List<Computer> computersList = new ArrayList<>();
		 try {
			computersList = computerDAO.findAll();
		} catch (DAOConfigurationException e) {
			System.err.println(e.getMessage());
		}
		 
		 return computersList;
	}
	
	/**
	 * Find page computers.
	 *
	 * @param pageNumber the page number
	 * @param nbEntries the number of entries
	 * @return the list
	 * @throws DAOConfigurationException 
	 */
	public static List<Computer> findPageComputers(int pageNumber,int nbEntries) {	
		 List<Computer> computersList = new ArrayList<>();
		 try {
			computersList = computerDAO.findPage(pageNumber, nbEntries);
		} catch (DAOConfigurationException e) {
			System.err.println(e.getMessage());
		};

		 return computersList;
	}
	
	/**
	 * Gets the number of computers.
	 *
	 * @return the number of computers present in the DB
	 * @throws DAOConfigurationException 
	 */
	public static int getNbComputers() {
		int i = 0;
		try {
			i = computerDAO.getNbComputers();
		} catch (DAOConfigurationException e) {
			System.err.println(e.getMessage());
		}
		return i;
	}
	
}
