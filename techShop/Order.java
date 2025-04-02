package TechShop2;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
public class Order {

	String oID;
	Customer customer;
	int totalAmount;
    LocalDate  OrderDate;
    String oStatus;
    Inventory inv;
    Product prod;
    String custid;
    
    HashMap<String,Order>orderData=new HashMap<>();
    
    Order(String oID,String custid,Inventory inv){
    this.oID=oID;	
    this.custid=custid;
    this.inv=inv;
    this.OrderDate=LocalDate.now();
    this.oStatus="Processing";
    }

    
  void calPrice(String prodid, int Mquantity) {
	 int oQuants= inv.getQuantityInStock(prodid);
	  if(oQuants>=Mquantity) {
		  prod=inv.products.get(prodid);
		  int price=prod.getProdPrice();
		  totalAmount=price*Mquantity;
		 inv.removeFromInventory(prodid,Mquantity);
		 orderData.put(oID,this);
			System.out.println("Order Placed!!!");
			//customer.orderHistory.add(this);
	  }
	  else {
		  System.out.println("Purchase quantity exceeded stock availability!!!, We will reach you back");
	  }
  }
 void updateOrderStatus(String oID, String newStatus) {
	if(orderData.containsKey(oID)) {
		orderData.get(oID).oStatus=newStatus;
		System.out.println("Order"+oID+" Status updated to:"+ newStatus);
	}
	else {
		System.out.println("Invalid orderID");
	}
 }
         void displayOrderDetails(String oID) {
     if (orderData.containsKey(oID)) {
         Order order = orderData.get(oID);
         System.out.println("Order ID: " + order.oID);
         System.out.println("Customer ID: " + order.custid);
         System.out.println("Total Amount: " + order.totalAmount);
         System.out.println("Order Date: " + order.OrderDate);
         System.out.println("Order Status: " + order.oStatus);
     } else {
         System.out.println("No order found with ID: " + oID);
     }
 }
}
