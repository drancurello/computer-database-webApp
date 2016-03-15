package com.excilys.computerDatabase.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerDatabase.model.ComputerDTO;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int page = 1;
		int nbComputersPage = 50;
		int nbComputers = ComputerService.findAllComputers().size();
		
		if(request.getParameter("nbComputersPage") != null) {
			nbComputersPage = Integer.parseInt(request.getParameter("nbComputersPage"));
		}
		
		int nbPage = ( nbComputers / nbComputersPage) + 1;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			
			if (page > nbPage)
			{
				page = nbPage;
			}
			else {
				if (page < 1) {
					page = 1;
				}
			}
		}
		
		List<ComputerDTO> computers = ComputerService.findPageComputers(page, nbComputersPage);
		
		RequestDispatcher rd = null;
		
		
		request.setAttribute("computers", computers);
		request.setAttribute("nbPage",nbPage);
		request.setAttribute("currentPage", page);
		request.setAttribute("nbComputers", nbComputers);
		request.setAttribute("nbComputersPage", nbComputersPage);
		
		rd = getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String[] computersId = request.getParameter("selection").split(",");
		
		for(String id:computersId) {
			ComputerService.deleteComputer(Integer.parseInt(id));
		}
		
		doGet(request, response);
	}

}
