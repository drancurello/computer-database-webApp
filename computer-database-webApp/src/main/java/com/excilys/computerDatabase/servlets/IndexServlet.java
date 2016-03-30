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
import com.excilys.computerDatabase.parser.ParserInteger;
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

		if (ParserInteger.parserInt(request.getParameter("nbComputersPage")) != -1) {
			nbComputersPage = ParserInteger.parserInt(request.getParameter("nbComputersPage"));
		}
		
		if (ParserInteger.parserInt(request.getParameter("nbComputersPage")) != -1) {
			page = ParserInteger.parserInt(request.getParameter("page"));
		}
		
		if (request.getParameter("order") != null) {
			ComputerService.setOrder(request.getParameter("order"));
		}
		
		if (request.getParameter("type") != null) {
			ComputerService.setType(request.getParameter("type"));
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
