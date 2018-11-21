package com.isa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.isa.entity.RestauranManager;

public interface RestaurantManagerRepository extends CrudRepository<RestauranManager, Long>{

	
	@Query("select m from RestauranManager m where m.email = :email")
	public RestauranManager findByEmail(@Param("email")String email);
	

}
