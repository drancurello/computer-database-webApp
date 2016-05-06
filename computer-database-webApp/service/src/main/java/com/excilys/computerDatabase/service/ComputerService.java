package com.excilys.computerDatabase.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerDatabase.dao.CompanyDAO;
import com.excilys.computerDatabase.dao.ComputerDAO;
import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.exceptions.ConnectionException;
import com.excilys.computerDatabase.exceptions.DAOException;
import com.excilys.computerDatabase.mapper.ComputerDTOMapper;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.page.Page;

/**
 * The Class ComputerService.
 */
@Component
@Transactional
public class ComputerService {
	
	/** The computer dao. */
	@Autowired
	private ComputerDAO computerDAO;
	@Autowired
	private CompanyDAO companyDAO;
	
	/**
	 * Adds the computer.
	 *
	 * @param computer the computer
	 * @return the computer
	 * @throws DAOException 
	 * @throws ConnectionException 
	 */
	public ComputerDTO addComputer(ComputerDTO computerdto) {		
		Computer c = null;
		c = computerDAO.add(ComputerDTOMapper.toComputer(computerdto));
		computerdto.setId(c.getId());
		return computerdto;
	}
	
	/**
	 * Update computer.
	 *
	 * @param a computer to update
	 * @return the computer which has been update
	 * @throws DAOException 
	 * @throws ConnectionException 
	 */
	public ComputerDTO updateComputer(ComputerDTO computerdto) {
		ComputerDTO c = null;
		c = ComputerDTOMapper.toComputerDTO(computerDAO.update(ComputerDTOMapper.toComputer(computerdto)));
		return c;
	}
	
	/**
	 * Delete computer.
	 *
	 * @param the id of the computer
	 * @return the result of the delete 
	 * @throws DAOException 
	 * @throws SQLException 
	 * @throws ConnectionException 
	 */
	public int deleteComputer(long id) {

		int i = computerDAO.delete(id);
		return i;
	}
	
	/**
	 * Find computer.
	 *
	 * @param the id of the computer that we want to find
	 * @return the computer found
	 * @throws DAOException 
	 * @throws ConnectionException 
	 */
	public ComputerDTO findComputer(long id) {
		Computer c = null;
		c = computerDAO.find(id);
		if (c != null && c.getCompany() != null) {
			c.setCompany(companyDAO.find(c.getCompany().getId()));
		}

		return ComputerDTOMapper.toComputerDTO(c);
	}
	
	/**
	 * search computers.
	 *
	 * @param the name of the computer that we want to find
	 * @return computers found
	 * @throws DAOException 
	 * @throws ConnectionException 
	 */
	public Page search(String name, Page page) {
		List<Computer> computers = new ArrayList<>();
		List<ComputerDTO> computersDTO = new ArrayList<>();
		
		computers = computerDAO.search(name, page.getNbEntriesByPage(), page.getPageNumber(), page.getOrder(), page.getType());
		page.setNbComputers(getNbComputersSearch(name));
		for (Computer c:computers) {
			if (c.getCompany() != null) {
				c.setCompany(companyDAO.find(c.getCompany().getId()));
			}
		}
		for(Computer c:computers) {
			computersDTO.add(ComputerDTOMapper.toComputerDTO(c));
		}
		
		page.setComputersList(computersDTO);
		
		return page;
	}
	
	/**
	 * 
	 * @param the name of the computer and the id of the company
	 * @return the number of computers
	 * @throws DAOException 
	 * @throws ConnectionException 
	 */
	public int getNbComputersSearch(String name) {
		int nbComputers = 0;
		nbComputers = computerDAO.getNbComputersSearch(name);
		return nbComputers;
	}
	
	/**
	 * Find all computers.
	 *
	 * @return the list of all computers
	 * @throws DAOException 
	 * @throws ConnectionException 
	 */
	public List<Computer> findAllComputers() {
		List<Computer> computersList = new ArrayList<>();
		computersList = computerDAO.findAll();
		for (Computer c:computersList) {
			if (c.getCompany() != null) {
				c.setCompany(companyDAO.find(c.getCompany().getId()));
			}
		}
		return computersList;
	}
	
	/**
	 * Find page computers.
	 *
	 * @param pageNumber the page number
	 * @param nbEntries the number of entries
	 * @return the list
	 * @throws DAOException 
	 * @throws ConnectionException 
	 */
	public Page findPageComputers(Page page) {	
		List<Computer> computers = new ArrayList<>();
		List<ComputerDTO> computersDTO = new ArrayList<>();	
		computers = computerDAO.findPage(page.getNbEntriesByPage(), page.getPageNumber(), page.getOrder(), page.getType());
		page.setNbComputers(getNbComputers());
		for (Computer c:computers) {
			if (c.getCompany() != null) {
				c.setCompany(companyDAO.find(c.getCompany().getId()));
			}
		}
		for(Computer c:computers) {
			computersDTO.add(ComputerDTOMapper.toComputerDTO(c));
		}

		page.setComputersList(computersDTO);
		
		return page;
	}
	
	/**
	 * Gets the number of computers.
	 *
	 * @return the number of computers present in the DB
	 * @throws DAOException 
	 * @throws ConnectionException 
	 */
	public int getNbComputers() {
		int i = 0;
		i = computerDAO.getNbComputers();
		return i;
	}
	
	
	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}
		
	public void setComputerDAO(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}
	
	
}
