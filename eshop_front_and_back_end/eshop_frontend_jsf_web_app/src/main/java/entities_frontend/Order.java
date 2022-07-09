package entities_frontend;

public class Order {
	private String customerEmail;
	private Product product;
	private int quantity;
	private int productID;
	
	public Order(String customerEmail, Product product, int quantity) {
		super();
		this.customerEmail = customerEmail;
		this.product = product;
		this.quantity = quantity;
	}

	public Order(String customerEmail, int productID, int quantity) {
		super();
		this.customerEmail = customerEmail;
		this.quantity = quantity;
		this.productID = productID;
	}

	public Order() {
		super();
		
	}
	
		

	public int getProductID() {
		return productID;
	}



	public void setProductID(int productID) {
		this.productID = productID;
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
