package com.excilys.computerDatabase.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.computerDatabase.mapper.ComputerMapper;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.parser.ParserInteger;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.validation.FormValidation;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
    }
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);	

		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-Module.xml"});
		computerService = (ComputerService) context.getBean("computerService");
		companyService = (CompanyService) context.getBean("companyService");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * return the information of the selected computer
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = ParserInteger.parserInt(request.getParameter("id"));
		Computer computer = null;
		
		if (id == -1) {
			response.sendRedirect("index");
		} else {
			computer = computerService.findComputer(id);
			if (computer == null) {
				response.sendRedirect("index");
			}
			else {
				List<Company> companies = companyService.findAllCompanies();
		
				request.setAttribute("companies", companies);
				request.setAttribute("name", computer.getName());
				request.setAttribute("introduced", computer.getIntroducedTime());
				request.setAttribute("discontinued", computer.getDiscontinuedTime());
				request.setAttribute("id", id);
				
				this.getServletContext().getRequestDispatcher("/views/editComputer.jsp").forward(request, response);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * verify the entered informations and update the computer if there is no exception
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String,String> erreurs = FormValidation.validForm(request);
		
		if(erreurs.isEmpty()) {
			Computer computer = ComputerMapper.servletRequestToComputer(request);
			computer.setCompany(companyService.findCompany(computer.getCompany().getId()));
			computer.setId(ParserInteger.parserInt(request.getParameter("id")));
			
			computerService.updateComputer(computer);
			response.sendRedirect("index");
			
		} else {
			request.setAttribute("erreurs", erreurs);
			doGet(request, response);
		}
		
	}

}
