package com.excilys.computerDatabase.page;

import com.excilys.computerDatabase.service.ComputerService;

public class Page {

	
	private int nbEntriesByPage;
	private int nbPage;
	private int nbComputers;
	private int pageNumber = 1;
	
	public Page(int pageNumber, int nbEntries)
	{
		this.pageNumber = pageNumber;
		this.nbEntriesByPage = nbEntries;
		this.nbComputers = ComputerService.getNbComputers();
		this.nbPage = (nbComputers/nbEntriesByPage) + 1;
	}
	
	public void previousPage()
	{
		if (pageNumber > 1)
		{
			pageNumber -= 1;
		}
	}
	
	public void nextPage()
	{
		if (pageNumber < nbPage)
		{
			pageNumber += 1;
		}
	}

	public int getNbEntriesByPage() {
		return nbEntriesByPage;
	}

	public void setNbEntriesByPage(int nbEntries) {
		this.nbEntriesByPage = nbEntries;
	}

	public int getNbPage() {
		return nbPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		if (pageNumber > nbPage) {
			this.pageNumber = nbPage;
		}
		else {
			this.pageNumber = pageNumber;
		}
	}

	public int getNbComputers() {
		return nbComputers;
	}	
	
}
