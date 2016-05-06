package com.excilys.computerDatabase.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.parser.ParserCountryDate;
import com.excilys.computerDatabase.parser.ParserInteger;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;

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
	public ModelAndView addComputer(@Valid @ModelAttribute("computerDTO") ComputerDTO computerDTO, BindingResult result ) throws ParseException {
		String locale = LocaleContextHolder.getLocale().toString();
		
		if(result.hasErrors()) {
			ModelAndView model = new ModelAndView("views/addComputer.jsp");
			model.addObject("computerDTO", computerDTO);
			model.addObject("companies", companyService.findAllCompanies());
			return model;
		}
		else {
			if (locale.equals("fr")) {
				if(computerDTO.getIntroduced() != null && !computerDTO.getIntroduced().equals("")) {
					computerDTO.setIntroduced(ParserCountryDate.toEnglishDate(computerDTO.getIntroduced()));
				}
				if(computerDTO.getDiscontinued() != null && !computerDTO.getDiscontinued().equals("")) {
					computerDTO.setDiscontinued(ParserCountryDate.toEnglishDate(computerDTO.getDiscontinued()));
				}
			}
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
	public ModelAndView updateComputerPage(@RequestParam(value="id") String idComputer) throws ParseException {
		ModelAndView model = new ModelAndView("views/editComputer.jsp");
		String locale = LocaleContextHolder.getLocale().toString();
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
				if (locale.equals("fr")) {
					if(computerDTO.getIntroduced() != null && !computerDTO.getIntroduced().equals("")) {
						computerDTO.setIntroduced(ParserCountryDate.toFrenchDate(computerDTO.getIntroduced()));
					}
					if(computerDTO.getDiscontinued() != null && !computerDTO.getDiscontinued().equals("")) {
						computerDTO.setDiscontinued(ParserCountryDate.toFrenchDate(computerDTO.getDiscontinued()));
					}
				}
				List<Company> companies = companyService.findAllCompanies();
				model.addObject("companies", companies);
				model.addObject("computerDTO", computerDTO);
			}
		}
		return model;
	}
	
	@RequestMapping(path = "editComputer", method = RequestMethod.POST)
	public ModelAndView editComputer(@Valid @ModelAttribute("computerDTO") ComputerDTO computerDTO, BindingResult result ) throws ParseException {
		String locale = LocaleContextHolder.getLocale().toString();
		
		if(result.hasErrors()) {
			ModelAndView model = new ModelAndView("views/editComputer.jsp");
			model.addObject("computerDTO", computerDTO);
			model.addObject("companies", companyService.findAllCompanies());
			return model;
		}
		else {
			if (locale.equals("fr")) {
				if(computerDTO.getIntroduced() != null && !computerDTO.getIntroduced().equals("")) {
					computerDTO.setIntroduced(ParserCountryDate.toEnglishDate(computerDTO.getIntroduced()));
				}
				if(computerDTO.getDiscontinued() != null && !computerDTO.getDiscontinued().equals("")) {
					computerDTO.setDiscontinued(ParserCountryDate.toEnglishDate(computerDTO.getDiscontinued()));
				}
			}
			computerDTO = computerService.updateComputer(computerDTO);
			return new ModelAndView("redirect:/index");
		}
	}
}
