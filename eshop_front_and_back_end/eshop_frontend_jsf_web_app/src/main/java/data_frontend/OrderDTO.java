package data_frontend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client; 
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities_frontend.Order;
import entities_frontend.Product;
import javax.ws.rs.client.WebTarget;

public class OrderDTO {
	
	public String getOrders() {
		Client client = ClientBuilder.newClient(); 
		
	WebTarget webTarget =client.target("http://localhost:8080/ESHOP/eshopapp/orders/getall");
		
		 Response response = webTarget.request(MediaType.APPLICATION_JSON).get(); 
		
		 
		if (response.getStatus() != 200) { 
		      String myerror="Error invoking REST web service - " + response.getStatusInfo().getReasonPhrase(); 
		      return myerror; 
		    } 
		 
		    //REST call was successful. return the response 
		      return response.readEntity(String.class); 
	
	}



	public String addOrder(Order order) throws JsonParseException, JsonMappingException, IOException {
		 Client client = ClientBuilder.newClient(); 
		 WebTarget webTarget; Response response;
		 Order adding_order = new Order();//order with product object
		 String reply;	  
		 //first, get all products to find product ordered
		 ObjectMapper objectMapper= new ObjectMapper();//maps string into objects
			
		 ProductDTO productDTO= new ProductDTO();
		 List<Product>productList= new ArrayList<Product>();
		 			 	
		 	String myProductsList = productDTO.getProductsJSON();
		 	 productList=objectMapper.readValue(myProductsList, new TypeReference<List<Product>>() {});
		 
		 //iterate through productList and extract the product
			for(Product value:productList) {
				if(order.getProductID()==value.getProductID()) {
					adding_order= new Order(order.getCustomerEmail(),value,order.getQuantity());//adding the project object

				}
				
			}
		 
		 webTarget =client.target("http://localhost:8080/ESHOP/eshopapp/orders/adding");
		 response = webTarget.request().post(Entity.entity(adding_order,MediaType.APPLICATION_JSON_TYPE));
		 
		//Check response code. 200 is OK 
		    if (response.getStatus() != 200) { 
		      //Print error message 
		    	 reply="Error invoking REST Web Service - " + response.getStatusInfo().getReasonPhrase() +  
		    			",  Error Code : " + response.getStatus()+"response message: "+response.readEntity(String.class); 
		     
		       
		    } else reply="order created! Response message: "+response.readEntity(String.class);
		    return reply;
		 
		    
		  } 
		
		
}	  
		 

	


