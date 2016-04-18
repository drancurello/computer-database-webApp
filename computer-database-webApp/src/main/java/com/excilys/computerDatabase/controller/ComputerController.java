package com.excilys.computerDatabase.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerDatabase.mapper.ComputerMapper;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.parser.ParserInteger;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.validation.FormValidation;

@Controller
public class ComputerController {
	
	@Autowired 
	private ComputerService computerService;
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(path = "addComputer", method = RequestMethod.GET)
	public ModelAndView addComputerPage() {
		ModelAndView model = new ModelAndView("views/addComputer.jsp");
		model.addObject("companies", companyService.findAllCompanies());
		return model;
	}
	
	@RequestMapping(path = "addComputer", method = RequestMethod.POST)
	public ModelAndView addComputer(@RequestParam Map<String,String> requestParams) {	
		Map<String,String> errors = FormValidation.validFormRequest(requestParams);
		
		if (errors.isEmpty()) {
			Computer computer = ComputerMapper.requestToComputer(requestParams);
			computer.setCompany(companyService.findCompany(computer.getCompany().getId()));
			computerService.addComputer(computer);
			return new ModelAndView("redirect:/index");
		} else {
			ModelAndView model = new ModelAndView("views/addComputer.jsp");
			model.addObject("errors", errors);
			model.addObject("companies", companyService.findAllCompanies());
			return model;
		}
	}
	
	@RequestMapping(path = "deleteComputer", method = RequestMethod.POST) 
	public ModelAndView deleteComputer(@RequestParam Map<String,String> requestParams) {
		String[] computersId = requestParams.get("selection").split(",");

		for (String id : computersId) {
			computerService.deleteComputer(Integer.parseInt(id));
		}
		
		return new ModelAndView("redirect:/index");
	}
	
	@RequestMapping(path = "editComputer", method = RequestMethod.GET)
	public ModelAndView updateComputerPage(@RequestParam Map<String,String> requestParams) {
		ModelAndView model = new ModelAndView("views/editComputer.jsp");
		int id = ParserInteger.parserInt(requestParams.get("id"));
		Computer computer = null;
		
		if (id == -1) {
			new ModelAndView("redirect:/index");
		} else {
			computer = computerService.findComputer(id);
			if (computer == null) {
				new ModelAndView("redirect:/index");
			}
			else {
				List<Company> companies = companyService.findAllCompanies();
				model.addObject("companies", companies);
				model.addObject("computer", computer);
			}
		}
		return model;
	}
	
	@RequestMapping(path = "editComputer", method = RequestMethod.POST)
	public ModelAndView editComputer(@RequestParam Map<String,String> requestParams) {
		Map<String,String> errors = FormValidation.validFormRequest(requestParams);
		
		if(errors.isEmpty()) {
			Computer computer = ComputerMapper.requestToComputer(requestParams);
			computer.setCompany(companyService.findCompany(computer.getCompany().getId()));
			computer.setId(ParserInteger.parserInt(requestParams.get("id")));
			
			computerService.updateComputer(computer);
			return new ModelAndView("redirect:/index");
			
		} else {
			ModelAndView model = new ModelAndView("views/editComputer.jsp");
			model.addObject("errors", errors);
			model.addObject("companies", companyService.findAllCompanies());
			return model;
		}
	}
}
