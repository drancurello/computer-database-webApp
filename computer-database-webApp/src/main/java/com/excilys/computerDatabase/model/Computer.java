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
	 * @param name the name
	 * @param introducedTime the introduced time
	 * @param discontinue the discontinue
	 * @param company the company
	 */
	public Computer(String name, LocalDate introducedTime, LocalDate discontinue, Company company) {
		this.name = name;
		this.introducedTime = introducedTime;
		this.discontinuedTime = discontinue;
		this.company = company;
	}

	/**
	 * Instantiates a new computer with a name and a company.
	 *
	 * @param name the name
	 * @param company the company
	 */
	public Computer(String name, Company company) {
		this.name = name;
		this.introducedTime = null;
		this.discontinuedTime = null;
		this.company = company;
	}

	/**
	 * Instantiates a new computer only with a name.
	 *
	 * @param name the name
	 */
	public Computer(String name) {
		this.name = name;
		this.introducedTime = null;
		this.discontinuedTime = null;
		this.company = null;
	}

	/**
	 * Instantiates a new computer with a name, an introduce date and a company.
	 *
	 * @param name the name
	 * @param introducedTime the introduced time
	 * @param company the company
	 */
	public Computer(String name, LocalDate introducedTime, Company company) {
		this.name = name;
		this.introducedTime = introducedTime;
		this.discontinuedTime = null;
		this.company = company;
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
	 * Gets the introduced time.
	 *
	 * @return the introduced time
	 */
	public LocalDate getIntroducedTime() {
		return introducedTime;
	}

	/**
	 * Sets the introduced time.
	 *
	 * @param introducedTime the new introduced time
	 */
	public void setIntroducedTime(LocalDate introducedTime) {
		this.introducedTime = introducedTime;
	}

	/**
	 * Gets the discontinued time.
	 *
	 * @return the discontinued time
	 */
	public LocalDate getDiscontinuedTime() {
		return discontinuedTime;
	}

	/**
	 * Sets the discontinued time.
	 *
	 * @param discontinuedTime the new discontinued time
	 */
	public void setDiscontinuedTime(LocalDate discontinuedTime) {
		this.discontinuedTime = discontinuedTime;
	}

	/**
	 * Gets the company.
	 *
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * Sets the company.
	 *
	 * @param company the new company
	 */
	public void setCompany(Company company) {
		this.company = company;
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
	 * @param i the new id
	 */
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

}
