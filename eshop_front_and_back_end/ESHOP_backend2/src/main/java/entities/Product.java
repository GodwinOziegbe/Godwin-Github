package entities;

import java.io.Serializable;
import javax.ejb.Stateless;


@Stateless
public class Product implements Serializable{
	private static final long serialVersionUID = 2L;
	private int productID;
	private String productName;
	private String unitPrice;
	private String dateInStock;

	public Product(int productID, String productName, String unitPrice, String date) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.dateInStock = date;
	}

	public Product() {
		super();
		
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	
	public String getDateInStock() {
		return dateInStock;
	}

	public void setDateInStock(String inStockDate) {
		this.dateInStock = inStockDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String name) {
		this.productName = name;
	}

	
	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", unitPrice=" + unitPrice
				+ ", dateInStock=" + dateInStock + "]";
	}

	
	
	
	
}
