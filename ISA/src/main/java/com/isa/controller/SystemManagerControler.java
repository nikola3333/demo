package com.isa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.service.SysManagerService;
import com.isa.entity.SystemManager;
import com.isa.service.MessageService;

@RestController
@RequestMapping("/sysmanager")
public class SystemManagerControler {

	@Autowired
	private SysManagerService smService;
	@Autowired
	private MessageService messageService;
	@Autowired
	HttpSession session;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<SystemManager> findAll(){
		return smService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public SystemManager update(@RequestBody SystemManager bd){
		return smService.save(bd);
	}
	@RequestMapping(method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
	public SystemManager save(@RequestBody SystemManager bd){
		SystemManager b = smService.save(bd);
		if(b != null) {
			messageService.sendMessage(bd);
		}
		return b;
	}
	
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public SystemManager findByEmail(){
		SystemManager bd = (SystemManager)session.getAttribute("user");
		return smService.findByEmail(bd.getEmail());
	}
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public void logout(){
		session.invalidate();
	}
}
