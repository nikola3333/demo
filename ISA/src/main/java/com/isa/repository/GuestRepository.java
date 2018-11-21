package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.isa.entity.Guest;

public interface GuestRepository extends CrudRepository<Guest, Long>{
	
	@Query("select g from Guest g where g.email = :email")
	public Guest findByEmail(@Param("email")String email);
	
	
	@Query("select g from Guest g where g.id not in(select f.requestResponder.id from Friends f where f.requestSender.id  = :id ) and"
			+ " g.id not in(select f.requestSender.id from Friends f where f.requestResponder.id  = :id)"
			+ "and g.confirmedRegistration = 1 order by g.firstName")
	public List<Guest> findNoneFriends(@Param("id")Long id);
	
	
	@Query("select g from Guest g where"//osobe koje nisu na listi prijatelja za pretragu 
			+ "(g.id not in (select f.requestResponder.id from Friends f where f.requestSender.id = :id) and"
			+ "  g.id not in(select f.requestSender.id from Friends f where f.requestResponder.id = :id)) and"
			+ "(g.firstName like :condition or g.lastName like :condition) and g.confirmedRegistration = 1 order by g.firstName")	
	public List<Guest> findNoneFriends(@Param("id")Long id,@Param("condition")String condition);

	@Query("select g from Guest g where"//osobe koje nisu na listi prijatelja za pretragu po imenu I prezimenu
			+ "(g.id not in (select f.requestResponder.id from Friends f where f.requestSender.id = :id) and"
			+ "  g.id not in(select f.requestSender.id from Friends f where f.requestResponder.id = :id)) and"
			+ "((g.firstName like :condition1 and g.lastName like :condition2) or"
			+ " (g.firstName like :condition2 and g.lastName like :condition1))"
			+ "and g.confirmedRegistration = 1 order by g.firstName")	
	public List<Guest> findNoneFriends(@Param("condition1")String condition1,@Param("condition2")String condition2,@Param("id")Long id);
}
