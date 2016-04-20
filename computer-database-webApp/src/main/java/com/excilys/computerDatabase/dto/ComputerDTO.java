package com.excilys.computerDatabase.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.excilys.computerDatabase.validation.annotations.DateFormat;
import com.excilys.computerDatabase.validation.annotations.DateBefore;
import com.excilys.computerDatabase.validation.annotations.DateBefore1970;

/**
 * The Class ComputerDTO.
 */
@DateBefore
public class ComputerDTO {

	/** The id. */
	private long id;
	
	/** The name. */
	@NotEmpty(message = "The name can't be empty")
	@NotNull(message = "The name can't be null")
	private String name;
	
	/** The introduced. */
	@DateBefore1970
	@DateFormat
	private String introduced;
	
	/** The discontinued. */
	@DateBefore1970
	@DateFormat
	private String discontinued;
	
	/** The company id. */
	private long companyId;
	
	/** The company Name */
	private String companyName;
	
	/**
	 * Instantiates a new computer dto.
	 */
	public ComputerDTO() {}

	/**
	 * Gets the id.
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the introduced.
	 * @return the introduced
	 */
	public String getIntroduced() {
		return introduced;
	}

	/**
	 * Sets the introduced.
	 * @param introduced the new introduced
	 */
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	/**
	 * Gets the discontinued.
	 * @return the discontinued
	 */
	public String getDiscontinued() {
		return discontinued;
	}

	/**
	 * Sets the discontinued.
	 * @param discontinued the new discontinued
	 */
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * Gets the company id.
	 * @return the company id
	 */
	public long getCompanyId() {
		return companyId;
	}

	/**
	 * Sets the company id.
	 * @param companyId the new company id
	 */
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}	
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComputerDTO other = (ComputerDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", Company=" + companyName;
	}
	
	public static class Builder {
		private long bId;
		private String bName;
		private String bIntroduced;
		private String bDiscontinued;
		private long bCompanyId;
		private String bCompanyName;
		
		public Builder idComputer(long id) {
			this.bId = id;
			return this;
		}
		
		public Builder nameComputer(String name) {
			this.bName = name;
			return this;
		}
		
		public Builder introducedComputer(String introduced) {
			this.bIntroduced = introduced;
			return this;
		}
		
		public Builder discontinuedComputer(String discontinued) {
			this.bDiscontinued = discontinued;
			return this;
		}
		
		public Builder companyComputer(long companyId, String companyName) {
			this.bCompanyId = companyId;
			this.bCompanyName = companyName;
			return this;
		}
		
		public ComputerDTO build() {
			ComputerDTO computer = new ComputerDTO();
			computer.id = bId;
			computer.name = bName;
			computer.introduced = bIntroduced;
			computer.discontinued = bDiscontinued;
			computer.companyId = bCompanyId;
			computer.companyName = bCompanyName;
			return computer;
		}
		
	}
	
	
}
