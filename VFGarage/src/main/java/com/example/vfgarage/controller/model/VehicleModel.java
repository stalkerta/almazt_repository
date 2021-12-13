package com.example.vfgarage.controller.model;

public class VehicleModel {
	public VehicleModel() {
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
