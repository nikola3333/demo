package com.isa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.entity.Friends;
import com.isa.entity.Guest;
import com.isa.service.FriendsService;

@RestController
@RequestMapping("/friends")
public class FriendsController {

	@Autowired
	private FriendsService friendsService;
	@Autowired
	private HttpSession session;
	
/*	@RequestMapping(method = RequestMethod.POST)
	public List<Friends> findUsersFriends(@RequestBody String userEmail){
		return friendsService.getUsersFriends(userEmail);
	}*/

	@RequestMapping(method = RequestMethod.GET)
	public List<Guest> findUsersFriends(){
		Guest g = (Guest) session.getAttribute("user");
		return friendsService.getUsersFriends(g.getEmail());
	}
	
	
	@RequestMapping(value = "/sender/{requestSenderId}/reciever/{requestRecieverId}",method = RequestMethod.PUT)
	public Friends sendRequest(@PathVariable Long requestSenderId,@PathVariable Long requestRecieverId){
		return friendsService.sendRequest(requestSenderId, requestRecieverId);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Friends confirmFriendship(@RequestBody Long id){
		return friendsService.confirmFriendship(id);
	}
	
	@RequestMapping(value = "/friend/{id}",method = RequestMethod.DELETE)
	public void removeFromFriendsList(@PathVariable Long id){
		Guest g = (Guest) session.getAttribute("user");
		friendsService.removeFromFriendsList(g.getId(), id);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public void removeFriendRequest(@PathVariable Long id){
		friendsService.declineFriendRequest(id);
	}
	
	@RequestMapping(value = "/requests", method = RequestMethod.GET)
	public List<Friends> getFriendRequests(){
		Guest g = (Guest) session.getAttribute("user");
		return friendsService.getFriendRequests(g.getEmail(),g.getId());
	}
	
	@RequestMapping(value = "/{condition}",method = RequestMethod.GET)
	public List<Guest> searchUsersFriends(@PathVariable String condition){
		Guest g = (Guest) session.getAttribute("user");
		return friendsService.searchUsersFriends(g.getId(), condition);
	}
	
	@RequestMapping(value = "/requests/{condition}", method = RequestMethod.GET)
	public List<Friends> searchUsersFriendsReqeusts(@PathVariable String condition){
		Guest g = (Guest) session.getAttribute("user");
		return friendsService.searchFriendRequests(g.getId(), condition);
	}
	
}
