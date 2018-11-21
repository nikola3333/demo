package com.isa.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.entity.Friends;
import com.isa.entity.Guest;
import com.isa.exceptions.UserNotFoundException;
import com.isa.repository.FriendsRepository;
import com.isa.repository.GuestRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendsServiceImplTest {

	@Autowired
	FriendsRepository friendsRepository;
	@Autowired
	GuestRepository guestRepository;	
	/*@Test
	public void testGetUsersFriends() {
		
	}*/

	@Test
	public void testSendRequest() {
		Guest requestSender = guestRepository.findOne(1L);
		Guest requestReciever = guestRepository.findOne(2L);
//		if(requestReciever == null) throw new UserNotFoundException(""+2L);
//		if(requestSender == null) throw new UserNotFoundException(""+1L);
//		if(friendsRepository.findFriendship(1L, 2L).size() >0 ) throw new DatabaseDuplicateException("Connection already exists");
		Friends f =friendsRepository.save(new Friends(requestSender, requestReciever, false));
		assertEquals("danilo@a.com",f.getRequestSender().getEmail());
		assertEquals("abc@d.com",f.getRequestResponder().getEmail());
	}

	@Test
	public void testConfirmFriendship() {
		Friends friends = sendRequest();
		
		Friends f = friendsRepository.findOne(friends.getId());
//		if(f == null) throw new ExpiredRequestException("Request cancelled");
		f.setConfirmedFriendship(true);		
		Friends saved = friendsRepository.save(f);
		assertEquals(true,saved.isConfirmedFriendship());
	}

	@Test
	public void testRemoveFromFriendsList() {
		add();
		Friends friends = friendsRepository.findFriendship(1L, 3L).get(0);
//		if(friends == null) throw new IllegalArgumentException("Friendship doesn't exist");
		friendsRepository.delete(friends);
		assertEquals(null, friendsRepository.findOne(friends.getId()));
	}

	@Test
	public void testGetFriendRequests() {
		List<Friends> friends = friendsRepository.findUserFriendRequests("danilo@a.com");
		assertEquals(1,friends.size());
	}

	@Test
	public void testDeclineFriendRequest() {
		Friends f = add();
		friendsRepository.delete(f.getId());
		assertEquals(null,friendsRepository.findOne(f.getId()));
	}

	@Test
	public void testSearchUsersFriends() {
		add();
		List<Friends> result = friendsRepository.findUsersFriends(1L, "laza%");
		assertEquals("laza@l.com",result.get(0).getRequestResponder().getEmail());
	}

	@Test
	public void testSearchFriendRequests() {
		sendRequest();
		List<Friends> result = friendsRepository.findUserFriendRequests(2L, "mika%");
		assertEquals(0,result.size());
	}

	
	public Friends sendRequest(){
		Guest requestSender = guestRepository.findOne(2L);
		Guest requestReciever = guestRepository.findOne(3L);
		if(requestReciever == null) throw new UserNotFoundException(""+3L);
		if(requestSender == null) throw new UserNotFoundException(""+2L);
		return friendsRepository.save(new Friends(requestSender, requestReciever, false));	
	}
	public Friends add(){
		Guest requestSender = guestRepository.findOne(1L);
		Guest requestReciever = guestRepository.findOne(3L);
		if(requestReciever == null) throw new UserNotFoundException(""+3L);
		if(requestSender == null) throw new UserNotFoundException(""+1L);
		return friendsRepository.save(new Friends(requestSender, requestReciever, true));	
	}
}
