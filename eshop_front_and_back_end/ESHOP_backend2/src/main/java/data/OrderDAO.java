package data;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import entities.Order;
import entities.Product;


@ApplicationScoped
public class OrderDAO {
	Serialiser serialiser= new Serialiser();
	
	private List <Order>orderList= new ArrayList<Order>();
	List<Order>cartList= new ArrayList<Order>();
	 Product product= new Product();
	ArrayList<Product>productList=new ArrayList<>();
	
	public List<Order>create(String email, int product, int quantity){
		Order order;
		 productList=serialiser.getProductList(productList);
		for(Product value:productList) {
			if(product==value.getProductID()) {
				order= new Order(email,value,quantity);orderList.add(order);
				cartList=serialiser.getCartList(cartList);	cartList.add(order);
			}
			
		}
		
		cartList=serialiser.serializeCartList(cartList);	
		return orderList;
	}

	public List<Order> createOrder(Order order) {
		orderList.add(order);
		cartList=serialiser.getCartList(cartList);
		cartList.add(order);
		
		cartList=serialiser.serializeCartList(cartList);	
		return orderList;
	}
	
	public List<Order>findAll(){
		
		return cartList=serialiser.getCartList((ArrayList<Order>)cartList);
		
	}
	
}
