package com.excilys.computerDatabase.mapper;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.page.Page;
import com.excilys.computerDatabase.parser.ParserInteger;

@Component
public class PageMapper {
	
	public static Page requestToPage(HttpServletRequest request) {
		int pageNumber = 1;
		int nbComputersPage = 50;
	
		if (ParserInteger.parserInt(request.getParameter("nbComputersPage")) != -1) {
			nbComputersPage = ParserInteger.parserInt(request.getParameter("nbComputersPage"));
		}
		
		if (ParserInteger.parserInt(request.getParameter("page")) != -1 ) {
			if (Integer.parseInt(request.getParameter("page")) == 0) {
				pageNumber = 1;
			} else {
				pageNumber = ParserInteger.parserInt(request.getParameter("page"));
			}
		}
		
		Page indexPage = new Page(pageNumber, nbComputersPage);
		
		if (request.getParameter("order") != null) {
			indexPage.setOrder(request.getParameter("order"));
		}
		
		if (request.getParameter("type") != null) {
			indexPage.setType(request.getParameter("type"));
		}
		
		return indexPage;
	}
	

}
