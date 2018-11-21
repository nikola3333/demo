package com.isa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.isa.entity.Waiter;

import com.isa.service.MessageService;
import com.isa.service.WaiterService;



	@RestController
	@RequestMapping("/waiter")
	public class WaiterController {
		
		@Autowired
		private WaiterService waiterService;
		@Autowired
		private MessageService messageService;
		@Autowired
		HttpSession session;
		
		@RequestMapping(method = RequestMethod.GET)
		public List<Waiter> findAll(){
			return waiterService.findAll();
		}
		
		@RequestMapping(method = RequestMethod.POST)
		public Waiter update(@RequestBody Waiter rm){
			return waiterService.save(rm);
		}
		@RequestMapping(method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
		public Waiter save(@RequestBody Waiter rm){
			Waiter r = waiterService.save(rm);
			if(r != null) {
				messageService.sendMessage(rm);
			}
			return r;
		}
		
		
		@RequestMapping(value = "/user", method = RequestMethod.GET)
		public Waiter findByEmail(){
			Waiter r = (Waiter)session.getAttribute("user");
			return waiterService.findByEmail(r.getEmail());
		}
}
