package com.example.vfgarage.service.dto;

public class VehicleDto {
	public VehicleDto() {
		super();
	}
	private String plate;
	private String colour;
	private String vehicleType;
	
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
}
