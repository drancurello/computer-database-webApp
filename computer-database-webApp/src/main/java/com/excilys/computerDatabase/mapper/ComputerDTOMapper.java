package com.excilys.computerDatabase.mapper;

import java.time.LocalDate;

import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;

/**
 * The Class ComputerDTOMapper.
 */
public class ComputerDTOMapper {

	/**
	 * To computerDTO.
	 *
	 * @param A computer 
	 * @return A computerDTO
	 */
	public static ComputerDTO toComputerDTO(Computer computer) {
		ComputerDTO computerDTO = new ComputerDTO();
		
		computerDTO.setId(computer.getId());
		computerDTO.setName(computer.getName());
		
		if (computer.getIntroducedTime() != null) {
			computerDTO.setIntroduced(String.valueOf(computer.getIntroducedTime()));
		}
		if (computer.getDiscontinuedTime() != null) {
			computerDTO.setDiscontinued(String.valueOf(computer.getDiscontinuedTime()));
		}
		
		if (computer.getCompany().getName() != null) {
			computerDTO.setCompanyId(computer.getCompany().getId());
		}
		
		return computerDTO;
	}
	
	/**
	 * To computer.
	 *
	 * @param A computerDTO
	 * @return A computer
	 */
	public static Computer toComputer(ComputerDTO computerDTO) {
		Computer computer = new Computer();
		
		computer.setId(computerDTO.getId());
		computer.setName(computerDTO.getName());
		
		if (computerDTO.getIntroduced() != null) {
			computer.setIntroducedTime(LocalDate.parse(computerDTO.getIntroduced()));
		}
		
		if (computerDTO.getDiscontinued() != null) {
			computer.setDiscontinuedTime(LocalDate.parse(computerDTO.getDiscontinued()));
		}
		
		Company company = new Company();
		company.setId(computerDTO.getCompanyId());
		computer.setCompany(company);
		
		return computer;
	}
	
}
