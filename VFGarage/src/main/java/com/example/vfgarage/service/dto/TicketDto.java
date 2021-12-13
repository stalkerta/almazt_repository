package com.example.vfgarage.service.dto;

public class TicketDto extends GarageStatusDto{
	public TicketDto() {
		super();
	}
	public TicketDto(int ticketNumber, String plate, String colour, int[] slots) {
		super(plate, colour, slots);
		this.ticketNumber = ticketNumber;		
	}
	private int ticketNumber;
	
	public int getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
}
