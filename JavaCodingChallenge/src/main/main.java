package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;


import dao.careerHub;
import dao.careerHubImpl;
import entity.Applicant;
import entity.Company;
public class main {

	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
   Scanner get= new Scanner(System.in);
   careerHub service=new careerHubImpl();
   int userType;
  
   do {
	   System.out.println("Enter your type: ");
	   System.out.println("1.Applicant");
	   System.out.println("2.Company");
	   System.out.println("3.Exit aplication..");
	   System.out.println();
	   System.out.println("Enter you  choice");
	   userType= get.nextInt();
	   get.nextLine();
	   int customerID = 0;
	   int companyID=0;
	   switch(userType) {
	   
	   case 1:
		   System.out.println("-----Customer DashBoard-----");
			System.out.println();
			int authChoice;
			int applicantID =0;
			boolean loggedIn=false;
			
			do {
				System.out.println("1. Create Account");
				System.out.println("2. Login");
				System.out.println("3.Exit");
				System.out.print("Choose Option: ");
				authChoice = get.nextInt();
				get.nextLine(); 
				 
				switch (authChoice) {
				    case 1:
				    	System.out.print("Enter First Name: ");
				    	String firstName = get.nextLine();

				    	System.out.print("Enter Last Name: ");
				    	String lastName = get.nextLine();

				    	System.out.print("Enter Email: ");
				    	String email = get.nextLine().trim();

				    	System.out.print("Enter Phone Number: ");
				    	String phone = get.nextLine().trim();

				    	System.out.print("Enter Resume Path (e.g., resume.pdf): ");
				    	String resumePath = get.nextLine().trim();

				    	System.out.print("Enter Years of Experience: ");
				    	int experience = get.nextInt();
				    	get.nextLine(); 

				    	System.out.print("Enter City: ");
				    	String city = get.nextLine();

				    	System.out.print("Enter State: ");
				    	String state = get.nextLine();
				    	Applicant applicant = new Applicant(firstName, lastName, email, phone, resumePath, experience, city, state);
				        boolean inserted = service.insertApplicant(applicant);
				        if (inserted) {
				            System.out.println(" Account Created Successfully. You can now log in.");
				        } else {
				            System.out.println(" Error occurred while creating the account.");
				        }
				        break;
				case 2:
					System.out.print("Enter your email: ");
					String loginEmail = get.nextLine().trim();

					 applicantID = service.verifyApplicantByEmail(loginEmail);

					// Loop until a valid login
					while (applicantID == -1) {
					    System.out.print("Enter your email again: ");
					    loginEmail = get.nextLine().trim();
					    applicantID = service.verifyApplicantByEmail(loginEmail);
					}

					loggedIn = true;

					break;	
				}
			}while(!loggedIn);
			int userChoice=0;
			do {
				System.out.println("1.Apply Jobs");
				System.out.println("2.View job Status");
				System.out.println("3.Exit");
				System.out.println("Enter your choice: ");
				userChoice=get.nextInt();
				get.nextLine();
				
				switch(userChoice) {
				
				case 1:
				    System.out.println("----- Apply to a Job -----");
				    service.listAllJobs(); 
				    System.out.print("Enter the Job Title you want to apply for: ");
				    String selectedJobTitle = get.nextLine().trim();
				    service.applyToJob(applicantID, selectedJobTitle,get);
				    break;					
				case 2:
				    System.out.println("----- View Job Application Status -----");
				    service.viewApplicationStatus(applicantID); 
				    break;
				case 3:
					System.out.println("Exiting...");
					break;
					default:
						System.out.println("enter valid choice");
				}
				
			}while(userChoice!=3);
			break;
			
			
	   case 2:
		   
		   System.out.println("----- Company Dashboard -----");
		   System.out.println();
		   int authChoice1;
		   boolean companyLoggedIn = false;
		   int loggedInCompanyID = -1;
		   do {
		       System.out.println("1. Create Account");
		       System.out.println("2. Login");
		       System.out.print("Choose Option: ");
		       authChoice1 = get.nextInt();
		       get.nextLine(); // consume newline

		       switch (authChoice1) {
		           case 1:
		               System.out.print("Enter Company Name: ");
		               String companyName = get.nextLine();

		               System.out.print("Enter Company Location: ");
		               String location = get.nextLine();

		               Company company = new Company(companyName, location); // companyID is auto-incremented

		               boolean inserted = service.insertCompany(company);

		               if (inserted) {
		                   System.out.println("Company account created successfully. You can now log in.");
		               } else {
		                   System.out.println("Error occurred while creating the company account.");
		               }
		               break;
		           case 2:
		               System.out.print("Enter your Company Name: ");
		               String loginCompanyName = get.nextLine().trim();

		               loggedInCompanyID = service.verifyCompanyByName(loginCompanyName);

		               while (loggedInCompanyID == -1) {
		                   System.out.println(" Company not found. Please try again or register.");
		                   System.out.print("Enter your Company Name: ");
		                   loginCompanyName = get.nextLine().trim();
		                   loggedInCompanyID = service.verifyCompanyByName(loginCompanyName);
		               }
		               System.out.println("Logged in successfully. Company ID: " + loggedInCompanyID);
		               companyLoggedIn = true;
		               break;

		           default:
		               System.out.println("Invalid option. Please choose 1 or 2.");
		       }
		   } while (!companyLoggedIn);
int compChoice=0;
		  do {
			 System.out.println("1.Post Jobs");
			 System.out.println("2.List the jobs posted");
			 System.out.println("3.Exit");
			 System.out.println("Enter your choice: ");
			 compChoice=get.nextInt();
			 get.nextLine();			 
			 switch(compChoice) {
			 case 1:
				 System.out.println("-----Post Job-----");
				 service.postJob(loggedInCompanyID,get);
				 break;
			 case 2:
				 System.out.println("-----List Posted jobs------");
				 service.listPostedJobs(loggedInCompanyID);
				 break;
				 
			 case 3:
				 System.out.println("-----Exit-----");
				 break;
				 default:
					 System.out.println("Enter a valid choice");				 
			 }
			 
		  }while(compChoice!=3);

		  
		   
		   break;
		   
	   case 3:
		   System.out.println("Exit...");
		   break;	   
		  default:
			  System.out.println("enter vlaid choice");
	   }
   }while(userType!=3);

	}

}
