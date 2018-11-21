package com.isa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.entity.Bidder;
import com.isa.service.MessageService;
import com.isa.service.BidderService;

@RestController
@RequestMapping("/bidder")
public class BidderController {

	@Autowired
	private BidderService BidderService;
	@Autowired
	private MessageService messageService;
	@Autowired
	HttpSession session;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Bidder> findAll(){
		return BidderService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Bidder update(@RequestBody Bidder bd){
		return BidderService.save(bd);
	}
	@RequestMapping(method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
	public Bidder save(@RequestBody Bidder bd){
		Bidder b = BidderService.save(bd);
		if(b != null) {
			messageService.sendMessage(bd);
		}
		return b;
	}
	
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Bidder findByEmail(){
		Bidder bd = (Bidder)session.getAttribute("user");
		return BidderService.findByMail(bd.getEmail());
	}
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public void logout(){
		session.invalidate();
	}
}
