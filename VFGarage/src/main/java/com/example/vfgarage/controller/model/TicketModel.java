package com.example.vfgarage.controller.model;

public class TicketModel extends GarageStatusModel{
	public TicketModel() {
		super();
	}
	private int ticketNumber;
	
	public int getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
}
