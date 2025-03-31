package techShop;

public  class Products {

	private String productID;
	private String name;
	private double price;
	private int stock;
	
	public Products(String productID, String name, int price, int stock) {
		this.productID=productID;
		this.name= name;
		this.price=price;
		this.stock=stock;
	}
	
	 public String getProductID() {
		return productID;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	public int geStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock=stock;
	}
	
	public void updateStock(int quantity) {
		if(stock>=quantity) {
			stock-=quantity;
		}
		else {
			System.out.println("Insufficient stock for"+ name);
		}
	}
	
	
}
