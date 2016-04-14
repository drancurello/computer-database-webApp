package com.excilys.computerDatabase.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.computerDatabase.mapper.PageMapper;
import com.excilys.computerDatabase.page.Page;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.validation.ValidationComputer;

/**
 * Servlet implementation class ComputersList
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ComputerService computerService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-Module.xml"});
		computerService = (ComputerService) context.getBean("computerService");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String search = null;
		Page indexPage = PageMapper.requestToPage(request);
		indexPage = computerService.findPageComputers(indexPage);

		if (request.getParameter("search") != null) {
			search = request.getParameter("search");
			indexPage = computerService.search(search, indexPage);
			
			ValidationComputer.PageNumberValidation(indexPage);
			request.setAttribute("search", search);
			
		} else {
			ValidationComputer.PageNumberValidation(indexPage);
		}	

		request.setAttribute("page", indexPage);

		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
