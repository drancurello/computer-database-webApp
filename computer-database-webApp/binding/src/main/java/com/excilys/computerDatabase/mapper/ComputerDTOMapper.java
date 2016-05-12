package com.excilys.computerDatabase.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;

/**
 * The Class ComputerDTOMapper.
 */
@Component
public class ComputerDTOMapper {

	/**
	 * To computerDTO.
	 * @param A computer 
	 * @return A computerDTO
	 */
	public static ComputerDTO toComputerDTO(Computer computer) {
		
		if (computer == null) {
			return null;
		}
		
		ComputerDTO.Builder builder = new ComputerDTO.Builder();
		builder.idComputer(computer.getId());
		builder.nameComputer(computer.getName());
		
		if (computer.getIntroducedTime() != null) {
			builder.introducedComputer(String.valueOf(computer.getIntroducedTime()));
		}
		if (computer.getDiscontinuedTime() != null) {
			builder.discontinuedComputer(String.valueOf(computer.getDiscontinuedTime()));
		}
		
		if (computer.getCompany() != null) {
			builder.companyComputer(computer.getCompany().getId(), computer.getCompany().getName());
		}
		
		return builder.build();
	}
	
	/**
	 * To computer.
	 * @param A computerDTO
	 * @return A computer
	 */
	public static Computer toComputer(ComputerDTO computerDTO) {
		
		if (computerDTO == null) {
			return null;
		}
		
		Computer computer = new Computer();
		
		computer.setId(computerDTO.getId());
		computer.setName(computerDTO.getName());
		
		if (computerDTO.getIntroduced() != null && !computerDTO.getIntroduced().equals("")) {
			computer.setIntroducedTime(LocalDate.parse(computerDTO.getIntroduced()));
		}
		
		if (computerDTO.getDiscontinued() != null && !computerDTO.getDiscontinued().equals("")) {
			computer.setDiscontinuedTime(LocalDate.parse(computerDTO.getDiscontinued()));
		}
		
		if (computerDTO.getCompanyId() > 0) {	
			Company company = new Company();
			company.setId(computerDTO.getCompanyId());
			company.setName(computerDTO.getCompanyName());
			computer.setCompany(company);
		}
		
		return computer;
	}
	
	
	
}
