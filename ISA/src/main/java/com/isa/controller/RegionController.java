package com.isa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.entity.Region;
import com.isa.service.RegionService;

@RestController
@RequestMapping("/regions")
public class RegionController {

	@Autowired
	private RegionService regionService;
	
	@RequestMapping(method = RequestMethod.GET)
	private List<Region> findAll(){
		return regionService.findAll();
	}
	
	@RequestMapping(method =RequestMethod.POST)
	private Region saveRegion(@RequestBody Region r){
		return regionService.save(r);
	}
	
}
