package com.isa.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

@Entity (name= "tables")
public class Table {

	private Long id;
	private Long version;
	private Integer oznakaStola;
	private Integer colNum;
	private Integer rowNum;
	private Integer seatNum;
	private List<Reservation> reservations;
	
	public Table() {
		super();
	}


	public Table(Integer oznakaStola, Integer colNum, Integer rowNum,Integer seatNum) {
		super();
		this.oznakaStola = oznakaStola;
		this.colNum = colNum;
		this.rowNum = rowNum;
		this.seatNum = seatNum;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TABLE_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToMany
	@JoinTable(
			name = "TABLE_RESERVATIONS",
			joinColumns = @JoinColumn(name = "TABLE_ID"),
			inverseJoinColumns = @JoinColumn(name = "RESERVATION_ID"))
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Integer getOznakaStola() {
		return oznakaStola;
	}

	public void setOznakaStola(Integer oznakaStola) {
		this.oznakaStola = oznakaStola;
	}

	public Integer getColNum() {
		return colNum;
	}

	public void setColNum(Integer colNum) {
		this.colNum = colNum;
	}

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	@Version
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}


	public Integer getSeatNum() {
		return seatNum;
	}


	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}
	
	
}
