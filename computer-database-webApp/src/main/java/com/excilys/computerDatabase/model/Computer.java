package com.excilys.computerDatabase.model;

import java.time.LocalDate;

/**
 * The Class Computer.
 */
public class Computer {

	/** The id. */
	private long id;
	
	/** The name. */
	private String name;
	
	/** The introduced time. */
	private LocalDate introducedTime;

	/** The discontinued time. */
	private LocalDate discontinuedTime;
	
	/** The company. */
	private Company company;

	/**
	 * Instantiates a new computer by default.
	 */
	public Computer() {
		this.name = null;
		this.introducedTime = null;
		this.discontinuedTime = null;
		this.company = null;
	}

	/**
	 * Instantiates a new computer with all the parameters.
	 *
	 *@param an id, a name, an introduced date, a discontinued date and a company
	 */
	public Computer(int id, String name, LocalDate introducedTime, LocalDate discontinue, Company company) {
		this.id = id;
		this.name = name;
		this.introducedTime = introducedTime;
		this.discontinuedTime = discontinue;
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getIntroducedTime() {
		return introducedTime;
	}

	public void setIntroducedTime(LocalDate introducedTime) {
		this.introducedTime = introducedTime;
	}

	public LocalDate getDiscontinuedTime() {
		return discontinuedTime;
	}

	public void setDiscontinuedTime(LocalDate discontinuedTime) {
		this.discontinuedTime = discontinuedTime;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public long getId() {
		return id;
	}

	public void setId(long i) {
		this.id = i;
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
		if (this == obj) {
			return true;
		}	
		if (obj == null) {
			return false;
		}	
		if (getClass() != obj.getClass()) {
			return false;
		}
		Computer other = (Computer) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", introducedTime=" + introducedTime + ", discontinuedTime="
				+ discontinuedTime + ", company=" + company.getName();
	}
	
	
	public static class Builder {
		private int bId;
		private String bName;
		private LocalDate bIntroduced;
		private LocalDate bDiscontinued;
		private Company bCompany;
		
		public Builder idComputer(int id) {
			this.bId = id;
			return this;
		}
		
		public Builder nameComputer(String name) {
			this.bName = name;
			return this;
		}
		
		public Builder introducedComputer(LocalDate introduced) {
			this.bIntroduced = introduced;
			return this;
		}
		
		public Builder discontinuedComputer(LocalDate discontinued) {
			this.bDiscontinued = discontinued;
			return this;
		}
		
		public Builder companyComputer(Company company) {
			this.bCompany = company;
			return this;
		}
		
		public Computer build() {
			return new Computer(bId, bName, bIntroduced, bDiscontinued, bCompany);
		}
		
	}
	
	
	

}
