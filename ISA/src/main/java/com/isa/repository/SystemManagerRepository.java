package com.isa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.isa.entity.SystemManager;

public interface SystemManagerRepository extends CrudRepository<SystemManager, Long>{

	
	@Query("select m from SystemManager m where m.email = :email")
	public SystemManager findByEmail(@Param("email")String email);
	

}
