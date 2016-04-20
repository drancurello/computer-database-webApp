package com.excilys.computerDatabase.page;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.model.Computer;

/**
 * The Class Page.
 */
public class Page {

	/** The number of entries by page. */
	private int nbEntriesByPage;
	
	/** The number of page. */
	private int nbPage;
	
	/** The number of computers. */
	private int nbComputers;
	
	/** The page number. */
	private int pageNumber;
	
	/** order of the page */
	private String order;
	
	/** type of order */
	private String type;
	
	/** the list of computers contained in the page */
	private List<ComputerDTO> computersList;
	
	public Page(int pageNumber, int nbEntries) {
		this.pageNumber = pageNumber;
		this.nbEntriesByPage = nbEntries;
		this.order = "name";
		this.type = "ASC";
	}
	
	/**
	 * Instantiates a new page.
	 *
	 * @param pageNumber the page number
	 * @param nbEntries the number of entries by page
	 */
	public Page(int pageNumber, int nbEntries, int nbComputers) {
		this.pageNumber = pageNumber;
		this.nbEntriesByPage = nbEntries;
		this.nbComputers = nbComputers;
		this.order = "name";
		this.type = "ASC";
		this.computersList = new ArrayList<>();
		update();
	}
	
	private void update() {
		if (nbComputers%nbEntriesByPage == 0) {
			nbPage = nbComputers/nbEntriesByPage;
		} else {
			nbPage = (nbComputers/nbEntriesByPage) + 1;
		}
	}
	
	/**
	 * Go to the previous page.
	 */
	public void previousPage()
	{
		if (pageNumber > 1)
		{
			pageNumber -= 1;
		}
	}
	
	/**
	 * Go to the next page.
	 */
	public void nextPage()
	{
		if (pageNumber < nbPage)
		{
			pageNumber += 1;
		}
	}

	/**
	 * Gets the number of entries by page.
	 *
	 * @return the number of entries by page
	 */
	public int getNbEntriesByPage() {
		return nbEntriesByPage;
	}

	/**
	 * Sets the number of entries by page.
	 *
	 * @param nbEntries the new number of entries by page
	 */
	public void setNbEntriesByPage(int nbEntries) {
		this.nbEntriesByPage = nbEntries;
	}

	/**
	 * Gets the number of page.
	 *
	 * @return the number of page
	 */
	public int getNbPage() {
		return nbPage;
	}

	/**
	 * Gets the page number.
	 *
	 * @return the page number
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * Sets the page number and verify if the new page number 
	 * isn't before the first page and after the last page.
	 *
	 * @param pageNumber the new page number
	 */
	public void setPageNumber(int pageNumber) {
		if (pageNumber > nbPage) {
			this.pageNumber = nbPage;
		}
		else {
			if (pageNumber < 1) {
				this.pageNumber = 1;
			}
			else {
				this.pageNumber = pageNumber;
			}		
		}
	}

	public List<ComputerDTO> getComputersList() {
		return computersList;
	}

	public void setComputersList(List<ComputerDTO> computersList) {
		this.computersList = computersList;
	}

	/**
	 * Gets the number of computers.
	 *
	 * @return the number of computers
	 */
	public int getNbComputers() {
		return nbComputers;
	}	
	
	public void setNbComputers(int nbComputers) {
		this.nbComputers = nbComputers;
		update();
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
