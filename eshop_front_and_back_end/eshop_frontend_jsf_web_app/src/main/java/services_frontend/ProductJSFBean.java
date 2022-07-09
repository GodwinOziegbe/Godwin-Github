package services_frontend;

import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data_frontend.ProductDTO;
import entities_frontend.Product;



@ManagedBean(name="productJSFBean")
@RequestScoped 
public class ProductJSFBean {
private String myProductsList;
private List<Product>productList;
private String errorMSG;

public String getMyproductsList() {
	return myProductsList;
}
public void setMyproductsList(String myproducts) {
	this.myProductsList = myproducts;
}

public List<Product> getProductList() {
	return productList;
}


public void setProductList(List<Product> productList) {
	this.productList = productList;
}


public String getErrorMSG() {
	return errorMSG;
}


public void setErrorMSG(String errorMSG) {
	this.errorMSG = errorMSG;
}


public String getProducts() throws JsonParseException, JsonMappingException, IOException {
	
	ObjectMapper objectMapper= new ObjectMapper();//maps string into objects
	
ProductDTO productDTO= new ProductDTO();
	
	
	 myProductsList = productDTO.getProductsJSON();
	 productList=objectMapper.readValue(myProductsList, new TypeReference<List<Product>>() {});
	
	 if (myProductsList.equals(null)) { 
		 
		    errorMSG = "Invalid results. Please try again"; 
		    return null; 
		  } else { 
			  errorMSG=null;

				 return "printproducts";
}

}

public String addProducts() throws JsonParseException, JsonMappingException, IOException {
	ObjectMapper objectMapper= new ObjectMapper();//maps string into objects
	ProductDTO productDTO= new ProductDTO();
		
	String addedProducts=null;
		
	addedProducts = productDTO.addProductsJSON();
	 productList = objectMapper.readValue(addedProducts, new TypeReference<List<Product>>() {});
	
	 
	 if (addedProducts.equals(null)) { 
		 
		    errorMSG = "Invalid results. Please try again"; 
		    return null; 
		  } else { 
			  errorMSG=null;
			 
				 return "printproducts";
}

}
}

