package techShop;

public class Order {
private String orderID;
private Customer customer;
private Products product;
private int quantity;


public Order(String orderID, Customer customer, Products product, int quantity ) {
	
	this.orderID=orderID;
	this.customer=customer;
	this.product=product;
	this.quantity=quantity;
}

public void processOrder() {
	product.updateStock(quantity);
	  System.out.println("Order " + orderID + " placed for " + quantity + " " + product.getName() + "(s)");
	
}
public String toString() {
	return "orderID:" + orderID + ", Product: " + product.getName()+" , Quantity"+ quantity;
}
}
