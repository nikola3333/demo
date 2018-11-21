package com.isa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.entity.Reservation;
import com.isa.entity.Table;
import com.isa.repository.TableRepository;

@Service
@Transactional
public class TableServiceImpl implements TableService{

	@Autowired
	private TableRepository tableRepository;
	
	@Override
	public List<Table> findAll() {
		// TODO Auto-generated method stub
		return (List<Table>) tableRepository.findAll();
	}

	@Override
	public Table findOne(Long id) {
		// TODO Auto-generated method stub
		return tableRepository.findOne(id);
	}

	@Override
	public Table save(Table t) {

		return tableRepository.save(t);
	}

	@Override
	public void delete(Table t) {
		// TODO Auto-generated method stub
		tableRepository.delete(t);
	}

	@Override
	public boolean checkIfReserved(Long id, Date reservationTime, Date stayTime) {
		Table t = tableRepository.findOne(id);
		boolean status = false;
		for(Reservation r : t.getReservations()){
			if((r.getDate().before(reservationTime) && r.getStay().before(reservationTime))
					|| (r.getDate().after(reservationTime) && r.getStay().after(reservationTime) && r.getDate().after(stayTime))){
				continue;
			}
			else{
				status =  true;
			}
		}
		return status;
	}

	@Override
	public ArrayList<Table> checkVersion(ArrayList<Table> tables, Date reservationTime, Date stayTime) {
		ArrayList<Table> result = new ArrayList<Table>();
		for(Table t : tables){
			Table table = tableRepository.findOne(t.getId());
			if(!t.getVersion().equals(table.getId())){
				boolean status = checkIfReserved(t.getId(), reservationTime, stayTime);
				if(status) throw new IllegalArgumentException("One of selected tables has been reserved");
				result.add(table);	
			}
			else{
				result.add(table);
			}
		}
		return result;
	}


}
