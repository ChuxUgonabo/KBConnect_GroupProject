/**
 * 
 */
package test.com.kbconnect.entity;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.kbconnect.entity.Order;
import com.kbconnect.entity.Product;
import com.kbconnect.entity.User;

/**
 * @author John Ugonabo
 *
 */
public class OrderTest {
	Order orderToTest;
	User tempUser;
	Product tempProduct;


	@Before
	public void setUp() throws Exception {
		orderToTest = new Order();
		orderToTest.set_id(1);
		orderToTest.set_quantity(3);
		// intitate a temp User
		tempUser = new User();
		tempUser.set_username("test");
		orderToTest.set_placedBy(tempUser);
		//intiate a temp Product
		tempProduct = new Product();
		tempProduct.set_description("tempProduct");
		orderToTest.set_productOrdered(tempProduct);
		orderToTest.set_approvalStatus(true);
	}
	@Test
	public void testGetOrderId() {
		assertEquals(1, orderToTest.get_id());
	}
	@Test
	public void testGetOrderQuantity() {
		assertEquals(3, orderToTest.get_quantity());
	}
	@Test
	public void testGetOrderPruduct() {
		assertEquals("tempProduct", orderToTest.get_productOrdered().get_description());
	}
	@Test
	public void testGetApprovedStatus() {
		assertEquals(true, orderToTest.is_approvalStatus());
	}
	
	@Test 
	public void testSetOrderId() {
		orderToTest.set_id(2);
		assertEquals(2, orderToTest.get_id());
	}

	@Test
	public void testSetOrderQuantity() {
		orderToTest.set_quantity(5);
		assertEquals(5, orderToTest.get_quantity());
	}
	
	@Test
	public void testSetOrderApproval() {
		orderToTest.set_approvalStatus(false);
		assertEquals(false, orderToTest.is_approvalStatus());
	}

}
