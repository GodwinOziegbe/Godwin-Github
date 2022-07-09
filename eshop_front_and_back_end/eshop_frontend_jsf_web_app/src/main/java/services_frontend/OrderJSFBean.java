package services_frontend;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data_frontend.OrderDTO;
import entities_frontend.Order;



@ManagedBean(name ="orderJSFBean") 
@RequestScoped 
public class OrderJSFBean {

private String email;
private int productID;
private int quantity;
private String errorMSG;
private String orderList;
private List<Order>myOrders;



public List<Order> getMyOrders() {
	return myOrders;
}
public void setMyOrders(List<Order> myOrders) {
	this.myOrders = myOrders;
}
public String getOrderList() {
	return orderList;
}
public void setOrderList(String orderList) {
	this.orderList = orderList;
}
public String getErrorMSG() {
	return errorMSG;
}
public void setErrorMSG(String errorMSG) {
	this.errorMSG = errorMSG;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getProductID() {
	return productID;
}
public void setProductID(int productID) {
	this.productID = productID;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}

public String getOrders() throws JsonParseException, JsonMappingException, IOException {
	ObjectMapper objectMapper= new ObjectMapper();
	OrderDTO orderDTO = new OrderDTO();	 

	 orderList= orderDTO.getOrders();
	 //List<Order>orders= new ArrayList<Order>();
	 myOrders=objectMapper.readValue(orderList, new TypeReference<List<Order>>() {});
	 
	 if (orderList.equals(null)) { 
		 
		    errorMSG = "Invalid results. Please try again"; 
		    return null; 
		  } else { 
			  errorMSG=null;
			  

			//order=   getOrder();
			  return "printorders";
			  
		  }			 
}



public String makeOrder()  {
		return  "orderform";
	}


public String addOrder() throws JsonParseException, JsonMappingException, IOException {
	
	OrderDTO orderDTO = new OrderDTO();	 
	Order order = new Order(email,productID,quantity);
	
	String  addingOrder= orderDTO.addOrder(order);
	
	 
	 if (addingOrder.equals("not added")) { 
		 
		    errorMSG = "No order added. Please try again"; 
		    return null; 
		  } else { 
			  errorMSG=null;
			 
			 				
				 return "orderadded";
	}
	
	
}

public void getCurrentOrder()  {
	//ObjectMapper objectMapper= new ObjectMapper();
	//OrderDTO orderDTO = new OrderDTO();	 

	 //orderList= orderDTO.getOrders();
	// List<Order>orders= new ArrayList<Order>();
	// orders=objectMapper.readValue(orderList, new TypeReference<List<Order>>() {});
	 
//	 while (i<orders.size()) {
//	 for(Order value:orders) {
//		 System.out.println(value);
//		 
//	 }
//	i++;
	// }
	//return null;
	
}




}
