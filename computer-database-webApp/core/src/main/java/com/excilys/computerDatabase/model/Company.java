package com.excilys.computerDatabase.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Company.
 */
@SuppressWarnings("serial")
@Entity
@XmlRootElement
@Table(name="company")
public class Company implements Serializable {

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	/** The name. */
	@Column
	private String name;
	
//	@OneToMany
//	private List<Computer> computers;

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
		Company other = (Company) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name;
	}

}
