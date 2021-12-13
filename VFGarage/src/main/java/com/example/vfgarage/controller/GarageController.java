package com.example.vfgarage.controller;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.vfgarage.controller.model.GarageStatusResponseModel;
import com.example.vfgarage.controller.model.VehicleModel;
import com.example.vfgarage.service.GarageService;
import com.example.vfgarage.service.dto.VehicleDto;
import org.dozer.DozerBeanMapper;

@Controller
@RequestMapping(path = "/garage")
public class GarageController { 
	
	@Autowired 
	private GarageService garageService; 

	@GetMapping(path = "/welcome", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String welcome(@RequestParam String name) {
		return garageService.welcomeMessage(name);
	}

	@GetMapping(path = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public GarageStatusResponseModel garageStatus() {
		return new DozerBeanMapper().map(garageService.garageStatus(),GarageStatusResponseModel.class);
	}
	
	@PostMapping(value = "/park", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String parkVehicle(@RequestBody VehicleModel vehicleModel) {
		return garageService.park(new DozerBeanMapper().map(vehicleModel, VehicleDto.class));
	}

	@DeleteMapping(value = "/leave",produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String leave(@RequestParam Integer ticketNumber) {
		return garageService.leaveFromGarage(ticketNumber);
	}
}
