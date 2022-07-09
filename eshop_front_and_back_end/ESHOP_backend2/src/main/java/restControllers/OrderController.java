package restControllers;


import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import entities.Order;
import entities.Product;
import services.OrderService;


@Path("orders")
public class OrderController implements OrderRemote{
	
	
private static OrderService orderService= new OrderService();
ArrayList<Product>productList= new ArrayList<Product>();
List<Order> orderList=new ArrayList<Order>();
List<Order> cartList= new ArrayList<Order>();

	

	@Context
	UriInfo uriInfo;
	
	@GET
	@Path("/getall")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Override
	public List<Order> getOrders() {
		cartList=(List<Order>) orderService.getAllOrders();
		return cartList;
	}

	@POST
	@Path("/add{email}/{product}/{quantity}")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<Order> placeMyOrder(@PathParam("email")String email, @PathParam("product")int product, @PathParam("quantity") int quantity){
	
		return orderList=(List<Order>) orderService.create(email,product,quantity);
	}
	
	
	
	@POST
	@Path("/adding")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<Order> makeOrder(Order order){
	
		return orderList=(ArrayList<Order>) orderService.createOrder(order);
	}


	
		}

