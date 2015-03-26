package com.excilys.dto;

public class CompanyDTO {
	
	private long id;
	private String name;
	
	public CompanyDTO() {}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		if (id <= 0) {
			throw new IllegalArgumentException("CompanyDTO - Invalid Id!");
		}
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (name == null || name.trim().isEmpty()){
			throw new IllegalArgumentException("CompanyDTO - Invalid Name!");
		}
		this.name = name;
	}
}
