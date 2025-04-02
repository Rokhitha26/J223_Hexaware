package TechShop2;

import java.util.Scanner;
public class mainClass {

	public static void main(String[] args) {
		
		Scanner scanner= new Scanner(System.in);
		
		Customer customer= new Customer("c01","Rokhitha","N","rokhi@gmail.com","1234567891","Pondy");
		Product product1= new Product("p01","Laptop","Laptops are good",500);
		Product product2= new Product("p02","Watch","Watches are good",550);
		Product product3= new Product("p03","Phone","Phone are good",600);
		Product product4= new Product("p04","Desktop","Desktop are good",700);
		Product product5= new Product("p05","Mouse","Mouse are good",800);
		Product product6= new Product("p06","Wires","Wires are good",9000);
		
	Inventory inv1=new Inventory();
	
		inv1.addToInventory(product1, 1000);
		inv1.addToInventory(product2, 2000);
		inv1.addToInventory(product3, 6000);
		inv1.addToInventory(product4, 7000);
		inv1.addToInventory(product5, 8000);
		inv1.addToInventory(product6, 3000);
	
		int userChoice;
		do {
			System.out.println("-----TechShop-----");
			System.out.println("1.Customer");
			System.out.println("2.Employee");
			System.out.println("3.Exit TechShop");
			System.out.println("Enter the choice: ");
			userChoice=scanner.nextInt();
			scanner.nextLine();
			
			switch(userChoice) {
			case 1:
				int custChoice;
				do {
					System.out.println("-----TechShop Customer DashBoard-----");
					System.out.println("1.Place order ");
					System.out.println("2.Update my Profile");
					System.out.println("3.View Order History");
					System.out.println("4.Exit TechShop");
					System.out.println();
					System.out.println("Enter your Choice");
					custChoice=scanner.nextInt();
					
					switch(custChoice) {
					case 1:
						System.out.println("-----Viewing Shop DashBoard-----");
						inv1.getProduct();
						scanner.nextLine();
						System.out.println("Enter the productID: ");
						String pID=scanner.nextLine();
						System.out.println("Enter the quantity: ");
						int pQuants=scanner.nextInt();
						scanner.nextLine();
						Order order= new Order("OR"+System.currentTimeMillis(), customer.getcustID(),inv1);
						order.calPrice(pID,pQuants);
						break;
					case 2:
						System.out.println("-----Update myProfile Dashboard-----");
						customer.updateCustomerInfo();
						
						break;
						
					case 3:
						System.out.println("-----View OrderHistory-----");
						
						break;
						
					case 4:
						System.out.println("Exiting TechShop!!!");
						break;
						
						default:
							System.out.println("Enter valid choice");
					}
					
				}while(custChoice!=4);break;
				
				
			case 2:
				int empChoice;
				do {
					System.out.println("-----Employee Dashboard----- ");
					System.out.println("1.View Inventory");
					System.out.println("2.Update Inventory");
					System.out.println("3.View Customer data");
					System.out.println("4.Exit Dashboard");
					System.out.println("");
					System.out.println("Enter your Choice");
					empChoice=scanner.nextInt();
					scanner.nextLine();
					switch(empChoice) {
					case 1:
						System.out.println("Viewing Inventory");
						inv1.getProduct();
						break;
					case 2:
						int empUpdateChoice;
						System.out.println("-----Update Inventory Dashboard-----");
						inv1.getProduct();
						do {
							System.out.println();
							System.out.println("1.Get quantity in stock for the product");
							System.out.println("2.Remove from Invnetory");
							System.out.println("3.List out the low stock products");
							System.out.println("4.List out of stock products");
							System.out.println("5.Exit Update Dashboard!!!");
							System.out.println("Enter your choice!!!");
							empUpdateChoice=scanner.nextInt();
							scanner.nextLine();
							switch(empUpdateChoice) {
							case 1:
								System.out.println("Get stock for the product(enter prodID): ");
								String prodId=scanner.nextLine();
								int stockQ=inv1.getQuantityInStock(prodId);
								System.out.println("Stock value of"+ prodId+" is: "+stockQ);
								break;
							case 2:
								System.out.println("Remove from inventory stocks (enter prodId): ");
								String UpdateProdId=scanner.nextLine();
								System.out.println("Remove from inventory stocks (enter updateQuantity): ");
								int updateQuantity=scanner.nextInt();
								scanner.nextLine();
								break;
							case 3:
								System.out.println("---low Stocked PRoducts---");
								inv1.listLowStockProducts();
								break;
							case 4:
								System.out.println("---OutofStocked Products---");
								inv1.listOutOfStock();
								break;
							case 5:
								System.out.println("Exiting the update dashboard");
								break;
								default:
									System.out.println("Enter a valid chice");
							}
						}while(empUpdateChoice!=5);
						break;
						
					case 3:
						System.out.println("View Customer Data");
						System.out.println("enter the customer id: ");
						String custid=scanner.nextLine();
						System.out.println(customer.getCustomerDetails(custid));
						break;
						
					case 4:
						System.out.println("Exiting TechShop!!!");
						break;
					
						default:
							System.out.println("Enter valid choice");
					}
				}while(empChoice!=4);break;
				
				
			case 3:
				System.out.println("Exiting TechShop!!!");
				break;	
			}	
		}while(userChoice!=3);
}
}