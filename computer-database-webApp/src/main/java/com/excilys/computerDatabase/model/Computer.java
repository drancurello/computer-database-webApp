package com.excilys.computerDatabase.model;

import java.time.LocalDate;

public class Computer {

	private long id;
	private String name;
	private LocalDate introducedTime;
	private LocalDate discontinuedTime;
	private Company company;

	public Computer() {
		this.name = null;
		this.introducedTime = null;
		this.discontinuedTime = null;
		this.company = null;
	}

	public Computer(String name, LocalDate introducedTime, LocalDate discontinue, Company company) {
		this.name = name;
		this.introducedTime = introducedTime;
		this.discontinuedTime = discontinue;
		this.company = company;
	}

	public Computer(String name, Company company) {
		this.name = name;
		this.introducedTime = null;
		this.discontinuedTime = null;
		this.company = company;
	}

	public Computer(String name) {
		this.name = name;
		this.introducedTime = null;
		this.discontinuedTime = null;
		this.company = null;
	}

	public Computer(String name, LocalDate introducedTime, Company company) {
		this.name = name;
		this.introducedTime = introducedTime;
		this.discontinuedTime = null;
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
	public String toString() {
		return "id=" + id + ", name=" + name + ", introducedTime=" + introducedTime + ", discontinuedTime="
				+ discontinuedTime + ", company=" + company.getName();
	}

}
