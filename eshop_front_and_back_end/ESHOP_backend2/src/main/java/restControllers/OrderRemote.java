package restControllers;

import java.util.List;
import entities.Order;


public interface OrderRemote {

	
	
	List <Order> getOrders();
	
	List<Order> makeOrder(Order order);
	
	List<Order> placeMyOrder(String email, int product, int quantity);
	
	
	
}
