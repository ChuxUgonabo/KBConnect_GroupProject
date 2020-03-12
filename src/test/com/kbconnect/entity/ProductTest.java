package test.com.kbconnect.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kbconnect.entity.Product;

class ProductTest {

	Product productToTest;
	
	@BeforeEach
	void setUp() throws Exception {
		
		// initialize a new product
		productToTest = new Product();
		productToTest.set_description("This is a wearable watch");
		productToTest.set_price(20);
		productToTest.set_type("Watch");
		productToTest.set_id(3);
		
	}

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	@Test
	void testGetDescription() {
		assertEquals("This is a wearable watch", productToTest.get_description());
	}
	
	@Test
	void testGetPrice() {
		assertEquals(20.0, productToTest.get_price());
	}
	
	@Test
	void testGetType() {
		assertEquals("Watch", productToTest.get_type());
	}
	
	@Test
	void testGetId() {
		assertEquals(3, productToTest.get_id());
	}
	
	@Test
	void testSetId() {
		productToTest.set_id(1);
		assertEquals(1, productToTest.get_id());
	}
	
	@Test
	void testSetPrice() {
		productToTest.set_price(10);
		assertEquals(10, productToTest.get_price());
	}
	
	@Test
	void testSetType() {
		productToTest.set_type("Band");
		assertEquals("Band", productToTest.get_type());
	}
	
	@Test
	void testSetDescription() {
		productToTest.set_description("This is the new Description");
		assertEquals("This is the new Description", productToTest.get_description());
	}

}
