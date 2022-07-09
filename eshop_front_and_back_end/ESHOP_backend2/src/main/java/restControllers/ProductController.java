package restControllers;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import entities.Product;
import services.ProductService;

@Path("products")
public class ProductController implements ProductRemote {
	
	private ProductService productService = new ProductService();
	ArrayList<Product>productList= new ArrayList<Product>();
	ArrayList<Product>allProductList= new ArrayList<Product>();
	
	@Context
	UriInfo uriInfo;
	
	@Override
	@GET
	@Path("/getproducts")
	@Produces(MediaType.APPLICATION_JSON)
	
	public List<Product> getProducts() {
		
		allProductList=(ArrayList<Product>) productService.getAllProducts();
		return allProductList;
		
	}


	@POST
	@Path("/addproducts")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public ArrayList<Product> makeProducts() {
		
		 productList= (ArrayList<Product>) productService.createProducts(productList);
		 
		 return productList;
		 
	}
}
