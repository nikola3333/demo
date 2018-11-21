package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.isa.entity.Friends;

public interface FriendsRepository extends CrudRepository<Friends, Long>{
	
	@Query("select f from Friends f where (f.requestSender.email  = :email or f.requestResponder.email =  :email)"
			+ "and f.confirmedFriendship = 1 ")
	public List<Friends> findUsersFriends(@Param("email")String email);	

	@Query("select f from Friends f where (f.requestSender.id =  :senderId and f.requestResponder.id = :receiverId) or (f.requestSender.id = :receiverId and f.requestResponder.id  = :senderId)")	
	public List<Friends> findFriendship(@Param("senderId")Long senderId,@Param("receiverId")Long recieverId);

	@Query("select f from Friends f where (f.requestSender.email  = :email or f.requestResponder.email  = :email)"
			+ "and f.confirmedFriendship = 0")
	public List<Friends> findUserFriendRequests(@Param("email")String email);
	
	@Query("select f from Friends f where (f.requestSender.id = :id or f.requestResponder.id = :id)"
			+ "and (f.requestSender.firstName like :condition or f.requestResponder.firstName like :condition or f.requestSender.lastName like :condition or f.requestResponder.lastName like :condition)"
			+ "and f.confirmedFriendship = 1")
	public List<Friends> findUsersFriends(@Param("id")Long id,@Param("condition") String condition);

	@Query("select f from Friends f where (f.requestSender.id = :id or f.requestResponder.id = :id)"
			+ "and (((f.requestSender.firstName like :condition1 and  f.requestSender.lastName like :condition2)  or"
			+ " (f.requestResponder.firstName like :condition1 and f.requestResponder.lastName like :condition2))or"
			+ " ((f.requestSender.firstName like :condition2 and f.requestSender.lastName like :condition1) or"
			+ " (f.requestResponder.firstName like :condition2 and f.requestResponder.lastName like :condition1)))"
			+ "and f.confirmedFriendship = 1")
	public List<Friends> findUsersFriends(@Param("condition1") String condition1,@Param("condition2") String condition2,@Param("id")Long id);
	
	
	@Query("select f from Friends f where ("
			+ "(f.requestSender.id = :id and (f.requestResponder.firstName like :condition or f.requestResponder.lastName like :condition))"
			+ " or (f.requestResponder.id = :id and (f.requestSender.firstName like :condition or f.requestSender.lastName like :condition))"
			+ "and f.confirmedFriendship = 0)")
	public List<Friends> findUserFriendRequests(@Param("id")Long id,@Param("condition")String condition);
	
	@Query("select f from Friends f where ("
			+ "(f.requestSender.id = :id and ((f.requestResponder.firstName like :condition1 and f.requestResponder.lastName like :condition2) or (f.requestResponder.firstName like :condition2 and f.requestResponder.lastName like :condition1)))"
			+ " or (f.requestResponder.id = :id and ((f.requestSender.firstName like :condition1 and f.requestSender.lastName like :condition2) or (f.requestSender.firstName like :condition2 and f.requestSender.lastName like :condition1)))"
			+ "and f.confirmedFriendship = 0)")
	public List<Friends>findUserFriendRequests(@Param("condition1") String condition1,@Param("condition2") String condition2,@Param("id")Long id);
	
	
}



