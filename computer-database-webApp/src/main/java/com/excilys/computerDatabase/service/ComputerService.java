package com.excilys.computerDatabase.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.dao.ComputerDAO;
import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.mapper.ComputerDTOMapper;
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
	 * @param computerdto the computerdto
	 * @return the computer dto
	 */
	public static ComputerDTO addComputer(ComputerDTO computerdto)
	{
		Computer computer = ComputerDTOMapper.toComputer(computerdto);
		
		return ComputerDTOMapper.toComputerDTO(computerDAO.add(computer));
	}
	
	/**
	 * Update computer.
	 *
	 * @param a computerDTO to update
	 * @return the computer dto which has been update
	 */
	public static ComputerDTO updateComputer(ComputerDTO computerdto)
	{
		Computer computer = ComputerDTOMapper.toComputer(computerdto);
		
		return ComputerDTOMapper.toComputerDTO(computerDAO.update(computer));
	}
	
	/**
	 * Delete computer.
	 *
	 * @param the id of the computer
	 * @return the result of the delete 
	 */
	public static int deleteComputer(long id)
	{
		return computerDAO.delete(id);
	}
	
	/**
	 * Find computer.
	 *
	 * @param the id of the computer that we want to find
	 * @return the found computerDTO
	 */
	public static ComputerDTO findComputer(long id)
	{
		return ComputerDTOMapper.toComputerDTO(computerDAO.find(id));
	}
	
	/**
	 * Find all computers.
	 *
	 * @return the list of all computers
	 */
	public static List<ComputerDTO> findAllComputers()
	{
		 List<Computer> computersList = computerDAO.findAll();
		 List<ComputerDTO> computerDTOList = new ArrayList<>();
		 
		 for(Computer computer:computersList) {
			 computerDTOList.add(ComputerDTOMapper.toComputerDTO(computer));
		 }
		 
		 return computerDTOList;
	}
	
	/**
	 * Find page computers.
	 *
	 * @param pageNumber the page number
	 * @param nbEntries the nb entries
	 * @return the list
	 */
	public static List<ComputerDTO> findPageComputers(int pageNumber,int nbEntries)
	{	
		 List<Computer> computersList = computerDAO.findPage(pageNumber, nbEntries);;
		 List<ComputerDTO> computerDTOList = new ArrayList<>();
		 
		 for(Computer computer:computersList) {
			 computerDTOList.add(ComputerDTOMapper.toComputerDTO(computer));
		 }
		 
		 return computerDTOList;
	}
	
	/**
	 * Gets the nb computers.
	 *
	 * @return the nb computers
	 */
	public static int getNbComputers()
	{
		return computerDAO.getNbComputers();
	}
	
}
