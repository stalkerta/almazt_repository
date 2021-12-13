package com.example.vfgarage.service.dto;

import java.util.List;

public class GarageStatusResponseDto {
	public GarageStatusResponseDto() {
		super();
	}
	private List<GarageStatusDto> garageStatus;
	public List<GarageStatusDto> getGarageStatus() {
		return garageStatus;
	}
	public void setGarageStatus(List<GarageStatusDto> garageStatus) {
		this.garageStatus = garageStatus;
	}
}
