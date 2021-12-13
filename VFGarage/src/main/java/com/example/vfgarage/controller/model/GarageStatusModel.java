package com.example.vfgarage.controller.model;

public class GarageStatusModel {
	public GarageStatusModel() {
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
