package com.isa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.entity.Cook;
import com.isa.service.MessageService;
import com.isa.service.CookService;

@RestController
@RequestMapping("/cook")
public class CookController {
	
	@Autowired
	private CookService cookService;
	@Autowired
	private MessageService messageService;
	@Autowired
	HttpSession session;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Cook> findAll(){
		return cookService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Cook update(@RequestBody Cook rm){
		return cookService.save(rm);
	}
	@RequestMapping(method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
	public Cook save(@RequestBody Cook rm){
		Cook r = cookService.save(rm);
		if(r != null) {
			messageService.sendMessage(rm);
		}
		return r;
	}
	
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Cook findByEmail(){
		Cook r = (Cook)session.getAttribute("user");
		return cookService.findByEmail(r.getEmail());
	}

	
}
