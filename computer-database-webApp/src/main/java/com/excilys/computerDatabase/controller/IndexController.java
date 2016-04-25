package com.excilys.computerDatabase.controller;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerDatabase.controller.mapper.RequestMapper;
import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.page.Page;
import com.excilys.computerDatabase.parser.ParserCountryDate;
import com.excilys.computerDatabase.service.ComputerService;

@Controller
public class IndexController {
	
	@Autowired 
	private ComputerService computerService;
	
	@RequestMapping(path = "index", method = RequestMethod.GET)
	public ModelAndView indexPage(@RequestParam Map<String,String> requestParams) throws ParseException {
		
		ModelAndView model = new ModelAndView("index.jsp");
		
		Page indexPage = RequestMapper.requestToPage(requestParams);
		indexPage = computerService.findPageComputers(indexPage);
	
		String locale = LocaleContextHolder.getLocale().toString();
		
		if (requestParams.get("search") != null) {
			String search = requestParams.get("search");
			indexPage = computerService.search(search, indexPage);
			if (indexPage.getPageNumber() > indexPage.getNbPage()) {
				indexPage.previousPage();
				indexPage = computerService.search(search, indexPage);
			}
			model.addObject("search", search);
		} else {
			if (indexPage.getPageNumber() > indexPage.getNbPage()) {
				indexPage.previousPage();
				indexPage = computerService.findPageComputers(indexPage);
			}
		}
		
		if (locale.equals("fr")) {
			for (ComputerDTO computer:indexPage.getComputersList()) {
				if(computer.getIntroduced() != null) {
					computer.setIntroduced(ParserCountryDate.toFrenchDate(computer.getIntroduced()));
				}
				if(computer.getDiscontinued() != null) {
					computer.setDiscontinued(ParserCountryDate.toFrenchDate(computer.getDiscontinued()));
				}
			}
		}
		
		model.addObject("page", indexPage);
		
		return model;
	}
	
}
