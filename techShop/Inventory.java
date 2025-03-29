package techShop;

import java.util.HashMap;

public class Inventory {

	private HashMap<String, Products>products= new HashMap<>();
	
	public void addProduct(Products product) {
		products.put(product.getProductID(), product);
	}
	
	public void showInventory() {
		
		for(Products prod: products.values()) {
			System.out.println(prod.getProductID()+" "+ prod.getName()+" -Price: $"+prod.getPrice());
		}
	}
	
	public Products getProductid(String productID) {
	return products.get(productID);
	}
	
	
	
	
	
}
