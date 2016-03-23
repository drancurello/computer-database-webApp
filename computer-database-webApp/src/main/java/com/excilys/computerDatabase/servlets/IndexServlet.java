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
import com.excilys.computerDatabase.validation.ValidationComputer;

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
		String search = null;
		List<Computer> computers = new ArrayList<>();
		String order = "name";
		String type = "ASC";

		if (request.getParameter("nbComputersPage") != null && request.getParameter("nbComputersPage").matches("\\d+")) {
			nbComputersPage = Integer.parseInt(request.getParameter("nbComputersPage"));
		}

		if (request.getParameter("page") != null && request.getParameter("page").matches("\\d+")) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		if (request.getParameter("order") != null) {
			order = request.getParameter("order");
			ComputerService.setOrder(order);
		}
		
		if (request.getParameter("type") != null) {
			type = request.getParameter("type");
			ComputerService.setType(type);
		}

		Page indexPage = new Page(page, nbComputersPage);

		if (request.getParameter("search") != null) {
			search = request.getParameter("search");
			for (Computer computer:ComputerService.search(search, page, nbComputersPage)) {
				if (!computers.contains(computer)) {
					computers.add(computer);
				}		
			}
			indexPage.setNbComputers(ComputerService.getNbComputersSearch(search));
			
			ValidationComputer.PageNumberValidation(indexPage);
			request.setAttribute("search", search);
			
		} else {
			ValidationComputer.PageNumberValidation(indexPage);
			computers = ComputerService.findPageComputers(indexPage.getPageNumber(),indexPage.getNbEntriesByPage());
		}	

		request.setAttribute("computers", computers);
		request.setAttribute("nbPage", indexPage.getNbPage());
		request.setAttribute("currentPage", indexPage.getPageNumber());
		request.setAttribute("nbComputers", indexPage.getNbComputers());
		request.setAttribute("nbComputersPage", indexPage.getNbEntriesByPage());

		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
