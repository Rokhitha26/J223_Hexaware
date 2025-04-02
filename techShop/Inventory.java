package TechShop2;

import java.util.HashMap;

public class Inventory {
	//String invId;
Product product;
	int QuantityInStock=0;
	public HashMap<String,Product>products=new HashMap<>();
	public HashMap<String,Integer>prodQ= new HashMap<>();
	
	Inventory(){}
	
	
	void addToInventory(Product prod, int quantity) {
		products.put(prod.getProdId(),prod);
		prodQ.put(prod.getProdId(), quantity);
	}

     void getProduct() {
    	 if(products.isEmpty()) {
    		 System.out.println("Inventory is empty");
    	 }
    	 else {
    		 System.out.println("------Viewing Product Dashboard-----");
    		 for(String prodId: products.keySet()) {
    			 Product prod= products.get(prodId);
    			 String prodName=prod.getProdName();
    			 int price= prod.getProdPrice();
    			 String prodID=prod.getProdId();
    			 System.out.println("ProductId: "+prodID+"  "+" ProductName: "+"  "+prodName+" ProductPrice: "+"  "+price);
    		 }
    	 }   
}
     int getQuantityInStock(String prodId) {
    	 return prodQ.get(prodId);	 
     }
     void removeFromInventory(String prodId, int rQuants) {//also updates stockQuantity
    	 int quants= prodQ.get(prodId);
    	 if(rQuants>quants) {
    		 prodQ.put(prodId,0);
    		 System.out.println("Product updated! Currently the stock is empty");
    	 }
    	 else {
    		 int newQuants=quants-rQuants;
    		 prodQ.put(prodId, newQuants);
    		 System.out.println("Product stock updated successfully");
    	 }
     }
     
     void listLowStockProducts() {
    	 boolean val=true;
    	 for(String pId: products.keySet()) {
    		 Product prod= products.get(pId);
    		 int quantity=prodQ.get(pId);
    		 if(quantity<200) {
    			 System.out.println(prod.getProdName()+" is in lowStock");
    			 val=false;
    		 } 
    	 }
    	 if(val==true) {
    		 System.out.println("All products are in required Stock!");
    	 }
     }
     
     void listOutOfStock() {
    	 boolean val=true;
    	 for(String pId: products.keySet()) {
    		 
    		 Product prod=products.get(pId);
    		 int quantity=prodQ.get(pId);
    		 if(quantity==0) {
    			 System.out.println(prod.getProdName()+" is out of stock!!");
    		 }
    	 }
    	 if(val==true) {
    		 System.out.println("All products are in required stock value");
    	 }
     }
     
     
}
