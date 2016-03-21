package com.excilys.computerDatabase.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int page = 1;
		int nbPage;
		int nbComputersPage = 10;
		
		if (request.getParameter("nbComputersPage") != null) {
			nbComputersPage = Integer.parseInt(request.getParameter("nbComputersPage"));
		}

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		List<Computer> computers = new ArrayList<>();
		String name = request.getParameter("search");
		
		if (ComputerService.findByName(name).size() != 0) {
			for (Computer c:ComputerService.findByName(name)) {
				computers.add(c);
			}
		}
		if (CompanyService.findIdByName(name) != 0) {
			int id = CompanyService.findIdByName(name);			
			for (Computer computer:ComputerService.findComputersByCompanyId(id)) {
				if (!computers.contains(computer)) {
					computers.add(computer);
				}			
			}
		}
		
		if (computers.size()%nbComputersPage == 0) {
			nbPage = computers.size()/nbComputersPage;
		} else {
			nbPage = computers.size()/nbComputersPage + 1;
		}
		
		request.setAttribute("computers",computers);
		request.setAttribute("nbComputers", computers.size());
//		request.setAttribute("nbPage", nbPage);
//		request.setAttribute("currentPage", page);
		
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
