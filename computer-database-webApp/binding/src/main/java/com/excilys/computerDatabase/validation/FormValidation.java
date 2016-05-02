//package com.excilys.computerDatabase.validation;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//public class FormValidation {
//
//	public static Map<String,String> validForm(HttpServletRequest request) {
//		Map<String,String> erreurs = new HashMap<String,String>();
//		
//		String name = request.getParameter("computerName");
//		
//		String introduced = request.getParameter("introduced");
//		String discontinued = request.getParameter("discontinued");
//		
//		try {
//			ValidationComputer.nameValidation(name);
//		}
//		catch(Exception e) {
//			erreurs.put("name", e.getMessage());
//		}
//		
//		try {
//			ValidationComputer.introducedValidation(introduced);
//		} catch(Exception e) {
//			erreurs.put("introduced", e.getMessage());
//		}
//		
//		try {
//			ValidationComputer.discontinuedValidation(discontinued,introduced);
//		} catch(Exception e) {
//			erreurs.put("discontinued", e.getMessage());
//		}
//		
//		try {
//			ValidationComputer.companyValidation(request.getParameter("companyId"));
//		}
//		catch(Exception e) {
//			erreurs.put("company", e.getMessage());
//		}
//		
//		return erreurs;
//	}
//	
//	public static Map<String,String> validFormRequest(Map<String,String> request) {
//		Map<String,String> erreurs = new HashMap<String,String>();
//		
//		String name = request.get("computerName");
//		
//		String introduced = request.get("introduced");
//		String discontinued = request.get("discontinued");
//		
//		try {
//			ValidationComputer.nameValidation(name);
//		}
//		catch(Exception e) {
//			erreurs.put("name", e.getMessage());
//		}
//		
//		try {
//			ValidationComputer.introducedValidation(introduced);
//		} catch(Exception e) {
//			erreurs.put("introduced", e.getMessage());
//		}
//		
//		try {
//			ValidationComputer.discontinuedValidation(discontinued,introduced);
//		} catch(Exception e) {
//			erreurs.put("discontinued", e.getMessage());
//		}
//		
//		try {
//			ValidationComputer.companyValidation(request.get("companyId"));
//		}
//		catch(Exception e) {
//			erreurs.put("company", e.getMessage());
//		}
//		
//		return erreurs;
//		
//	}
//	
//}
