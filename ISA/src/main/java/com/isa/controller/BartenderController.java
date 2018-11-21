package com.isa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.entity.Bartender;
import com.isa.service.BartenderService;
       
import com.isa.service.MessageService;

	@RestController
	@RequestMapping("/bartender")
	public class BartenderController {
		
		@Autowired
		private BartenderService bartenderService;
		@Autowired
		private MessageService messageService;
		@Autowired
		HttpSession session;
		
		
		@RequestMapping(method = RequestMethod.GET)
		public List<Bartender> findAll(){
			return bartenderService.findAll();
		}
		
		@RequestMapping(method = RequestMethod.POST)
		public Bartender update(@RequestBody Bartender rm){
			return bartenderService.save(rm);
		}
		@RequestMapping(method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
		public Bartender save(@RequestBody Bartender rm){
			Bartender r = bartenderService.save(rm);
			if(r != null) {
				messageService.sendMessage(rm);
			}
			return r;
		}
		
		
		@RequestMapping(value = "/user", method = RequestMethod.GET)
		public Bartender findByEmail(){
			Bartender r = (Bartender)session.getAttribute("user");
			return bartenderService.findByEmail(r.getEmail());
		}
}
