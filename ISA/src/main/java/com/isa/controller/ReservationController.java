package com.isa.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.entity.Drink;
import com.isa.entity.Foodstuffs;
import com.isa.entity.Guest;
import com.isa.entity.Region;
import com.isa.entity.Reservation;
import com.isa.entity.Restaurant;
import com.isa.entity.Table;
import com.isa.entity.User;
import com.isa.repository.GuestRepository;
import com.isa.service.MessageService;
import com.isa.service.ReservationService;
import com.isa.service.RestaurantService;
import com.isa.service.TableService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	@Autowired
	private TableService tableService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	GuestRepository guestRepository;
	@Autowired
	HttpSession session;
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value = "/all/" , method = RequestMethod.GET)
	public Map<Long,Boolean> checkIfReserved(){
		HashMap<Long, Boolean> statuses = new HashMap<>();
		Date reservationTime = (Date)session.getAttribute("reservationTime");
		Date stayTime = (Date) session.getAttribute("stayTime");
		Long restaurantId = (Long) session.getAttribute("restaurant");
		Restaurant restaurant = restaurantService.findOne(restaurantId);
		for(Region region: restaurant.getRegions()){
			Boolean status = false;
			for(Table t: region.getTables()){
				status = tableService.checkIfReserved(t.getId(), reservationTime, stayTime);
				statuses.put(t.getId(), status);
			}
		}
		return statuses;
	}
	
	@RequestMapping(value = "/date",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public Date sendDate(@RequestBody ArrayList<Date> dates){
		Calendar cal = Calendar.getInstance();
		cal.setTime(dates.get(0));
		Calendar timeC = Calendar.getInstance();
		timeC.setTime(dates.get(1));
		timeC.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
		session.setAttribute("reservationTime", dates.get(0));
		Date dateStay = timeC.getTime();
		session.setAttribute("stayTime", dateStay);
		return timeC.getTime();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Reservation confirmReservation(@RequestBody ArrayList<Table> tables){
		Long restaurantId = (Long) session.getAttribute("restaurant");
		Restaurant restaurant = restaurantService.findOne(restaurantId);
		Guest g = (Guest) session.getAttribute("user");
		Date reservationTime = (Date) session.getAttribute("reservationTime");
		Date stayTime = (Date) session.getAttribute("stayTime");
		
		Integer i = 0;
		for(Table t : tables){
			i += t.getSeatNum();
		}
		
		ArrayList<Table> ts = tableService.checkVersion(tables,reservationTime,stayTime);
		
		Reservation r = new Reservation(restaurant, reservationTime, stayTime,i);
		Reservation rr = reservationService.save(r,g);
		
		if(ts.size() > 0){
			for(Table t : ts){
				t.getReservations().add(rr);
				tableService.save(t);
			}
			return rr;
		}
		return null;
	}
	
	@RequestMapping(value = "/friend/{reservationId}",method = RequestMethod.POST)
	public Reservation inviteFriend(@RequestBody Guest g,@PathVariable Long reservationId){
		User user = (User) session.getAttribute("user");
		Reservation r =reservationService.addToInvited(reservationId,g);
		messageService.sendInvitation(user, g, reservationId);
		return r;
	}
	
	@RequestMapping(value = "/confirmation/{reservationCode}/{invitedFriendCode}",method = RequestMethod.GET)
	public Reservation findOne(@PathVariable String reservationCode,@PathVariable String invitedFriendCode){
		String idString = reservationCode.substring(6, reservationCode.length());
		Long reservationId = Long.parseLong(idString);
		String idString1 = invitedFriendCode.substring(6, invitedFriendCode.length());
		Long friendId = Long.parseLong(idString1);
		session.setAttribute("invitedFriend", guestRepository.findOne(friendId));
		
		return reservationService.findOne(reservationId);
	}
	
	@RequestMapping(value = "/invitation/{reservationId}",method = RequestMethod.POST)
	public Reservation acceptInvitation(@PathVariable Long reservationId){
		Guest g = (Guest)session.getAttribute("invitedFriend");
		return reservationService.acceptInvitation(reservationId, g);
	}
	
	
	@RequestMapping(value = "/invitation/{reservationId}",method = RequestMethod.DELETE)
	public Reservation declineInvitation(@PathVariable Long reservationId){
		Guest g = (Guest)session.getAttribute("invitedFriend");
		return reservationService.declineInvitation(reservationId, g);
	}
	
	@RequestMapping(value = "/restaurant/{reservationId}", method = RequestMethod.GET)
	 public Restaurant getRestaurantOfReservation(@PathVariable Long reservationId){
		 Reservation reservation = reservationService.findOne(reservationId);
		 Restaurant r = reservation.getRestaurant();
		 return r;
	 }
	
	@RequestMapping(value = "/{id}",method = RequestMethod.POST)
	public void setReservation(@PathVariable Long id ){
		session.setAttribute("reservationId", id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Reservation getReservation(){
		Long id = (Long)session.getAttribute("reservationId");
		Reservation r = null;
		if(id != null)
			r= reservationService.findOne(id);
		return r;
	}
	
	@RequestMapping(value = "/orders/food",method = RequestMethod.POST)
	public Reservation addFood(@RequestBody Foodstuffs item){
		Guest g = (Guest)session.getAttribute("user");
		if(g == null)
			g = (Guest) session.getAttribute("invitedFriend");
		Long id = (Long) session.getAttribute("reservationId");
		
		return reservationService.addFood(g,id,item);
	}

	@RequestMapping(value = "/orders/drink",method = RequestMethod.POST)
	public Reservation addDrink(@RequestBody Drink item){
		Guest g = (Guest)session.getAttribute("user");
		if(g == null)
			g = (Guest) session.getAttribute("invitedFriend");
		Long id = (Long) session.getAttribute("reservationId");
		
		return reservationService.addDrink(g,id,item);
	}	
	
	@RequestMapping(value = "/orders/guest", method = RequestMethod.GET)
	public Guest getGuest(){
		Guest g = (Guest) session.getAttribute("user");
		if(g == null)
			g = (Guest) session.getAttribute("invitedFriend");
		
		return g;
	}
	
	
	
	@RequestMapping(value = "/orders/food/{itemId}",method = RequestMethod.DELETE)
	public Reservation deleteFood(@PathVariable Long itemId){
		Guest g = (Guest)session.getAttribute("user");
		if(g == null)
			g = (Guest) session.getAttribute("invitedFriend");
		Long id = (Long) session.getAttribute("reservationId");
		
		return reservationService.deleteFood(g,id,itemId);
	}

	@RequestMapping(value = "/orders/drink/{itemId}",method = RequestMethod.DELETE)
	public Reservation deleteDrink(@PathVariable Long itemId){
		Guest g = (Guest)session.getAttribute("user");
		if(g == null)
			g = (Guest) session.getAttribute("invitedFriend");
		Long id = (Long) session.getAttribute("reservationId");
		
		return reservationService.deleteDrink(g,id,itemId);
	}
	
	@RequestMapping(value = "/guest",method = RequestMethod.GET)
	public List<Reservation> getReservations(){
		Guest g = (Guest) session.getAttribute("user");
		return reservationService.getReservations(g);
	}
	
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public List<Reservation> getReservationsHistory(){
		Guest g = (Guest)session.getAttribute("user");
		return reservationService.getHistory(g);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void cancelReservation(@PathVariable Long id){
		ArrayList<Table> tables = (ArrayList<Table>) tableService.findAll();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)+31);
		Reservation r = reservationService.findOne(id);
		if(cal.getTime().after(r.getDate())) throw new IllegalAccessError("You can't cancel reservation, less than 30 minutes to reservation");
		Reservation temp = null;
		for(Table t : tables){
			for(Reservation res : t.getReservations()){
				if(res.getId().equals(id)){
					temp = res;
				}
			}
			if(temp != null){
				t.getReservations().remove(temp);
			}
		}
		reservationService.delete(id);
	}
	
	
	
}
