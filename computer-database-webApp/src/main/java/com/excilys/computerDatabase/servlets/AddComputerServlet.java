package com.excilys.computerDatabase.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;

/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet("/AddComputerServlet")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Company> companies = CompanyService.findAllCompanies();
		
		request.setAttribute("companies", companies);
		
		this.getServletContext().getRequestDispatcher("/addComputer.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String,String> erreurs = new HashMap<String,String>();
		
		String name = request.getParameter("computerName");
		
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");

		int companyId = Integer.parseInt(request.getParameter("companyId"));
		
		Computer computer = new Computer(name);
		
		try {
			nameValidation(name);
		}
		catch(Exception e) {
			erreurs.put("name", e.getMessage());
		}
		
		try {
			introducedValidation(introduced);
		} catch(Exception e) {
			erreurs.put("introduced", e.getMessage());
		}
		
		try {
			discontinuedValidation(discontinued,introduced);
		} catch(Exception e) {
			erreurs.put("discontinued", e.getMessage());
		}
		
		try {
			companyValidation(companyId);
		}
		catch(Exception e) {
			erreurs.put("company", e.getMessage());
		}
		
		if(erreurs.isEmpty()) {
			if(!introduced.equals("")) {
				computer.setIntroducedTime(LocalDate.parse(introduced));
			}
			if(!discontinued.equals("")) {
				computer.setIntroducedTime(LocalDate.parse(discontinued));
			}
			Company company = CompanyService.findCompany(companyId);
			computer.setCompany(company);
			
			ComputerService.addComputer(computer);
			response.sendRedirect("index");
			
		} else {
			request.setAttribute("erreurs", erreurs);
			List<Company> companies = CompanyService.findAllCompanies();	
			request.setAttribute("companies", companies);
			this.getServletContext().getRequestDispatcher("/addComputer.jsp").forward(request, response);
		}
	}
	
	private void nameValidation(String name) throws Exception {
		if(name.equals("")) {
			throw new Exception("Enter a name please");
		} else {
			if (name.length() < 2) {
				throw new Exception("Enter a name of at least 2 characters");
			}
		}
	}
	
	private void introducedValidation(String introduced) throws Exception {
		if(!introduced.equals("")) {
			try {
				LocalDate.parse(introduced);
			}
			catch(DateTimeParseException e) {
				throw new Exception("introduced is not a date");
			}
		}
	}
	
	private void discontinuedValidation(String discontinued, String introduced) throws Exception {
		
		if(!discontinued.equals("")) {
			try {
				LocalDate.parse(discontinued);
			}
			catch(DateTimeParseException e) {
				throw new Exception("discontinued is not a date");
			}
		}	
		
		if(!discontinued.equals("") && !introduced.equals("")) {
			if(LocalDate.parse(discontinued).isBefore(LocalDate.parse(introduced))) {
				throw new Exception("discontinued date must be after the introduced date");
			}
		}
	}
	
	private void companyValidation(int id) throws Exception {
		Company company = CompanyService.findCompany(id);
		
		if(company.getName() == null) {
			throw new Exception("id entered did not match any company ");
		}
	}


}
