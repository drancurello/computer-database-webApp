package com.excilys.computerDatabase.dto;

import java.time.LocalDate;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.model.Computer.Builder;

/**
 * The Class ComputerDTO.
 */

public class ComputerDTO {

	/** The id. */
	private long id;
	
	/** The name. */
	private String name;
	
	/** The introduced. */
	private String introduced = null;
	
	/** The discontinued. */
	private String discontinued = null;
	
	/** The company id. */
	private long companyId;
	
	/**
	 * Instantiates a new computer dto.
	 */
	public ComputerDTO() {}
	
	/**
	 * Instantiates a new computer dto.
	 *
	 * @param name the name
	 * @param introduced the introduced
	 * @param discontinued the discontinued
	 * @param companyId the company id
	 * @param companyName the company name
	 */
	public ComputerDTO(int id, String name, String introduced,String discontinued,long companyId) {
	
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the introduced.
	 *
	 * @return the introduced
	 */
	public String getIntroduced() {
		return introduced;
	}

	/**
	 * Sets the introduced.
	 *
	 * @param introduced the new introduced
	 */
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	/**
	 * Gets the discontinued.
	 *
	 * @return the discontinued
	 */
	public String getDiscontinued() {
		return discontinued;
	}

	/**
	 * Sets the discontinued.
	 *
	 * @param discontinued the new discontinued
	 */
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * Gets the company id.
	 *
	 * @return the company id
	 */
	public long getCompanyId() {
		return companyId;
	}

	/**
	 * Sets the company id.
	 *
	 * @param companyId the new company id
	 */
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
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
				+ discontinued;
	}
	
	public static class Builder {
		private int bId;
		private String bName;
		private String bIntroduced;
		private String bDiscontinued;
		private long bCompany;
		
		public Builder idComputer(int id) {
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
		
		public Builder companyComputer(long companyId) {
			this.bCompany = companyId;
			return this;
		}
		
		public ComputerDTO build() {
			return new ComputerDTO(bId, bName, bIntroduced, bDiscontinued, bCompany);
		}
		
	}
	
	
}
