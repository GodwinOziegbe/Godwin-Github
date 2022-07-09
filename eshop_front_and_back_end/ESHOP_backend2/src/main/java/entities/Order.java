package entities;

import java.io.Serializable;


import javax.ejb.Stateless;

@Stateless
public class Order implements Serializable {
	private static final long serialVersionUID = 3L;
	private String customerEmail;
	private Product product;
	private int quantity;
	
	public Order(String customerEmail, Product product, int quantity) {
		super();
		this.customerEmail = customerEmail;
		this.product = product;
		this.quantity = quantity;
	}

	public Order() {
		super();
		
	}
	

	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}



	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	
}
