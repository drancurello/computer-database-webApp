package com.excilys.computerDatabase.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

		if (request.getParameter("nbComputersPage") != null) {
			nbComputersPage = Integer.parseInt(request.getParameter("nbComputersPage"));
		}

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		Page indexPage = new Page(page, nbComputersPage);

		if (indexPage.getPageNumber() > indexPage.getNbPage()) {
			indexPage.setPageNumber(indexPage.getNbPage());
		} else {
			if (indexPage.getPageNumber() < 1) {
				indexPage.setPageNumber(1);
			}
		}

		List<Computer> computers = ComputerService.findPageComputers(indexPage.getPageNumber(),
				indexPage.getNbEntriesByPage());

		RequestDispatcher rd = null;

		request.setAttribute("computers", computers);
		request.setAttribute("nbPage", indexPage.getNbPage());
		request.setAttribute("currentPage", indexPage.getPageNumber());
		request.setAttribute("nbComputers", indexPage.getNbComputers());
		request.setAttribute("nbComputersPage", indexPage.getNbEntriesByPage());

		rd = getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
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
