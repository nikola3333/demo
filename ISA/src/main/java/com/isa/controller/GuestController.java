package com.isa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.entity.Guest;
import com.isa.service.GuestService;
import com.isa.service.MessageService;

@RestController
@RequestMapping("/guests")
public class GuestController {

	@Autowired
	private GuestService guestService;
	@Autowired
	private MessageService messageService;
	@Autowired
	HttpSession session;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Guest> findAll(){
		return guestService.findAll();
	}
	
	@RequestMapping(value = "/none" ,method = RequestMethod.GET)
	public List<Guest> findNoneFriends(){
		Guest g = (Guest) session.getAttribute("user");
		return guestService.findNoneFriends(g.getId());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Guest update(@RequestBody Guest guest){
		return guestService.save(guest);
	}
	
	@RequestMapping(method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
	public Guest save(@RequestBody Guest guest){
		Guest g = guestService.save(guest);
		if(g != null) {
			messageService.sendMessage(guest);
		}
		return g;
	}
	
	@RequestMapping(method = RequestMethod.DELETE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteGuest(@RequestBody Guest guest){
		guestService.delete(guest);
	}
	
	@RequestMapping(value = "/confirmation/{code}", method = RequestMethod.GET)
	public void confirmRegistration(@PathVariable String code){
		String idString = code.substring(6, code.length());
		Long id = Long.parseLong(idString);
		guestService.confirmRegistration(id);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Guest findByEmail(){
		Guest g = (Guest)session.getAttribute("user");
		return guestService.findByEmail(g.getEmail());
	}
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public void logout(){
		session.invalidate();
	}
	
	@RequestMapping(value = "/none/{condition}",method = RequestMethod.GET)
	public List<Guest> searchNoneFriends(@PathVariable String condition){
		Guest g = (Guest) session.getAttribute("user");
		return guestService.findNoneFriends(g.getId(), condition);
	}
	

}
