package com.excilys.computerDatabase.model;

public class Company {

	private int id;
	private String name;

	public Company(String name) {
		this.name = name;
	}

	public Company(int id) {
		this.id = id;
	}

	public Company() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name;
	}

}
