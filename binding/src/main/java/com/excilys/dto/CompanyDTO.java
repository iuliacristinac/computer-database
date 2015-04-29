package com.excilys.dto;

import javax.validation.constraints.Size;

public class CompanyDTO {
	
	private long id;

	@Size(min=0, max=100)
	private String name;
	
	public CompanyDTO() {}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		if (id < 0) {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyDTO [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
}
