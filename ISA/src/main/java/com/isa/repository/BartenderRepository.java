package com.isa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.isa.entity.Bartender;

public interface BartenderRepository extends CrudRepository<Bartender, Long>{

	@Query("select b from Bartender b where b.email = :email")
	public Bartender findByEmail(@Param("email") String email);
	

	
}
