package restControllers;

import java.util.ArrayList;
import java.util.List;
import entities.Product;

public interface ProductRemote {

	List<Product> getProducts();

	ArrayList<Product> makeProducts();

}