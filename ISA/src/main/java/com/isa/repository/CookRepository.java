package com.isa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.isa.entity.Cook;

public interface CookRepository extends CrudRepository<Cook, Long>{

	@Query("select c from Cook c where c.email = :email")
	public Cook findByEmail(@Param("email")String email);
	

}
