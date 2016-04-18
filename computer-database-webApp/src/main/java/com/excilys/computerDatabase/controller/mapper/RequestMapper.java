package com.excilys.computerDatabase.controller.mapper;

import java.util.Map;

import com.excilys.computerDatabase.page.Page;
import com.excilys.computerDatabase.parser.ParserInteger;

public class RequestMapper {

	public static Page requestToPage(Map<String,String> requestParams) {
		
		int pageNumber = 1;
		int nbComputersPage = 50;
		
		if (ParserInteger.parserInt(requestParams.get("nbComputersPage")) != -1) {
			nbComputersPage = ParserInteger.parserInt(requestParams.get("nbComputersPage"));
		}
		
		if (ParserInteger.parserInt(requestParams.get("page")) != -1 ) {
			if (Integer.parseInt(requestParams.get("page")) == 0) {
				pageNumber = 1;
			} else {
				pageNumber = ParserInteger.parserInt(requestParams.get("page"));
			}
		}
		
		Page indexPage = new Page(pageNumber, nbComputersPage);
		
		if (requestParams.get("order") != null) {
			indexPage.setOrder(requestParams.get("order"));
		}
		
		if (requestParams.get("type") != null) {
			indexPage.setType(requestParams.get("type"));
		}
		
		return indexPage;
		
	}
	
	
}
