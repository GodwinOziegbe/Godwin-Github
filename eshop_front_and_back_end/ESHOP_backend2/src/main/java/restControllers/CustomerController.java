package restControllers;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import entities.Customer;
import services.CustomerService;


@Path("customers")
public class CustomerController implements CustomerRemote {
	private static CustomerService customerService=new CustomerService();
	private ArrayList<Customer>customerList= new ArrayList<Customer>();
	
	
	@Context
	UriInfo uriInfo;
	
	//TODO(not working yet)
	@Override
	@GET
	@Path("/getcustomers")
	@Produces(MediaType.APPLICATION_JSON)
	
	public List<Customer> getCustomers() {
		//productList=serializer.getProductList(productList);
		return customerList;
		
	}

//TODO(not working yet)
	@POST
	@Path("/addcustomer")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public ArrayList<Customer> makeCustomers() {
		
		// orderList=  orderService.getAllOrders();
		 
		 customerList= customerService.getAllCustomers(customerList);
		 
		 return customerList;
		 
	}
}
