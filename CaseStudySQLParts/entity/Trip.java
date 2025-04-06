package entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class Trip {

	int tripID;
	Vehicle vehicle;
	Routes route;
	Driver driver;
	LocalDate departureDate;
	//LocalDate arrivalDate;
	String status;
	String tripType;
	int maxPassenger;
	Passenger passenger;
	int passengerCount;
	String cVehicle;
	public int routeId;
	/*Trip(Vehicle vehicle, Routes route, LocalDateTime departureDate, LocalDateTime arrivalDate,String status,String tripType,int maxPassenger){
		this.vehicle=vehicle;
		this.route=route;
		this.departureDate=departureDate;
		this.arrivalDate=arrivalDate;
		this.status="Available";
		this.tripType=tripType;
		this.maxPassenger=maxPassenger;
	}*/
	
	public Trip(String cVehicle, Routes route,LocalDate departureDate,  int passengerCount){
		this.cVehicle=cVehicle;
		this.route=route;
		this.departureDate=departureDate;
		this.status="Trip Booked";
		this.passengerCount=passengerCount;
		this.routeId=route.getRouteID();
	}
	public Routes getRoute() {
		return route;
	}
	public String getVehicle() {
		return cVehicle;
	}
	public int getRouteID() {
	    return routeId;
	}

	public int getPassengerCount() {
		return passengerCount;
	}
	
	
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver=driver;
	}
	public int getTripID() {
		return tripID;
	}
	
	public LocalDate getDepartureDate() {
		return departureDate;
	}
	
	public String getStatus() {
		return status;
	}
	public int maxPassenger() {
		return maxPassenger;
	}
	
	public void setVehicle(Vehicle vehicle) {
		this.vehicle=vehicle;
	}
	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate=departureDate;
	}
	
	public void setStatus(String status) {
		this.status=status;
	}
	public void setTripType(String tripType) {
		 this.tripType=tripType;
	}
	public void maxPassenger(int maxPassenger) {
		this.maxPassenger=maxPassenger;
	}
	
}
