package com.isa.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.entity.Drink;
import com.isa.entity.Foodstuffs;
import com.isa.entity.Guest;
import com.isa.entity.GuestOrder;
import com.isa.entity.Reservation;
import com.isa.repository.ReservationRepository;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	private ReservationRepository reservationRepository;
	
	
	@Override
	public List<Reservation> findAll() {
		// TODO Auto-generated method stub
		return (List<Reservation>) reservationRepository.findAll();
	}

	@Override
	public Reservation findOne(Long id) {
		// TODO Auto-generated method stub
		return reservationRepository.findOne(id);
	}

	@Override
	public Reservation save(Reservation r,Guest g) {
		if(r.getGuests() == null)
			r.setGuests(new ArrayList<Guest>());
		r.getGuests().add(g);
		return reservationRepository.save(r);
	}

	@Override
	public void delete(Long id) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)+31);
		
		Reservation r = reservationRepository.findOne(id);
		if(cal.getTime().after(r.getDate())) throw new IllegalAccessError("You can't cancel reservation, less than 30 minutes to reservation");
		
		reservationRepository.delete(id);
	}
	@Override
	public Reservation addToInvited(Long reservationId, Guest g) {
		//int i = 0;
		Reservation r = reservationRepository.findOne(reservationId);
		
		if(r.getInvitedFriends() == null)
			r.setInvitedFriends(new ArrayList<Guest>());
		if(r.getInvitedFriends().size() == r.getSeatNum()-1 ) throw new IllegalArgumentException("Can't invite more friends");
		r.getInvitedFriends().add(g);

		return reservationRepository.save(r);
	}

	@Override
	public Reservation acceptInvitation(Long reservationId, Guest g) {
		Reservation reservation = reservationRepository.findOne(reservationId);
		Guest guest = null;
		for(Guest gg : reservation.getInvitedFriends()){
			if(gg.getId().equals(g.getId())){
				guest = gg;
			}
		}
		if(guest != null){
			reservation.getGuests().add(guest);
			reservation.getInvitedFriends().remove(guest);
		}
		return reservationRepository.save(reservation);
	}
	
	@Override
	public Reservation declineInvitation(Long reservationId,Guest g){
		Reservation reservation = reservationRepository.findOne(reservationId);
		Guest guest = null;
		for(Guest gg : reservation.getInvitedFriends()){
			if(gg.getId().equals(g.getId())){
				guest = gg;
			}
		}
		if(guest != null)
			reservation.getInvitedFriends().remove(guest);
		return reservationRepository.save(reservation);		
	}

	@Override
	public Reservation addFood(Guest g, Long id, Foodstuffs item) {
		Reservation reservation = reservationRepository.findOne(id);
		if(reservation.getOrders() == null){
			reservation.setOrders(new ArrayList<GuestOrder>());
			GuestOrder order = new GuestOrder(g);
			order.getFoodstuffs().add(item);
			reservation.getOrders().add(order);
		}
			
		else{
			boolean find = false;
			for(GuestOrder o : reservation.getOrders()){
				if(o.getGuest().getId().equals(g.getId())){
					o.getFoodstuffs().add(item);
					find = true;
				}
			}
			if(!find){
				GuestOrder order = new GuestOrder(g);
				order.getFoodstuffs().add(item);
				reservation.getOrders().add(order);
			}
		}
		return reservationRepository.save(reservation);
	}

	@Override
	public Reservation addDrink(Guest g, Long id, Drink item) {
		Reservation reservation = reservationRepository.findOne(id);
		if(reservation.getOrders() == null){
			reservation.setOrders(new ArrayList<GuestOrder>());
			GuestOrder order = new GuestOrder(g);
			order.getDrinks().add(item);
			reservation.getOrders().add(order);
		}
			
		else{
			boolean find = false;
			for(GuestOrder o : reservation.getOrders()){
				if(o.getGuest().getId().equals(g.getId())){
					o.getDrinks().add(item);
					find = true;
				}
			}
			if(!find){
				GuestOrder order = new GuestOrder(g);
				order.getDrinks().add(item);
				reservation.getOrders().add(order);
			}
		}
		return reservationRepository.save(reservation);
	}

	@Override
	public Reservation deleteFood(Guest g, Long id, Long itemId) {
		Reservation reservation = reservationRepository.findOne(id);
		int pos = 0;
		for(GuestOrder o : reservation.getOrders()){
			if(o.getGuest().getId().equals(g.getId()))
				for(int i = 0 ; i < o.getFoodstuffs().size();i++){
					if(o.getFoodstuffs().get(i).getId().equals(itemId)){
						pos = i;
						break;
					}
				}
		}
		for(GuestOrder o : reservation.getOrders()){
			if(o.getGuest().getId().equals(g.getId()))
				o.getFoodstuffs().remove(pos);
		}
		return reservationRepository.save(reservation);
	}

	@Override
	public Reservation deleteDrink(Guest g, Long id, Long itemId) {
		// TODO Auto-generated method stub
		Reservation reservation = reservationRepository.findOne(id);
	
		
		int pos = 0;
		for(GuestOrder o : reservation.getOrders()){
			if(o.getGuest().getId().equals(g.getId())){
				//if(o.getStatus()){
				for(int i = 0 ; i < o.getDrinks().size();i++){
					if(o.getDrinks().get(i).getId().equals(itemId)){
						pos = i;
						break;
					}
				}
			//}
			}
		}
		for(GuestOrder o : reservation.getOrders()){
			if(o.getGuest().getId().equals(g.getId())){
				o.getDrinks().remove(pos);
			}
		}
		return reservationRepository.save(reservation);
	}

	@Override
	public List<Reservation> getReservations(Guest g) {
		Calendar cal = Calendar.getInstance();
		ArrayList<Reservation> result = new ArrayList<Reservation>();
		ArrayList<Reservation> reservations = (ArrayList<Reservation>) reservationRepository.findAll();
		for(Reservation r : reservations){
			for(Guest guest : r.getGuests()){
				if(g.getId().equals(guest.getId()) && r.getDate().after(cal.getTime())){
					result.add(r);
					break;
				}
			}
		}
		return result;
	}

	@Override
	public List<Reservation> getHistory(Guest g) {
		Calendar cal = Calendar.getInstance();
		ArrayList<Reservation> result = new ArrayList<Reservation>();
		ArrayList<Reservation> reservations = (ArrayList<Reservation>) reservationRepository.findAll();
		for(Reservation r : reservations){
			for(Guest guest : r.getGuests()){
				if(g.getId().equals(guest.getId()) && r.getStay().before(cal.getTime())){
					result.add(r);
					break;
				}
			}
		}
		return result;		
	}
}
