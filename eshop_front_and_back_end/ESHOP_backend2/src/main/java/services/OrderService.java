package services;

import java.util.ArrayList;
import java.util.List;
import data.OrderDAO;
import entities.Order;



public class OrderService {
	
	private OrderDAO orderDAO= new OrderDAO();
	List<Order>orderList= new ArrayList<Order>();
	
	
	public List<Order> create(String email, int product, int quantity) {
				return orderList= orderDAO.create(email, product, quantity);
	}

	
	public List<Order> getAllOrders() {
	  return orderDAO.findAll();
	}


	public List<Order> createOrder(Order order) {
		return orderList= orderDAO.createOrder(order);
		
	}
	

}
