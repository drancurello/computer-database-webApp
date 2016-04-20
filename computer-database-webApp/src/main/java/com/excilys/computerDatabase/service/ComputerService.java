package com.excilys.computerDatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.connection.ConnectionMySQL;
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
public class ComputerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerService.class);

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
		try {
			c = computerDAO.add(ComputerDTOMapper.toComputer(computerdto));
			computerdto.setId(c.getId());
		} catch (ConnectionException | DAOException e) {
			LOGGER.error("add a computer failed cause " + e.getMessage());
		}
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
		try {
			c = ComputerDTOMapper.toComputerDTO(computerDAO.update(ComputerDTOMapper.toComputer(computerdto)));
		} catch (ConnectionException | DAOException e) {
			LOGGER.error("update a computer failed cause " + e.getMessage());
		}
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
		int i =0;
		Connection connection = null;
		
		try {
			i = computerDAO.delete(id);
			connection = ConnectionMySQL.getInstance().getConnection();
		} catch (ConnectionException | SQLException | DAOException e) {
			LOGGER.error("delete the computer " + id + " failed cause " + e.getMessage());
		} finally {
			ConnectionMySQL.CloseConnection(connection, null, null, null);
		}
		
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
		try {
			c = computerDAO.find(id);
			if (c != null && c.getCompany() != null) {
				c.setCompany(companyDAO.find(c.getCompany().getId()));
			}
		} catch (ConnectionException | DAOException e) {
			LOGGER.error("find the computer " + id + " failed cause " + e.getMessage());
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
		try {
			computers = computerDAO.search(name,page);
			for (Computer c:computers) {
				if (c.getCompany() != null) {
					c.setCompany(companyDAO.find(c.getCompany().getId()));
				}
			}
			for(Computer c:computers) {
				computersDTO.add(ComputerDTOMapper.toComputerDTO(c));
			}
		} catch (ConnectionException | DAOException e) {
			LOGGER.error("the search failed cause " + e.getMessage());
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
		try {
			nbComputers = computerDAO.getNbComputersSearch(name);
		} catch (ConnectionException | DAOException e) {
			LOGGER.error("get the number of computers of the search failed cause " + e.getMessage());
		}
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
		 try {
			computersList = computerDAO.findAll();
			for (Computer c:computersList) {
				if (c.getCompany() != null) {
					c.setCompany(companyDAO.find(c.getCompany().getId()));
				}
			}
		} catch (ConnectionException | DAOException e) {
			LOGGER.error("find all the computers failed cause " + e.getMessage());
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
		 try {
			computers = computerDAO.findPage(page);
			for (Computer c:computers) {
				if (c.getCompany() != null) {
					c.setCompany(companyDAO.find(c.getCompany().getId()));
				}
			}
			for(Computer c:computers) {
				computersDTO.add(ComputerDTOMapper.toComputerDTO(c));
			}
		} catch (ConnectionException | DAOException e) {
			LOGGER.error("find the page failed cause " + e.getMessage());
		};

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
		try {
			i = computerDAO.getNbComputers();
		} catch (ConnectionException | DAOException e) {
			LOGGER.error("get the number of computers failed cause " + e.getMessage());
		}
		return i;
	}
	
	
	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}
		
	public void setComputerDAO(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}
	
	
}
