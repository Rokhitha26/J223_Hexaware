package dao;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import entity.Bookings;
import entity.Driver;
import entity.Routes;
import entity.Trip;
import entity.Vehicle;

public interface TransportService {

	void showVehicle() throws FileNotFoundException, SQLException, IOException;
	boolean addVehicle(Vehicle vehicle) throws FileNotFoundException, IOException;
	boolean updateVehicle(int vehicleID) throws FileNotFoundException, IOException;
	boolean deleteVehicle(int vehicleID) throws FileNotFoundException, IOException;
	void saveRoute(Routes route) throws FileNotFoundException, IOException;
	String scheduleTrip(Trip trip) throws FileNotFoundException, IOException;
	String cancelTrip(int tripID) throws FileNotFoundException, IOException;
	
	boolean bookTrip(int passengerID, int tripID);
	boolean cancelBooking(int bookingID);
	
	int allocateDriver(Connection conn) throws SQLException;
	boolean deallocateDriver(int tripID);
	boolean driverStatusUpdate(int driverID);
	 String getDriverNameById(Connection conn, int driverID) throws SQLException;
	
	List<Bookings>getBookingsByPassenger(int passengerID);
	List<Bookings>getBookingsByTrip(int tripID);
	List<String>getAvailableDrivers();
	
}
