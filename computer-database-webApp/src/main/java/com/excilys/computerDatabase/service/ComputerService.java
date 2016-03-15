package com.excilys.computerDatabase.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.dao.ComputerDAO;
import com.excilys.computerDatabase.mapper.ComputerDTOMapper;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.model.ComputerDTO;

public class ComputerService {

	private static ComputerDAO computerDAO = new ComputerDAO();
	
	public static ComputerDTO addComputer(ComputerDTO computerdto)
	{
		Computer computer = ComputerDTOMapper.toComputer(computerdto);
		
		return ComputerDTOMapper.toComputerDTO(computerDAO.add(computer));
	}
	
	public static ComputerDTO updateComputer(ComputerDTO computerdto)
	{
		Computer computer = ComputerDTOMapper.toComputer(computerdto);
		
		return ComputerDTOMapper.toComputerDTO(computerDAO.update(computer));
	}
	
	public static int deleteComputer(long id)
	{
		return computerDAO.delete(id);
	}
	
	public static ComputerDTO findComputer(long id)
	{
		return ComputerDTOMapper.toComputerDTO(computerDAO.find(id));
	}
	
	public static List<ComputerDTO> findAllComputers()
	{
		 List<Computer> computersList = computerDAO.findAll();
		 List<ComputerDTO> computerDTOList = new ArrayList<>();
		 
		 for(Computer computer:computersList) {
			 computerDTOList.add(ComputerDTOMapper.toComputerDTO(computer));
		 }
		 
		 return computerDTOList;
	}
	
	public static List<ComputerDTO> findPageComputers(int pageNumber,int nbEntries)
	{	
		 List<Computer> computersList = computerDAO.findPage(pageNumber, nbEntries);;
		 List<ComputerDTO> computerDTOList = new ArrayList<>();
		 
		 for(Computer computer:computersList) {
			 computerDTOList.add(ComputerDTOMapper.toComputerDTO(computer));
		 }
		 
		 return computerDTOList;
	}
	
	public static int getNbComputers()
	{
		return computerDAO.getNbComputers();
	}
	
}
