package com.isa.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Region {

	private Long id; 
	
	private String name;
	
	private Integer rowNum;
	private Integer colNum;
	private List<Table> tables;
	
	public Region() {
		super();
	}

	public Region(Long id, String name, Integer rowNum, Integer colNum) {
		super();
		this.name = name;
		this.rowNum = rowNum;
		this.colNum = colNum;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "REGION_ID")	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "REGION_TABLES",
			joinColumns = @JoinColumn(name = "REGION_ID"),
			inverseJoinColumns = @JoinColumn(name = "TABLE_ID"))	
	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public Integer getColNum() {
		return colNum;
	}

	public void setColNum(Integer colNum) {
		this.colNum = colNum;
	}	
}
