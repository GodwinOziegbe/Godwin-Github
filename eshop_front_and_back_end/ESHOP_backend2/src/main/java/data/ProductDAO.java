package data;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.enterprise.context.ApplicationScoped;
import com.github.javafaker.Faker;
import entities.Order;
import entities.Product;



@ApplicationScoped
public class ProductDAO {
		Serialiser serialiser= new Serialiser();
		Faker faker= new Faker();
		ArrayList<Order>cartList= new ArrayList<Order>();
		 Product product= new Product();
		ArrayList<Product>productList=new ArrayList<>();
		List<Product>allProductList=new ArrayList<>();
		 int start_date = 18200;
	        int end_date=18800;
	     
	        LocalDate date;

		public ArrayList<Product> createProducts() {
			 
			for(int i=1; i<6; i++) {
	

				 date=LocalDate.ofEpochDay(ThreadLocalRandom.current().nextInt(start_date, end_date));//generates random dates, start and end_dates are in days from 1970
				
				String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

				
		if(productList==null) {
				
			product= new Product(faker.number().numberBetween(1, 20), //faker method to generate fake product
					faker.commerce().productName(), faker.commerce().price(20.0, 79.99), formattedDate);
			productList.add(product); 
			
			}else {
				
				product= new Product(faker.number().numberBetween(1, 20),
						faker.commerce().productName(), faker.commerce().price(20.0, 79.99), formattedDate);
				
			for(Product value:productList) {
			while( product.getProductID()==value.getProductID())	{//check product list to make sure no productID is repeated

				product= new Product(faker.number().numberBetween(1, 9),faker.commerce().productName(),
						faker.commerce().price(20.0, 79.99), formattedDate); }
			}
			productList.add(product);
			
			
			}
		
		
			}
			allProductList=serialiser.getAllProductList(allProductList);
			allProductList.addAll(productList);
			allProductList=serialiser.serializeAllProductList(allProductList);	
			return productList;

		}
		public List<Product>findAll() {
			
			return serialiser.getAllProductList((ArrayList<Product>)allProductList);
			
		}
		
		
}
