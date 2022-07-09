package restControllers;

import java.util.ArrayList;
import java.util.List;
import entities.Customer;

public interface CustomerRemote {

	List<Customer> getCustomers();

	ArrayList<Customer> makeCustomers();

}