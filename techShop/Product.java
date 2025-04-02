package TechShop2;

import java.util.Scanner;

public class Product {
	Scanner get= new Scanner(System.in);
	
	protected String prodId;
	String prodName;
	String prodDes;
	int prodPrice;

	
	Product(String prodId, String prodName, String prodDes, int prodPrice){
		this.prodId=prodId;
		this.prodName=prodName;
		this.prodDes=prodDes;
		this.prodPrice=prodPrice;
	}
	//getters for products
	public String getProdId() {return prodId;}
	public String getProdName() {return prodName;}
	public String getProdDes() {return prodDes;}
	public int getProdPrice() {return prodPrice;}
	
	//setters for products
	public void setProdID() {
		String ProdID;
		System.out.println("Enter the update Product ID:");
		ProdID=get.nextLine();
		this.prodId=ProdID;
		}
	public void setProdName() {
		String ProdName;
		System.out.println("Enter the update Product name");
		ProdName=get.nextLine();
		this.prodName=ProdName;
		}
	public void setProdDes() {
		String ProdDes;
		System.out.println("Enter the update Product Description: ");
		ProdDes=get.nextLine();
		this.prodDes=ProdDes;
		}
	public void setProdPrice() {
		int ProdPrice;
		System.out.println("Enter the update Product Price: ");
		ProdPrice=get.nextInt();
		this.prodPrice=ProdPrice;
		}
	
	public void updateProdInfo() {
		int upProd;
		do {
			System.out.println("Product Update Dashboard");
			System.out.println("1.Update ProductID");
			System.out.println("2.Update ProductName");
			System.out.println("3.Update Product Description");
			System.out.println("4.Update Product Price");
			System.out.println("5.Exit Product update Dashboard");
			System.out.println();
			System.out.println("Enter you choice 1,2,3,4,5");
			upProd=get.nextInt();
			get.nextLine();
			switch(upProd) {
			case 1:
				setProdID();
				break;
			case 2:
				setProdName();
				break;
			case 3:
				setProdDes();
				break;
			case 4:
				setProdPrice();
				break;
			case 5:
				System.out.println("Exiting Product update Dashboard!!!");
				break;
				default:
					System.out.println("Enter a valid option");			
			}
		}while(upProd!=5);
		
		
		
	}
	public void  getProdDetails()//for the employee
	{
		System.out.println("ID: "+getProdId()+"Product Name: "+ getProdName()+ "Description: "+getProdDes()+"Price: $"+getProdPrice());
	}
	
	
	
	
	
	
}
