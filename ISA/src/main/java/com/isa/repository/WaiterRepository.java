package com.isa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.isa.entity.Waiter;

public interface WaiterRepository extends CrudRepository<Waiter, Long>{
	
	@Query("select w from Waiter w where w.email = :email")
	public Waiter findByEmail(@Param("email")String email);
	

}
