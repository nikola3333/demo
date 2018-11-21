package com.isa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.isa.entity.Table;

public interface TableService {
	
	public List<Table> findAll();
	public Table findOne(Long id);
	public Table save(Table t);
	public void delete(Table t);
	public boolean checkIfReserved(Long id, Date reservationTime, Date stayTime);
	public ArrayList<Table> checkVersion(ArrayList<Table> tables, Date reservationTime, Date stayTime);
}
