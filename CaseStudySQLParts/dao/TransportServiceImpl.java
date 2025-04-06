package dao;

import entity.Vehicle;
import entity.Bookings;
import entity.Driver;
import entity.Routes;
import entity.Trip;
import util.DBConnUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TransportServiceImpl implements TransportService {

	Scanner get=new Scanner(System.in);

	public void showVehicle() throws FileNotFoundException, SQLException, IOException {
		String sql= "select model,capacity,type from vehicles where status='Available'";
		
		try(Connection conn=DBConnUtil.getConnection("src/util/application.properties");){
			Statement st=conn.createStatement();
			ResultSet rs= st.executeQuery(sql);
			
			System.out.printf("%-20s %-10s %-15s%n", "Model", "Capacity", "Type");
			System.out.println("----------------------------------------------------");
			
			while(rs.next()) {
				String model=rs.getString("model");
				double capacity=rs.getDouble("Capacity");
				String type=rs.getString("type");
				
				System.out.printf("%-20s %-10.2f %-15s%n", model, capacity, type);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public boolean addVehicle(Vehicle vehicle) throws FileNotFoundException, IOException {
		String sql="insert into vehicles ( model, capacity,type,status) values(?,?,?,?)";
		try(Connection conn=DBConnUtil.getConnection("src/util/application.properties");){
			PreparedStatement ps=conn.prepareStatement(sql);
			
			ps.setString(1,vehicle.getModel());
			ps.setDouble(2, vehicle.getCapacity());
			ps.setString(3,vehicle.getType());
			ps.setString(4,vehicle.getStatus());
			
			int rowsInserted=ps.executeUpdate();
			System.out.println("Added vehicle");
			return rowsInserted >0;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean updateVehicle(int vehicleID) throws FileNotFoundException, IOException {
		String sql="update vehicles set model=?, capacity=?, type=?, status=? where vehicleID=?";
		try(Connection conn=DBConnUtil.getConnection("src/util/application.properties")){
			PreparedStatement ps= conn.prepareStatement(sql);
			
			System.out.println("Enter the Model to be updated: ");
			String newModel=get.nextLine();
			ps.setString(1, newModel);
			
			System.out.println("Enter the Updated Capacity: ");
			int newCapacity=get.nextInt();
			get.nextLine();
			ps.setInt(2, newCapacity);
		    
			System.out.println("Enter the Updated Type: ");
			String newType=get.nextLine();
			ps.setString(3,newType);
			
			System.out.println("Enter the updated Status: ");
			String newStatus=get.nextLine();
			ps.setString(4, newStatus);
			
			ps.setInt(5,vehicleID);
			
			int rowsUpdated=ps.executeUpdate();
		    System.out.println("Rows were updated!!!");
			return rowsUpdated>0;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	
	}
	
	public boolean deleteVehicle(int vehicleID) throws FileNotFoundException, IOException {
		String sql="delete from vehicles where vehicleID=?";
		
		try(Connection Conn=DBConnUtil.getConnection("src/util/application.properties");){
			PreparedStatement ps= Conn.prepareStatement(sql);
			System.out.println("enter the VehicleId for deletion: ");
			int deleteVehicle= get.nextInt();
			ps.setInt(1, deleteVehicle);
			int updates=ps.executeUpdate();
			System.out.println("Vehicle deleted from DB!!");
			return updates>0;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String scheduleTrip(Trip trip) throws FileNotFoundException, IOException {
	String insertTripSql="Insert into trips (vehicleName,departureDate,status,passengerCount,driverID,routeID)values(?,?,?,?,?,?)";	
	String driverName;
	try(Connection conn=DBConnUtil.getConnection("src/util/application.properties")){
		
		int allocatedDriverID=allocateDriver(conn);
		if(allocatedDriverID==-1) {
			return "NO available drivers at the moment.";
		}
		driverName=getDriverNameById(conn,allocatedDriverID);
		
		PreparedStatement ps=conn.prepareStatement(insertTripSql,Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, trip.getVehicle());
		
		ps.setTimestamp(2, Timestamp.valueOf(trip.getDepartureDate().atStartOfDay()));
		//ps.setTimestamp(4, Timestamp.valueOf(trip.getArrivalDate().atStartOfDay()));
		ps.setString(3, trip.getStatus());
		ps.setInt(4, trip.getPassengerCount());
		ps.setInt(5, allocatedDriverID);
		ps.setInt(6, trip.getRoute().getRouteID());
		
		int rows=ps.executeUpdate();
		
		if(rows>0) {
			ResultSet rs=ps.getGeneratedKeys();
			if(rs.next()){
				int tripId=rs.getInt(1);
				return "Trip scheduled Successfully with Driver: " +driverName+" and Trip ID: "+tripId;
			}
		}		
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
	return "Trip Scheduling failed";
	
	}
	
	public String cancelTrip(int tripID) throws FileNotFoundException, IOException {
		String updateTripSQL = "UPDATE trips SET tripType = 'Cancelled' WHERE tripID = ?";
	    String updateDriverStatusSQL = "UPDATE drivers SET dstatus = 'Available' WHERE driverID = (SELECT driverID FROM trips WHERE tripID = ?)";

	    try (Connection conn = DBConnUtil.getConnection("src/util/application.properties")) {

	        conn.setAutoCommit(false);

	       
	        try (PreparedStatement psTrip = conn.prepareStatement(updateTripSQL)) {
	            psTrip.setInt(1, tripID);
	            psTrip.executeUpdate();
	        }

	       
	        try (PreparedStatement psDriver = conn.prepareStatement(updateDriverStatusSQL)) {
	            psDriver.setInt(1, tripID);
	            psDriver.executeUpdate();
	        }

	        conn.commit();
	        return "Trip cancelled successfully and driver marked as available.";

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "Failed to cancel trip.";
	    }
		
	}
	
	public boolean bookTrip(int passengerID, int tripID) {
		
		return false;
	}
	
	public boolean cancelBooking(int bookingID) {
		
		
		return false;
	}
	
	public int allocateDriver(Connection conn) throws SQLException {
		String sql="Select driverID from drivers where dStatus='Available' limit 1";
		try(Statement st=conn.createStatement();){
			ResultSet rs=st.executeQuery(sql);
			if(rs.next()) {
				int driverID=rs.getInt("driverID");
				
				PreparedStatement dUpdate=conn.prepareStatement("update drivers set dStatus='Assigned' where driverID=?");
				dUpdate.setInt(1,driverID);
				dUpdate.executeUpdate();
				return driverID;
			}
		}
		return -1;
	}
	
	public boolean deallocateDriver(int tripID) {
		
		return false;
	}
	public boolean driverStatusUpdate(int driverID) {
		 String updateSQL = "UPDATE drivers SET dstatus = 'Available' WHERE driverID = ?";

		    try (Connection conn = DBConnUtil.getConnection("src/util/application.properties");
		         PreparedStatement ps = conn.prepareStatement(updateSQL)) {
		        
		        ps.setInt(1, driverID);
		        int rowsUpdated = ps.executeUpdate();

		        return rowsUpdated > 0; 

		    } catch (SQLException | IOException e) {
		        e.printStackTrace();
		        return false;
		    }
		
	}
	
	public String getDriverNameById(Connection conn, int driverID) throws SQLException {
	PreparedStatement ps=conn.prepareStatement("select driverName from drivers where driverID=?");
	ps.setInt(1, driverID);
	ResultSet rs=ps.executeQuery();
	if(rs.next()) {
		return rs.getString("driverName");
	}
	return "Unknown";
	}
	
	public List<Bookings> getBookingsByPassenger(int passengerID){
		
		return new ArrayList<>();
	}
	
	public List<Bookings>getBookingsByTrip(int tripID){
		
		return new ArrayList<>();
	}
	
	public List<String>getAvailableDrivers(){
	
		 List<String> availableDriverNames = new ArrayList<>();
		    String sql = "SELECT driverName FROM drivers WHERE dstatus = 'Available'";

		    try (Connection conn = DBConnUtil.getConnection("src/util/application.properties");
		         PreparedStatement ps = conn.prepareStatement(sql);
		         ResultSet rs = ps.executeQuery()) {

		        while (rs.next()) {
		            availableDriverNames.add(rs.getString("driverName"));
		        }
			    
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return availableDriverNames;
	
	}
	public void saveRoute(Routes route) throws FileNotFoundException, IOException {
		String sql="insert into routes(startDestination,endDestination,distance, fare)values(?,?,?,?)";
		try(Connection conn= DBConnUtil.getConnection("src/util/application.properties");){
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,route.getStartDestination());
			ps.setString(2,route.getEndDestination());
			ps.setDouble(3,route.getDistance());
			ps.setDouble(4, route.getFare());
			int res= ps.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
