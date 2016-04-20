package com.excilys.computerDatabase.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.mapper.ComputerDTOMapper;
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
		model.addObject("computerDTO", new ComputerDTO());
		model.addObject("companies", companyService.findAllCompanies());
		return model;
	}

	@RequestMapping(path = "addComputer", method = RequestMethod.POST)
	public ModelAndView addComputer(@Valid @ModelAttribute("computerDTO") ComputerDTO computerDTO, BindingResult result ) {
		
		if(result.hasErrors()) {
			ModelAndView model = new ModelAndView("views/addComputer.jsp");
			model.addObject("computerDTO", computerDTO);
			model.addObject("companies", companyService.findAllCompanies());
			return model;
		}
		else {
			computerService.addComputer(computerDTO);
			return new ModelAndView("redirect:/index");
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
	public ModelAndView updateComputerPage(@RequestParam(value="id") String idComputer) {
		ModelAndView model = new ModelAndView("views/editComputer.jsp");
		int id = ParserInteger.parserInt(idComputer);
		ComputerDTO computerDTO = null;
		
		if (id == -1) {
			new ModelAndView("redirect:/index");
		} else {
			computerDTO = computerService.findComputer(id);
			if (computerDTO == null) {
				new ModelAndView("redirect:/index");
			}
			else {
				List<Company> companies = companyService.findAllCompanies();
				model.addObject("companies", companies);
				model.addObject("computerDTO", computerDTO);
			}
		}
		return model;
	}
	
	@RequestMapping(path = "editComputer", method = RequestMethod.POST)
	public ModelAndView editComputer(@Valid @ModelAttribute("computerDTO") ComputerDTO computerDTO, BindingResult result ) {
		
		if(result.hasErrors()) {
			ModelAndView model = new ModelAndView("views/editComputer.jsp");
			model.addObject("computerDTO", computerDTO);
			model.addObject("companies", companyService.findAllCompanies());
			return model;
		}
		else {
			computerDTO = computerService.updateComputer(computerDTO);
			return new ModelAndView("redirect:/index");
		}
	}
}
