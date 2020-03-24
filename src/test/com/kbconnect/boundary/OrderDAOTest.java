/**
 * 
 */
package test.com.kbconnect.boundary;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kbconnect.boundary.OrderDAO;
import com.kbconnect.entity.Admin;
import com.kbconnect.entity.Order;
import com.kbconnect.entity.Product;
import com.kbconnect.entity.User;

/**
 * @author William
 *
 */
class OrderDAOTest {

	/**
	 * @throws java.lang.Exception
	 */

	Order orderToTest;
	OrderDAO orderDAOToTest;
	ArrayList<Order> originalList;

	@BeforeEach
	void setUp() throws Exception {
		orderToTest = new Order();

		orderToTest.set_quantity(0);
		long millis = System.currentTimeMillis();
		Date today = new Date(millis);
		orderToTest.set_transactionDate(today);

		Product p = new Product();
		p.set_id(1000);
		orderToTest.set_productOrdered(p);

		User n = new User();
		n.set_id(1000);
		orderToTest.set_placedBy(n);

		Admin admin = new Admin();
		admin.set_id(1000);
		orderToTest.set_approvedBy(admin);

		orderDAOToTest = new OrderDAO();
		originalList = orderDAOToTest.getAllOrders();

	}

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	/**
	 * testing for getAllOrders method
	 */

	@Test
	void testGetAllOrders() {

		orderDAOToTest.createOrder(orderToTest);
		ArrayList<Order> currList = new ArrayList<Order>();
		currList = orderDAOToTest.getAllOrders();

		assertEquals(originalList.size() + 1, currList.size());

		orderToTest = currList.get(currList.size() - 1);
		orderDAOToTest.deleteOrder(orderToTest);

	};

	/**
	 * Testing for the geteOneOrder method
	 */
	@Test
	void testGetOrder() {
		orderDAOToTest.createOrder(orderToTest);
		int position = (orderDAOToTest.getAllOrders().size()) - 1;
		Order curr = new Order();
		curr = orderDAOToTest.getAllOrders().get(position);

		orderToTest = orderDAOToTest.getOrder(curr.get_id());

		assertEquals(curr.get_approvedBy().get_id(), orderToTest.get_approvedBy().get_id());
		orderDAOToTest.deleteOrder(curr);

	};

	/**
	 * 
	 * testing for updateOrder function
	 */
	@Test
	void testUpdateOrder() {
		orderDAOToTest.createOrder(orderToTest);

		ArrayList<Order> currList = new ArrayList<Order>();
		currList= orderDAOToTest.getAllOrders();
		
		int position = currList.size() - 1;
		Order updatedOrder = new Order();
		updatedOrder = currList.get(position);
		
		Admin updatedAdmin = new Admin();
		updatedAdmin.set_id(1000000);
		updatedOrder.set_approvedBy(updatedAdmin);

		assertTrue(orderDAOToTest.updateOrder(updatedOrder));

		orderDAOToTest.deleteOrder(updatedOrder);
	};

	/**
	 * testing for the deleteOrder function
	 */
	@Test
	void testDeleteOrder() {

		orderDAOToTest.createOrder(orderToTest);
		ArrayList<Order> currList = new ArrayList<Order>();
		currList = orderDAOToTest.getAllOrders();
		Order order = new Order();
		order = currList.get(currList.size() - 1);

		assertTrue(orderDAOToTest.deleteOrder(order));

	};

}
