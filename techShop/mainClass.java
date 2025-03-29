package techShop;

import java.util.Scanner;

public class mainClass {

	public static void main(String[] args) {
		
	Scanner scanner =new Scanner(System.in);
	Inventory inventory= new Inventory();
	
	inventory.addProduct(new Products("P100","Laptop",1200,10));
	inventory.addProduct(new Products("P1002","SmartPhone",800,5));
	
	Employee employee= new Employee("E01","Rokhitha","Manager", inventory);
	Customer customer= new Customer("C101","Riya","riya@gmail.com");
	
	
	
	int userType;
	
	
	do {
		System.out.println("\n-----Welcome to TechShop-----");
		System.out.println("1.Customer");
		System.out.println("2.Employee");
		System.out.println("3.Exit");
		System.out.println("Enter your role ");
		
		userType= scanner.nextInt()	;
		scanner.nextLine();
		
		switch(userType) {
		
		case 1:
			int customerChoice;
			do {
				System.out.println("\n-----Customer Menu-----");
				System.out.println("1.Place Order");
				System.out.println("2.View Order Histoey");
				System.out.println("3.Go Back");
				System.out.println("Enter your choice: ");
				customerChoice= scanner.nextInt();
				scanner.nextLine();
				
				switch(customerChoice) {
				case 1:
					System.out.println("\n Available Products: ");
					inventory.showInventory();
					
					System.out.println("Enter the prodId to place order: ");
					String prodID= scanner.nextLine();
					
					System.out.println("Enter quantity: ");
					int quantity= scanner.nextInt();
					scanner.nextLine();
					
					Products selectedProduct= inventory.getProductid(prodID);
					
					if(selectedProduct != null) {
						Order newOrder= new Order("O"+ System.currentTimeMillis(),customer, selectedProduct, quantity);
						customer.placeOrder(newOrder);
						newOrder.processOrder();
					}
					else {
						System.out.println("Invalid Product Id");
					}
					break;
					
				case 2:
					customer.showOrderHistory();
					break;
				case 3:
					System.out.println("Returning to main menu!!!");
					break;
					
				default:
					System.out.println("Invalid choice! Try again.");
				
				}
				
		}while(customerChoice !=3);
			break;
			
		case 2:
			int empChoice;
			
			do {
				System.out.println("\n-----Employee Menu-----");
				System.out.println("1.Manage Inventory");
				System.out.println("2.View Inventory");
				System.out.println("3.Go Back");
				System.out.println("Enter your choice: ");
				
				empChoice= scanner.nextInt();
				scanner.nextLine();
				
				switch(empChoice) {
				
				case 1:
					System.out.println("\nCurrent Inventory:");
					inventory.showInventory();
					
					System.out.println("Enter product ID to update Stock: ");
			        String updateProdID= scanner.nextLine();
			        
					System.out.println("Enter quantity of stock update: ");
					int newStock= scanner.nextInt();
					
					employee.manageInventory(updateProdID, newStock);
					break;
				case 2:
					inventory.showInventory();
					break;
				case 3:
					System.out.println("going to main ");
					break;
					default:
						System.out.println("Invalid choice!");
				
				}
			}while(empChoice !=3);
			break;
			
		case 3:
			System.out.println("Exit techShop!!");
			break;
			default:
				System.out.println("Invalid choice!");
			
		}
	}while(userType!=3);
	scanner.close();
	
	}

}
