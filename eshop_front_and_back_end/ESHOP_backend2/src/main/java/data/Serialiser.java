package data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import entities.Order;
import entities.Product;

public class Serialiser {
	private  static final String SERIALIZED_FILE_NAME = "ProductList.xml";
	private  static final String SERIALIZED_FILE_NAME1 = "CartList.xml";
	private  static final String SERIALIZED_FILE_NAME3 = "AllProduct.xml";
	@SuppressWarnings("unchecked")
	
	public ArrayList<Product>getProductList(ArrayList<Product>productList) {//decoder for product list
		
		XMLDecoder decoder = null;
		try {
			// Skapa en decoder, med en output stream och en target file
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_FILE_NAME)));
			System.out.println("File stream opened and XMLDecoder created");

		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File productList.xml not found");
		}
		
		productList = (ArrayList<Product>) decoder.readObject();
		
			
		 System.out.println("File stream closed");
		 decoder.close();
		
		return productList;
		
	}

	public ArrayList<Product>serializeProductList(ArrayList<Product>productList) {//Encoder for product list
		
		XMLEncoder encoder = null;
		 try {
		 	// Skapa en encoder, med en output stream och en target file
		 	encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(SERIALIZED_FILE_NAME)));
		 	System.out.println("File stream opened and XMLEncoder created");

		 } catch (FileNotFoundException fileNotFound) {
		 	System.out.println("ERROR: While Creating or Opening the File ProductList.xml");
		 }

		 
		 System.out.println("Object written");
		encoder.writeObject(productList);
		
		encoder.flush();
		 encoder.close();
		 
		return productList;
		
	}
	public List<Order> serializeCartList(List<Order> cartList) {//encoder for order list
		
		XMLEncoder encoder = null;
		 try {
		 	// Skapa en encoder, med en output stream och en target file
		 	encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(SERIALIZED_FILE_NAME1)));
		 	System.out.println("File stream opened and XMLEncoder created");

		 } catch (FileNotFoundException fileNotFound) {
		 	System.out.println("ERROR: While Creating or Opening the File CartList.xml");
		 }

		 
		 System.out.println("Object written");
		encoder.writeObject(cartList);
		
		encoder.flush();
		 encoder.close();
		 return cartList;
		 
	
}
	@SuppressWarnings("unchecked")
	public List<Order> getCartList(List<Order> cartList) {//decoder for order list
		
		XMLDecoder decoder = null;
		try {
			// Skapa en decoder, med en output stream och en target file
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_FILE_NAME1)));
			System.out.println("File stream opened and XMLDecoder created");

		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File CartList.xml not found");
		}
		
		cartList = (List<Order>) decoder.readObject();
		
			
		 System.out.println("File stream closed");
		 decoder.close();
		 
		 return cartList;
	
}
	
public List<Product> serializeAllProductList(List<Product> allProductList) {//encoder for order list
		
		XMLEncoder encoder = null;
		 try {
		 	// Skapa en encoder, med en output stream och en target file
		 	encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(SERIALIZED_FILE_NAME3)));
		 	System.out.println("File stream opened and XMLEncoder created");

		 } catch (FileNotFoundException fileNotFound) {
		 	System.out.println("ERROR: While Creating or Opening the File AllProduct.xml");
		 }

		 
		 System.out.println("Object written");
		encoder.writeObject(allProductList);
		
		encoder.flush();
		 encoder.close();
		 return allProductList;
		 
	
}
	@SuppressWarnings("unchecked")
	public List<Product> getAllProductList(List<Product> allProductList) {//decoder for order list
		
		XMLDecoder decoder = null;
		try {
			// Skapa en decoder, med en output stream och en target file
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_FILE_NAME3)));
			System.out.println("File stream opened and XMLDecoder created");

		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File AllProduct.xml not found");
		}
		
		allProductList = (List<Product>) decoder.readObject();
		
			
		 System.out.println("File stream closed");
		 decoder.close();
		 
		 return allProductList;
	
}
}
