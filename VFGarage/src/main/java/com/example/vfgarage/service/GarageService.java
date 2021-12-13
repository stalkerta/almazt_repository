package com.example.vfgarage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.vfgarage.repository.CarRepository;
import com.example.vfgarage.service.dto.GarageStatusDto;
import com.example.vfgarage.service.dto.GarageStatusResponseDto;
import com.example.vfgarage.service.dto.TicketDto;
import com.example.vfgarage.service.dto.VehicleDto;

@Service
public class GarageService {	
	public String welcomeMessage(String name) {
		return "Welcome to VF Garage of " + name;
	}
	
	public String park(VehicleDto vehicleDto) {
			int allocationCount = -1;
			switch(vehicleDto.getVehicleType()) {
			case "Car":
				for(int i = 0; i<CarRepository.getSlot(); i++) {
					try {
						if(CarRepository.getGarageMap().get(i)==null) {
							CarRepository.getGarageMap().put(i, vehicleDto);
							int[] slots = new int[]{i};
	                        TicketDto newTicket = new TicketDto(i,vehicleDto.getPlate(),vehicleDto.getColour(),slots);
	                        if(CarRepository.getTicketMap().get(i)==null) {
	                        	CarRepository.getTicketMap().put(i, newTicket);
	                        	allocationCount = slots.length;
	                        }
							break;
						}
					}catch(Exception e) {
						System.out.println("\nGarage is full");
						break;
					}
				}
				break;
			case "Jeep":
				for(int i = 0; i<CarRepository.getSlot(); i++) {
					try {
						if(CarRepository.getGarageMap().get(i)==null && (i+1<CarRepository.getSlot() && CarRepository.getGarageMap().get(i+1)==null)) {
							CarRepository.getGarageMap().put(i, vehicleDto);
							CarRepository.getGarageMap().put(i+1, vehicleDto);
							int[] slots = new int[]{i,i+1};
	                        TicketDto newTicket = new TicketDto(i,vehicleDto.getPlate(),vehicleDto.getColour(),slots);
	                        if(CarRepository.getTicketMap().get(i)==null) {
	                        	CarRepository.getTicketMap().put(i, newTicket);
	                        	allocationCount = slots.length;
	                        }
							break;
						}
					}catch(Exception e) {
						System.out.println("\nGarage is full");
					}
				}
				break;
			case "Truck":
				for(int i = 0; i<CarRepository.getSlot(); i++) {
					try {
						if(CarRepository.getGarageMap().get(i)==null && (i+1<CarRepository.getSlot() && CarRepository.getGarageMap().get(i+1)==null && i+2<CarRepository.getSlot()&& CarRepository.getGarageMap().get(i+2)==null && i+3<CarRepository.getSlot() && CarRepository.getGarageMap().get(i+3)==null )) {
							CarRepository.getGarageMap().put(i, vehicleDto);
							CarRepository.getGarageMap().put(i+1, vehicleDto);
							CarRepository.getGarageMap().put(i+2, vehicleDto);
							CarRepository.getGarageMap().put(i+3, vehicleDto);
							int[] slots = new int[]{i,i+1,i+2,i+3};
	                        TicketDto newTicket = new TicketDto(i,vehicleDto.getPlate(),vehicleDto.getColour(),slots);
	                        if(CarRepository.getTicketMap().get(i)==null) {
	                        	CarRepository.getTicketMap().put(i, newTicket);
	                        	allocationCount = slots.length;
	                        }
							break;
						}
					}catch(Exception e) {
						System.out.println("Error processing park garage request: " + vehicleDto.getPlate() );
					}
				}
				break;
			default:
				break;
			}

			System.out.println("\n(Service Side) Parking car plate: " + vehicleDto.getPlate() + "\nParking car colour: "
					+ vehicleDto.getColour() + "\nParking vehicle type: " + vehicleDto.getVehicleType());
		
			return allocateMessage(allocationCount);
	}

	public GarageStatusResponseDto garageStatus() {
		GarageStatusResponseDto garageStatusResponseDto = new GarageStatusResponseDto();
		List<GarageStatusDto> garageStatusDtoList = new ArrayList<>();
				
		for (Map.Entry<Integer, TicketDto> entry : CarRepository.getTicketMap().entrySet()) {
			GarageStatusDto garageStatusDto = new GarageStatusDto(entry.getValue().getPlate(),
					entry.getValue().getColour(), entry.getValue().getSlots());
			garageStatusDtoList.add(garageStatusDto);
			garageStatusResponseDto.setGarageStatus(garageStatusDtoList);
		}
		return garageStatusResponseDto;
	}
	
	public String leaveFromGarage(Integer ticketNumber) {
			System.out.println("(Service Side) Deleting car with ticket number: " + ticketNumber);
			int[] slots = CarRepository.getTicketMap().get(ticketNumber).getSlots();
			int slotLength = slots.length;
			
			switch(slotLength) {
			case 1:
				CarRepository.getGarageMap().remove(slots[0]);
				CarRepository.getTicketMap().remove(slots[0]);
				break;
			case 2:
				for(int slotNumber=ticketNumber;slotNumber<slotLength;slotNumber++) {
					CarRepository.getGarageMap().remove(slots[slotNumber]);
				}
				CarRepository.getTicketMap().remove(slots[0]);
				break;
			case 4:
				for(int slotNumber=ticketNumber;slotNumber<slotLength;slotNumber++) {
					CarRepository.getGarageMap().remove(slots[slotNumber]);
				}
				CarRepository.getTicketMap().remove(slots[0]);
			default:
				break;
			}
			return allocateMessage(slotLength);
	}			
	
	
	public String allocateMessage(int slotCount) {
		String result = null;
		
		if(slotCount == -1) {
			result = "Garage is full!!";
		}
		else {
			result = "Allocated " + slotCount + " slot";
		}
		
		return result;
	}
}


