package com.example.vfgarage.controller.model;

import java.util.List;

public class GarageStatusResponseModel {
	public GarageStatusResponseModel() {
		super();
	}
	private List<GarageStatusModel> garageStatus;
	
	public List<GarageStatusModel> getGarageStatus() {
		return garageStatus;
	}
	public void setGarageStatus(List<GarageStatusModel> garageStatus) {
		this.garageStatus = garageStatus;
	}
}