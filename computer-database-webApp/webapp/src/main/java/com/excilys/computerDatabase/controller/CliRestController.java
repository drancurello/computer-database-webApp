package com.excilys.computerDatabase.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.page.Page;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;

@RestController
public class CliRestController {
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private ComputerService computerService;
	
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
	public ComputerDTO insertComputer(@RequestBody @Valid ComputerDTO computerDTO) {
		if (computerDTO.getCompanyId() > 0) {
			Company company = companyService.findCompany(computerDTO.getCompanyId());
			computerDTO.setCompanyName(company.getName());
		}
		
		computerDTO = computerService.addComputer(computerDTO);
	
		return computerDTO;
	}
	
	@RequestMapping(value="/merge",  method = RequestMethod.POST)
	public ComputerDTO mergeComputer(@RequestBody @Valid ComputerDTO computerDTO) {
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

}
