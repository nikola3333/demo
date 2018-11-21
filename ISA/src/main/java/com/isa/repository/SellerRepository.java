package com.isa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.isa.entity.Seller;

public interface SellerRepository extends CrudRepository<Seller, Long>{

	@Query("select s from Seller s where s.email = :email")
	public Seller findByEmail(@Param("email")String email);
	
}
