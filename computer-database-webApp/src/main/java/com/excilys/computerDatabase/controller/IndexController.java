package com.excilys.computerDatabase.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerDatabase.controller.mapper.RequestMapper;
import com.excilys.computerDatabase.page.Page;
import com.excilys.computerDatabase.service.ComputerService;

@Controller
public class IndexController {
	
	@Autowired 
	private ComputerService computerService;
	
	@RequestMapping(path = "index", method = RequestMethod.GET)
	public ModelAndView indexPage(@RequestParam Map<String,String> requestParams) {
		
		ModelAndView model = new ModelAndView("index.jsp");
		
		Page indexPage = RequestMapper.requestToPage(requestParams);
		indexPage = computerService.findPageComputers(indexPage);
		
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
		model.addObject("page", indexPage);
		
		return model;
	}
	
}
