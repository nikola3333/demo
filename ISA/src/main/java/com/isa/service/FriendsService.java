package com.isa.service;

import java.util.List;

import com.isa.entity.Friends;
import com.isa.entity.Guest;

public interface FriendsService {

	public List<Guest> getUsersFriends(String userEmail);
	public Friends sendRequest(Long requestSenderId,Long requestRecieverId);
	public Friends confirmFriendship(Long friendsId);
	public void removeFromFriendsList(Long id,Long friendId);
	public void declineFriendRequest(Long id);
	
	public List<Friends> getFriendRequests(String email,Long id);
	public List<Guest> searchUsersFriends(Long id,String condition);
	public List<Friends> searchFriendRequests(Long id,String condition);
}
