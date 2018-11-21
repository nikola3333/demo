package com.isa.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.Friends;
import com.isa.entity.Guest;
import com.isa.exceptions.DatabaseDuplicateException;
import com.isa.exceptions.ExpiredRequestException;
import com.isa.exceptions.UserNotFoundException;
import com.isa.repository.FriendsRepository;
import com.isa.repository.GuestRepository;

@Service
@Transactional
public class FriendsServiceImpl implements FriendsService{

	@Autowired
	FriendsRepository friendsRepository;
	@Autowired
	GuestRepository guestRepository;
	
	@Override
	public List<Guest> getUsersFriends(String userEmail) {
		ArrayList<Friends> f = new ArrayList<>( friendsRepository.findUsersFriends(userEmail));
		ArrayList<Guest> friends = new ArrayList<>();
		for(Friends ff : f){
			if(ff.getRequestResponder().getEmail().equals(userEmail)){
				friends.add(ff.getRequestSender());
			}
			else{
				friends.add(ff.getRequestResponder());
			}
		}
		return sortGuest(friends);
	}

	@Override
	public Friends sendRequest(Long requestSenderId, Long requestRecieverId) {
		
		Guest requestSender = guestRepository.findOne(requestSenderId);
		Guest requestReciever = guestRepository.findOne(requestRecieverId);
		
		if(requestReciever == null) throw new UserNotFoundException(""+requestRecieverId);
		if(requestSender == null) throw new UserNotFoundException(""+requestSenderId);
		if(friendsRepository.findFriendship(requestSenderId, requestRecieverId).size() >0 ) throw new DatabaseDuplicateException("Connection already exists");
		return friendsRepository.save(new Friends(requestSender, requestReciever, false));
	}

	@Override
	public Friends confirmFriendship(Long friendsId) {
		// TODO Auto-generated method stub
		Friends f = friendsRepository.findOne(friendsId);
		if(f == null) throw new ExpiredRequestException("Request cancelled");
		f.setConfirmedFriendship(true);
		return friendsRepository.save(f);
	}

	@Override
	public void removeFromFriendsList(Long id,Long friendId) {
		Friends friends = friendsRepository.findFriendship(id, friendId).get(0);
		if(friends == null) throw new IllegalArgumentException("Friendship doesn't exist");
		friendsRepository.delete(friends);
	}

	@Override
	public List<Friends> getFriendRequests(String email,Long id) {
		// TODO Auto-generated method stub
		return sortFriendRequests((ArrayList<Friends>) friendsRepository.findUserFriendRequests(email),id);
	}

	@Override
	public void declineFriendRequest(Long id) {
		if(friendsRepository.findOne(id) == null) throw new IllegalArgumentException("Friendship doesn't exist");
		friendsRepository.delete(id);
	}

	@Override
	public List<Guest> searchUsersFriends(Long id, String condition) {
		ArrayList<Friends> f =null;
		String splitted [];
		splitted = condition.split(" ");
		if(splitted.length == 2){
			 f = new ArrayList<>( friendsRepository.findUsersFriends(splitted[0]+ "%",splitted[1]+"%",id));
		}else{
			 f = new ArrayList<>( friendsRepository.findUsersFriends(id,condition+"%"));
		}
		ArrayList<Guest> friends = new ArrayList<>();
		for(Friends ff : f){
			if(ff.getRequestResponder().getId().equals(id)){
				friends.add(ff.getRequestSender());
			}
			else{
				friends.add(ff.getRequestResponder());
			}
		}
		return sortGuest(friends);
	}

	@Override
	public List<Friends> searchFriendRequests(Long id, String condition) {

		String splitted [] =condition.split(" ");
		
		if(splitted.length == 2)
			return sortFriendRequests((ArrayList<Friends>) friendsRepository.findUserFriendRequests(splitted[0]+ "%",splitted[1]+"%",id),id);
		else
			return  sortFriendRequests((ArrayList<Friends>) friendsRepository.findUserFriendRequests(id, condition+"%"),id);
		}
	
	public List<Guest> sortGuest(ArrayList<Guest> guests){
		for(int i = 0 ; i < guests.size(); i ++){
			for(int j = i+1 ; j< guests.size();j++){
				 if(guests.get(i).getFirstName().compareToIgnoreCase(guests.get(j).getFirstName()) > 0){
					 Collections.swap(guests, i, j);
				 }
			}
		}
		return guests;
	}

	public List<Friends> sortFriendRequests(ArrayList<Friends> friends,Long id){

		for(int i = 0 ; i < friends.size(); i++){

			for(int j = i+1 ; j < friends.size();j++){
				Guest friend1 = null;
				Guest friend2 = null;
				if(friends.get(i).getRequestSender().getId() != id){
					friend1 = friends.get(i).getRequestSender();
				}
				else{
					friend1 = friends.get(i).getRequestResponder();
				}
				if(friends.get(j).getRequestSender().getId() != id){
					friend2 = friends.get(j).getRequestSender();
				}
				else{
					friend2 = friends.get(j).getRequestResponder();
				}
				if(friend1.getFirstName().compareToIgnoreCase(friend2.getFirstName()) > 0){
					Collections.swap(friends, i, j);
				}
			}
		}
		return friends;
	}
}
