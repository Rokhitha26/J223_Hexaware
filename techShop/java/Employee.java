package techShop;

public class Employee {

	
	private String employeeID;
	private String name;
	private String position;
	private Inventory inventory;
	
	public Employee(String employeeID, String name, String position, Inventory inventory) {
		this.employeeID=employeeID;
		this.name=name;
		this.position=position;
		this.inventory=inventory;
	}
	
	public void manageInventory(String productID, int newStock) {
		Products product=inventory.getProductid(productID);
		if(product != null) {
			product.setStock(newStock);
			System.out.println("Stock updated for "+ product.getName()+ ". New Stock: "+ newStock);
			
		}
		else {
			System.out.println("Product not found");
		}
	}
}
