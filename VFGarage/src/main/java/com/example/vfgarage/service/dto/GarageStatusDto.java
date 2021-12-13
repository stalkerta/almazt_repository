package com.example.vfgarage.service.dto;

public class GarageStatusDto {
	public GarageStatusDto(String plate, String colour, int[] slots) {
		super();
		this.plate = plate;
		this.colour = colour;
		this.slots = slots;
	}
	public GarageStatusDto() {
		super();
	}
	private String plate;
	private String colour;
	private int[] slots;
	
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
	public int[] getSlots() {
		return slots;
	}
	public void setSlots(int[] slots) {
		this.slots = slots;
	}
}
