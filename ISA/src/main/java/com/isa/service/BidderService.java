package com.isa.service;

import java.util.List;

import com.isa.entity.Bidder;


public interface BidderService {

	public List<Bidder> findAll();

	public Bidder save(Bidder bidder);
	public Bidder findOne(Long id);
	public Bidder findByMail(String mail);
	public void delete(Bidder b);
	public Bidder update(Bidder b);
}
