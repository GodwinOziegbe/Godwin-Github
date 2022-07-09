package services;

import java.util.ArrayList;
import java.util.List;
import data.ProductDAO;
import entities.Product;
public class ProductService {
	
	private ProductDAO productDAO= new ProductDAO();
	List<Product>productList=new ArrayList<Product>();
	List<Product>allProductList=new ArrayList<Product>();
	
	public List<Product> getAllProducts() {
			return allProductList=productDAO.findAll();
	}
	
	
	public ArrayList<Product> createProducts(ArrayList<Product>productList) {
		return productList= productDAO.createProducts();
		
		
	}

}
