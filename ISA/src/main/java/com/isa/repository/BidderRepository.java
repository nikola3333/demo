package com.isa.repository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;

import com.isa.entity.Bidder;


public interface BidderRepository extends CrudRepository<Bidder,Long> {

	
	@Query("select bd from Bidder bd where bd.email = :email")
	public Bidder findByEmail(@Param("email")String email);
}
