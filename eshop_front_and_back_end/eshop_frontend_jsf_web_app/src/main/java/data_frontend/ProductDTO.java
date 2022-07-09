package data_frontend;
import javax.ws.rs.client.Client; 
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ProductDTO{

	public String getProductsJSON() {
		Client client = ClientBuilder.newClient(); 
		
	WebTarget webTarget =client.target("http://localhost:8080/ESHOP/eshopapp/products/getproducts");
		
		 Response response = webTarget.request(MediaType.APPLICATION_JSON).get(); 
	
		
		if (response.getStatus() != 200) { 
		      String myerror="Error invoking REST web service - " + response.getStatusInfo().getReasonPhrase(); 
		      return myerror; 
		    } 
		 
		    //REST call was successful. return the response 
		     return response.readEntity(String.class); 
		
	}

	public String addProductsJSON() {
		Client client = ClientBuilder.newClient(); 
		Response response;
		
	WebTarget webTarget =client.target("http://localhost:8080/ESHOP/eshopapp/products/addproducts");
	
	response = webTarget.request().post(null);
		if (response.getStatus() != 200) { 
		      String myerror="Error invoking REST web service - " + response.getStatusInfo().getReasonPhrase(); 
		      return myerror; 
		    } 
		 
		    //REST call was successful. return the response 
		     return response.readEntity(String.class); 
		
	}

	
}
