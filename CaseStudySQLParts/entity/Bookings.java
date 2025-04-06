package entity;

import java.time.LocalDateTime;

public class Bookings {

	int bookingID;
	Trip trip;
	Passenger passenger;
	LocalDateTime bookingDate;
	LocalDateTime bookingStatus;
	
	Bookings(Trip trip, Passenger passenger, LocalDateTime bookingDate,LocalDateTime bookingStatus){
		this.trip=trip;
		this.passenger=passenger;
		this.bookingDate=bookingDate;
		this.bookingStatus=bookingStatus;
	}
	
	public int getBookingID() {
		return bookingID;
	}
	public Trip getTrip() {
		return trip;
	}
	public Passenger getPassenger() {
		return passenger;
	}
	public LocalDateTime bookingDate() {
		return bookingDate;
	}
    public LocalDateTime getBookingStatus() {
    	return bookingStatus;
    }
    
    public void setBookingID(int bookingID) {
    	this.bookingID=bookingID;
    }
    public void setBookingDate(LocalDateTime bookingDate) {
    	this.bookingDate=bookingDate;
    }
    public void setBookingStatus(LocalDateTime bookingStatus) {
    	this.bookingStatus=bookingStatus;
    }
	
}
