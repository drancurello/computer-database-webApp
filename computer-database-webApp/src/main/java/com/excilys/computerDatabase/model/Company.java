package com.excilys.computerDatabase.model;

/**
 * The Class Company.
 */
public class Company {

	/** The id. */
	private long id;
	
	/** The name. */
	private String name;

	/**
	 * Instantiates a new company with a name.
	 *
	 * @param name the name
	 */
	public Company(String name) {
		this.name = name;
	}

	/**
	 * Instantiates a new company.
	 *
	 * @param id the id
	 */
	public Company(int id) {
		this.id = id;
		this.name = null;
	}

	/**
	 * Instantiates a new company.
	 */
	public Company() {
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

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name;
	}

}
