package TechShop2;

import java.util.ArrayList;
import java.util.Scanner;

public class Customer {

	private String CustomerId;
	String FirstName;
	String LastName;
	String Email;
	String Phone;
	String Address;
	ArrayList<Order>orderHistory= new ArrayList<>();
	
	Scanner get= new Scanner(System.in);
	
	public Customer(String CustomerId, String FirstName, String LastName, String Email, String Phone,String Address) {
		
		this.CustomerId= CustomerId;
		this.FirstName=FirstName;
		this.LastName=LastName;
		this.Email= validateEmail(Email);
		this.Phone=validatePhone(Phone);
		this.Address=Address;
	}
	
	private String validateEmail( String Email) {
		
		while(!Email.endsWith("@gmail.com") || Email.endsWith("@yahoo.com")) {
			System.out.println("Invalied email!! retry!!");
		    System.out.println("Reenter email: ");	
		    Email=get.nextLine();
	}
	return Email;	
	}
	
	private String validatePhone( String Phone) {
		while(!Phone.matches("\\d{10}")) {
			System.out.println("ReEnter the Phone Number");
			Phone= get.nextLine();
		}
		return Phone;
	}
	//getters for customers
	public String getcustID(){return CustomerId;}
	public String getFirstName() {return FirstName+" "+ LastName;}
	public String getEmail() {return Email;}
	public String getPhone() {return Phone;}
	//setters for customers
	public void  setEmail() {
		System.out.println("Enter the update Email:");
		String newEmail=get.nextLine();
		this.Email=validateEmail(newEmail);
		System.out.println("Email reset Successfully!!!");
	}
	
	public void setPhone() {
		System.out.println("Enter the update PhoneNumber:");
		String newPhone= get.nextLine();
		this.Phone= validatePhone(newPhone);
		System.out.println("Phone number reset Successfully!!!");
	}
	
	public void setAddress() {
		System.out.println("Enter the update Address: ");
		String newAddress=get.nextLine();
		this.Address=newAddress;
	}
	
	public void  updateCustomerInfo() {
		int userChoice;
		do {
			System.out.println("Customer Data Change Dashboard: ");
			System.out.println("1.Change my Email");
			System.out.println("2.Change my Phone Number");
			System.out.println("3.change my Address");
			System.out.println("4.Exit Dashboard!");
			System.out.println();
			System.out.println("Enter your choice 1,2 3");
			userChoice=get.nextInt();
			get.nextLine();
			
			switch(userChoice) {
			case 1:
				setEmail();
				break;
			case 2:
				setPhone();
				break;
			case 3:
				setAddress();
				break;
			case 4:
				System.out.println("Exiting Update Dashboard...");
				break;
				default:
					System.out.println("enter the right choice!!!");
					break;
			}
		}while(userChoice!=4);
	}
	
	public int totalOrders()
	{
		return  orderHistory.size();
	}
	
	public String getCustomerDetails(String custID) {
		if (!this.CustomerId.equals(custID)) {
			return "No customer found with ID: " + custID;
		}
		return "Customer ID: " + CustomerId + "\n" +
		       "Name: " + FirstName + " " + LastName + "\n" +
		       "Email: " + Email + "\n" +
		       "Phone: " + Phone + "\n" +
		       "Address: " + Address + "\n" +
		       "Total Orders: " + totalOrders();
	}
	}
	
	

