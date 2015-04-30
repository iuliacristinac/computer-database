package com.excilys.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.excilys.util.ValidDateFormat;

public class ComputerDTO {

	private long id;

	@NotBlank
	@Size(min=0, max=100)
	private String name;
	
	@ValidDateFormat
	private String introduced;
	
	@ValidDateFormat
	private String discontinued;	

	private long companyId;
	
	@Size(min=0, max=100)
	private String companyName;
	
	public ComputerDTO() { }
	
	public ComputerDTO(String name, String introduced, String discontinued, long companyId, String companyName) {
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
		this.companyName = companyName;
	}
	
	public ComputerDTO(long id, String name, String introduced, String discontinued, long companyId, String companyName) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
		this.companyName = companyName;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduced() {
		return introduced;
	}
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	public String getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
	public long getCompanyId() {
		return companyId;
	}
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
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ComputerDTO [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", introduced=");
		builder.append(introduced);
		builder.append(", discontinued=");
		builder.append(discontinued);
		builder.append(", companyId=");
		builder.append(companyId);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append("]");
		return builder.toString();
	}	
	
}
