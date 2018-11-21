package com.isa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Cook extends User{



	@Column
	private String dateOfBirth;	
	@Column
	private Integer dressSize;
	@Column
	private Integer shoesSize;
	
	public Cook() {
		super();
	}

	public Cook(String dateOfBirth, Integer dressSize, Integer shoesSize) {
		super();
		this.dateOfBirth = dateOfBirth;
		this.dressSize = dressSize;
		this.shoesSize = shoesSize;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getDressSize() {
		return dressSize;
	}

	public void setDressSize(Integer dressSize) {
		this.dressSize = dressSize;
	}

	public Integer getShoesSize() {
		return shoesSize;
	}

	public void setShoesSize(Integer shoesSize) {
		this.shoesSize = shoesSize;
	}



}
