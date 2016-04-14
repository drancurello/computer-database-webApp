package com.excilys.computerDatabase.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.computerDatabase.service.ComputerService;

/**
 * Servlet implementation class deleteServlet
 */
@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ComputerService computerService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        }
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-Module.xml"});
		computerService = (ComputerService) context.getBean("computerService");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String[] computersId = request.getParameter("selection").split(",");

		for (String id : computersId) {
			computerService.deleteComputer(Integer.parseInt(id));
		}
		
		response.sendRedirect("index");
	}

}
