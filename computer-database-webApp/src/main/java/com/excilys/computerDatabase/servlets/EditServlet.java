package com.excilys.computerDatabase.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerDatabase.mapper.ComputerMapper;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.validation.ValidationComputer;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static int id = 0;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * return the information of the selected computer
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}
		
		Computer computer = ComputerService.findComputer(id);
		
		List<Company> companies = CompanyService.findAllCompanies();
		
		request.setAttribute("companies", companies);
		request.setAttribute("name", computer.getName());
		request.setAttribute("introduced", computer.getIntroducedTime());
		request.setAttribute("discontinued", computer.getDiscontinuedTime());
		
		this.getServletContext().getRequestDispatcher("/views/editComputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * verify the entered informations and update the computer if there is no exception
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String,String> erreurs = new HashMap<String,String>();
		
		String name = request.getParameter("computerName");
		
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		
		Computer computer = new Computer();
		
		try {
			ValidationComputer.nameValidation(name);
		}
		catch(Exception e) {
			erreurs.put("name", e.getMessage());
		}
		
		try {
			ValidationComputer.introducedValidation(introduced);
		} catch(Exception e) {
			erreurs.put("introduced", e.getMessage());
		}
		
		try {
			ValidationComputer.discontinuedValidation(discontinued,introduced);
		} catch(Exception e) {
			erreurs.put("discontinued", e.getMessage());
		}
		
		try {
			ValidationComputer.companyValidation(request.getParameter("companyId"));
		}
		catch(Exception e) {
			erreurs.put("company", e.getMessage());
		}
		
		if(erreurs.isEmpty()) {
			computer = ComputerMapper.requestToComputer(request);
			computer.setId(id);
			
			ComputerService.updateComputer(computer);
			response.sendRedirect("index");
			
		} else {
			request.setAttribute("erreurs", erreurs);
			doGet(request, response);
		}
		
	}

}
