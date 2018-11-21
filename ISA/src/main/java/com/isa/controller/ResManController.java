package com.isa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.entity.RestauranManager;
import com.isa.service.ManagerService;
import com.isa.service.MessageService;

@RestController
@RequestMapping("/manager")
public class ResManController {
	
	@Autowired
	private ManagerService managerService;
	@Autowired
	private MessageService messageService;
	@Autowired
	HttpSession session;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<RestauranManager> findAll(){
		return managerService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public RestauranManager update(@RequestBody RestauranManager rm){
		return managerService.save(rm);
	}
	@RequestMapping(method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
	public RestauranManager save(@RequestBody RestauranManager rm){
		RestauranManager r = managerService.save(rm);
		if(r != null) {
			messageService.sendMessage(rm);
		}
		return r;
	}
	@RequestMapping(value = "/confirmation/{code}", method = RequestMethod.GET)
	public void confirmRegistration(@PathVariable String code){
		String idString = code.substring(6, code.length());
		Long id = Long.parseLong(idString);
		managerService.confirmRegistration(id);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public RestauranManager findByEmail(){
		RestauranManager r = (RestauranManager)session.getAttribute("user");
		return managerService.findByEmail(r.getEmail());
	}
	//do the check
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public void logout(){
		session.invalidate();
	}
	
}
