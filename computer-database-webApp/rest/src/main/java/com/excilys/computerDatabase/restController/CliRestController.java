package com.excilys.computerDatabase.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.UserInfo;
import com.excilys.computerDatabase.page.Page;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.service.UserService;

@RestController
public class CliRestController {
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private ComputerService computerService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/companies", method = RequestMethod.GET, produces = "application/json")
	public List<Company> getCompanies() {
		List<Company> companies = companyService.findAllCompanies();
		
		return companies;
	}
	
	@RequestMapping("/computers")
	public List<ComputerDTO> getComputers() {
		List<ComputerDTO> computersDTO = computerService.findAllComputers();
		
		return computersDTO;
	}
	
	@RequestMapping(value = "/page/{pageNumber}/{nbComputers}", method = RequestMethod.GET, produces = "application/json")
	public List<ComputerDTO> getComputersPage(@PathVariable int pageNumber,@PathVariable int nbComputers) {
		Page page = new Page(pageNumber,nbComputers);
		page = computerService.findPageComputers(page);
		
		return page.getComputersList();
	}
	
	@RequestMapping(value="/insert",  method = RequestMethod.POST)
	public ComputerDTO insertComputer(@RequestBody ComputerDTO computerDTO) {
		if (computerDTO.getCompanyId() > 0) {
			Company company = companyService.findCompany(computerDTO.getCompanyId());
			computerDTO.setCompanyName(company.getName());
		}
		
		computerDTO = computerService.addComputer(computerDTO);
	
		return computerDTO;
	}
	
	@RequestMapping(value="/merge",  method = RequestMethod.POST)
	public ComputerDTO mergeComputer(@RequestBody ComputerDTO computerDTO) {
		
		if (computerDTO.getCompanyId() > 0) {
			Company company = companyService.findCompany(computerDTO.getCompanyId());
			computerDTO.setCompanyName(company.getName());
		}
		
		computerDTO = computerService.updateComputer(computerDTO);
		
		return computerDTO;
		
	}
	
	@RequestMapping(value = "/computer/{id}", method = RequestMethod.GET, produces = "application/json")
	public ComputerDTO getComputer(@PathVariable String id) {
		ComputerDTO computer = new ComputerDTO();
		
		computer = computerService.findComputer(Integer.parseInt(id));
		
		return computer;
	}
	
	@RequestMapping(value="/delete/{id}",  method = RequestMethod.GET, produces = "application/json")
	public int deleteComputer(@PathVariable int id) {
		
		int deleteResponse = computerService.deleteComputer(id);
		
		return deleteResponse;
	}
	
	@RequestMapping(value="/deleteCompany/{id}",  method = RequestMethod.GET, produces = "application/json")
	public int deleteCompany(@PathVariable int id) {
		
		int deleteResponse = companyService.delete(id);
		
		return deleteResponse;
	}
	
	@RequestMapping(value="/insertUser",  method = RequestMethod.POST)
	public UserInfo insertUser(@RequestBody UserInfo user) {

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		
		user.setPassword(hashedPassword);
		
		userService.insertUserInfo(user);
		
		return user;
	}
	
	

}
