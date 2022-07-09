package services;

import java.util.ArrayList;
import entities.Customer;


public class CustomerService {

  //private OrderDAO orderDAO;
	public Customer newCustomer(String name, String email) {
	
		Customer customer= new Customer(name, email);
		
		//staticData.saveCustomer(customer);
		
		return customer;
		
		
	}

	public ArrayList<Customer> getAllCustomers(ArrayList<Customer> customerList) {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer getCustomer(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
