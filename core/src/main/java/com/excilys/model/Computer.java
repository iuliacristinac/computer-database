package com.excilys.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="computer")
public class Computer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="introduced")
	@Type(type="com.excilys.util.CustomLocalDateTimeUserType")
	private LocalDateTime introduced;
	
	@Column(name="discontinued")
	@Type(type="com.excilys.util.CustomLocalDateTimeUserType")
	private LocalDateTime discontinued;
	
	@OneToOne
	private Company company;
	
	public Computer() {}
	
	public Computer(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Computer(long id, String name, Company company) {
		this.id = id;
		this.name = name;
		this.company = company;
	}
	
	public Computer(long id, String name, LocalDateTime introduced, Company company) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.company = company;
	}
	
	public Computer( String name, LocalDateTime introduced, LocalDateTime discontinued, Company company) {
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}

	
	public Computer(long id, String name, LocalDateTime introduced, LocalDateTime discontinued, Company company) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
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

	public LocalDateTime getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}

	public LocalDateTime getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDateTime discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Computer [id = ");
		builder.append(id);
		builder.append(", name = ");
		builder.append(name);
		builder.append(", introduced = ");
		builder.append(introduced);
		builder.append(", discontinued = ");
		builder.append(discontinued);
		builder.append(", company = ");
		builder.append(company);
		builder.append("]");
		return builder.toString();
	}
}
