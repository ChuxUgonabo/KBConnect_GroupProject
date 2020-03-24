package test.com.kbconnect.boundary;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kbconnect.boundary.ProductDAO;
import com.kbconnect.entity.Product;

class ProductDAOTest {

	Product productToTest;
	ProductDAO productDAOToTest;
	ArrayList<Product> listToTest;

	@BeforeEach
	void setUp() throws Exception {
		productToTest = new Product();
		productToTest.set_description("this is test product");
		productToTest.set_price(20.00);
		productToTest.set_type("test");

		productDAOToTest = new ProductDAO();
	}

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	@Test 
	void testUpdaeProduct() {
		productDAOToTest.createProduct(productToTest);
		listToTest = productDAOToTest.getAllProducts();
		Product currProduct = new Product();
		currProduct = listToTest.get(listToTest.size() - 1);
		//set update value for testing
		currProduct.set_description("updating testing");
		
	    assertTrue(productDAOToTest.updateProduct(currProduct));
	    productDAOToTest.deleteProduct(currProduct);
		
	}
	
	@Test
	void testGetOneProduct() {
		productDAOToTest.createProduct(productToTest);

		listToTest = productDAOToTest.getAllProducts();
		Product currProduct = new Product();
		currProduct = listToTest.get(listToTest.size() - 1);
		
		assertEquals(currProduct.get_description(), productDAOToTest.getProduct(currProduct.get_id()).get_description());
	
	    productDAOToTest.deleteProduct(currProduct);
	}

	/**
	 * tesing for delete function in productDAO
	 */
	@Test
	void testDeleteProduct() {
		productDAOToTest.createProduct(productToTest);

		listToTest = productDAOToTest.getAllProducts();
		Product currProduct = new Product();
		currProduct = listToTest.get(listToTest.size() - 1);

		assertTrue(productDAOToTest.deleteProduct(currProduct));

	}

}
