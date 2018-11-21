package com.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.Bidder;
import com.isa.repository.BidderRepository;



@Service
@Transactional
public class BidderServiceImpl implements BidderService {
	
	@Autowired
	private  BidderRepository bidderRepository;
	
	

	public List<Bidder> findAll() {
		return (List<Bidder>) bidderRepository.findAll();
	}

	@Override
	public Bidder save(Bidder bidder) {
		return bidderRepository.save(bidder);
	}

	@Override
	public Bidder findOne(Long id) {
		return bidderRepository.findOne(id);
	}

	

	@Override
	public Bidder findByMail(String mail) {
		
		return bidderRepository.findByEmail(mail);
	}

	@Override
	public void delete(Bidder b) {
		// TODO Auto-generated method stub
		bidderRepository.delete(b.getId());
	}

	@Override
	public Bidder update(Bidder b) {
		Bidder bider=bidderRepository.findByEmail(b.getEmail());
		bider.setPassword(bider.getPassword());
		return bidderRepository.save(bider);
	}

	
}
