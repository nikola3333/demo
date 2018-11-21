package com.isa.service;

import java.util.List;

import com.isa.entity.Drink;
import com.isa.entity.Foodstuffs;
import com.isa.entity.Guest;
import com.isa.entity.Reservation;

public interface ReservationService {

	public List<Reservation> findAll();
	public Reservation findOne(Long id);
	public Reservation save(Reservation r,Guest g);
	public void delete(Long id);
	public Reservation addToInvited(Long reservationId, Guest g);
	public Reservation acceptInvitation(Long reservationId,Guest g);
	public Reservation declineInvitation(Long reservationId,Guest g);
	public Reservation addFood(Guest g, Long id, Foodstuffs item);
	public Reservation addDrink(Guest g, Long id, Drink item);
	public Reservation deleteFood(Guest g, Long id, Long itemId);
	public Reservation deleteDrink(Guest g, Long id, Long itemId);
	public List<Reservation> getReservations(Guest g);
	public List<Reservation> getHistory(Guest g);
}
