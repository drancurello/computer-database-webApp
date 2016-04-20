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

import com.excilys.computerDatabase.mapper.ComputerDTOMapper;
import com.excilys.computerDatabase.mapper.ComputerMapper;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.validation.FormValidation;

/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet("/AddComputerServlet")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputerServlet() {
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
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Company> companies = companyService.findAllCompanies();	
		request.setAttribute("companies", companies);	
		this.getServletContext().getRequestDispatcher("/views/addComputer.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String,String> errors = FormValidation.validForm(request);
		
		if(errors.isEmpty()) {
			
			Computer computer = ComputerMapper.servletRequestToComputer(request);
			computer.setCompany(companyService.findCompany(computer.getCompany().getId()));
			computerService.addComputer(ComputerDTOMapper.toComputerDTO(computer));
			response.sendRedirect("index");
			
		} else {
			request.setAttribute("errors", errors);
			doGet(request, response);
		}
	}

}
