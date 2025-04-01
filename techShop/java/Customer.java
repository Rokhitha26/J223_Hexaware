package techShop;

import java.util.Scanner;
import java.util.ArrayList;
public class Customer {
Scanner get=new Scanner(System.in);

	String CustomerID;
	String FirstName;
	//String LastName;
	String Email;
	//String Phone;
	//String Address;
	ArrayList<Order>orderHistory=new ArrayList<>();
	
	Customer(String CustomerID, String FirstName, String Email){		
	/*	System.out.println("Enter CustomerID: ");
		CustomerID=get.nextInt();
		get.nextLine();
		System.out.println("Enter FirstName: ");
		FirstName=get.nextLine();
		System.out.println("Enter LastName: ");
		LastName= get.nextLine();
		System.out.println("Enter Email: ");
		Email=get.nextLine();
		System.out.println("Enter Phone: ");
		Phone= get.nextLine();
		System.out.println("Enter Address: ");
		Address=get.nextLine(); */
		this.CustomerID=CustomerID;
		this.FirstName=FirstName;
		//this.LastName=LastName;
		this.Email=Email;
	}
	
	public void placeOrder(Order order) {
		orderHistory.add(order);
	}
	
	public void showOrderHistory() {
		if(orderHistory.isEmpty()) {
			System.out.println("No orders yet");
		}
		else {
			for(Order or: orderHistory) {
				System.out.println(or);
			}
		}
		
	}
	
	
	
}
