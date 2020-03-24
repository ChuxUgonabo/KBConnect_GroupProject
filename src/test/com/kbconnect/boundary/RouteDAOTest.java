package test.com.kbconnect.boundary;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kbconnect.boundary.RouteDAO;
import com.kbconnect.entity.Route;

class RouteDAOTest {
	Route routeToTest;
	RouteDAO routeDAOToTest;
	ArrayList<Route> listToTest;

	@BeforeEach
	void setUp() throws Exception {
		// instantiate a new route
		routeToTest = new Route();
		// set values to attributes
		routeToTest.set_routeNo("888");
		routeToTest.set_startingStop("douglas");
		routeToTest.set_terminationStop("waterfront");

		routeDAOToTest = new RouteDAO();
		listToTest = new ArrayList<Route>();
	}

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	/**
	 * testing for update a route function in routeDAO class
	 */
	@Test
	void testUpdateRoute() {
		// create new route
		routeDAOToTest.createRoute(routeToTest);
		listToTest = routeDAOToTest.getAllRoutes();

		Route curr = new Route();
		curr = listToTest.get(listToTest.size() - 1);
		// set the update value for testing
		curr.set_terminationStop("AIRPORT");
		assertTrue(routeDAOToTest.updateRoute(curr));

		routeDAOToTest.deleteRoute(curr);
	}

	/**
	 * testing for the getRoute function in routeDAO class
	 */
	@Test
	void testGetOneRoute() {
		// create new route
		routeDAOToTest.createRoute(routeToTest);
		listToTest = routeDAOToTest.getAllRoutes();

		Route curr = new Route();
		curr = listToTest.get(listToTest.size() - 1);

		assertEquals(curr.get_routeNo(), routeDAOToTest.getRoute(curr.get_id()).get_routeNo());

		routeDAOToTest.deleteRoute(curr);
	}

	/**
	 * testing for a delete route function
	 */
	@Test
	void testDeleteRoute() {
		// create new route
		routeDAOToTest.createRoute(routeToTest);
		listToTest = routeDAOToTest.getAllRoutes();

		Route curr = new Route();
		curr = listToTest.get(listToTest.size() - 1);

		assertTrue(routeDAOToTest.deleteRoute(curr));

	}

}
