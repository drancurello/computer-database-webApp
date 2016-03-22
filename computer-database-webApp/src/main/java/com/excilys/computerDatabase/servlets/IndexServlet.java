package com.excilys.computerDatabase.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.page.Page;
import com.excilys.computerDatabase.service.ComputerService;

/**
 * Servlet implementation class ComputersList
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int page = 1;
		int nbComputersPage = 50;
		String name = null;
		List<Computer> computers = new ArrayList<>();

		if (request.getParameter("nbComputersPage") != null) {
			nbComputersPage = Integer.parseInt(request.getParameter("nbComputersPage"));
		}

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		Page indexPage = new Page(page, nbComputersPage);

		if (request.getParameter("search") != null) {
			name = request.getParameter("search");
			for (Computer computer:ComputerService.search(name, page, nbComputersPage)) {
				if (!computers.contains(computer)) {
					computers.add(computer);
				}		
			}
			indexPage.setNbComputers(ComputerService.getNbComputersSearch(name));
			
			if (indexPage.getPageNumber() > indexPage.getNbPage()) {
				indexPage.setPageNumber(indexPage.getNbPage());
			} else {
				if (indexPage.getPageNumber() < 1) {
					indexPage.setPageNumber(1);
				}
			}
			request.setAttribute("search", name);
		} else {
			if (indexPage.getPageNumber() > indexPage.getNbPage()) {
				indexPage.setPageNumber(indexPage.getNbPage());
			} else {
				if (indexPage.getPageNumber() < 1) {
					indexPage.setPageNumber(1);
				}
			}
			computers = ComputerService.findPageComputers(indexPage.getPageNumber(),
				indexPage.getNbEntriesByPage());
		}	

		request.setAttribute("computers", computers);
		request.setAttribute("nbPage", indexPage.getNbPage());
		request.setAttribute("currentPage", indexPage.getPageNumber());
		request.setAttribute("nbComputers", indexPage.getNbComputers());
		request.setAttribute("nbComputersPage", indexPage.getNbEntriesByPage());

		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
