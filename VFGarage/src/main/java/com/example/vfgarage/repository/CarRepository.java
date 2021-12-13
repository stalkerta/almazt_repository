package com.example.vfgarage.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.vfgarage.service.dto.TicketDto;
import com.example.vfgarage.service.dto.VehicleDto;

@Repository
public class CarRepository {
	private static Map<Integer, VehicleDto> garageMap = new HashMap<Integer, VehicleDto>();
	private static Map<Integer, TicketDto> ticketMap = new HashMap<Integer, TicketDto>();
	public static final int slot = 10; 
	
	public static Map<Integer, VehicleDto> getGarageMap() {
		return garageMap;
	}

	public static void setGarageMap(Map<Integer, VehicleDto> garageMap) {
		CarRepository.garageMap = garageMap;
	}

	public static Map<Integer, TicketDto> getTicketMap() {
		return ticketMap;
	}

	public static void setTicketMap(Map<Integer, TicketDto> ticketMap) {
		CarRepository.ticketMap = ticketMap;
	}

	public static int getSlot() {
		return slot;
	}
}
