package test.com.kbconnect.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kbconnect.entity.Route;

class RouteTest {

	Route routeToTest;
	
	@BeforeEach
	void setUp() throws Exception {
		// initialize a new test
		routeToTest = new Route();
	}

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

	@Test
	void testEmptyRoute() {
		
		// check to see that none of the values are set
		assertNull(routeToTest.get_fromCity());
		assertNull(routeToTest.get_routeNo());
		assertNull(routeToTest.get_startingStop());
		assertNull(routeToTest.get_terminationStop());
		assertNull(routeToTest.get_toCity());
		
	}
	
	
	public void populateRoute() {
		// populate all values for the route
		routeToTest.set_fromCity("Vancouver");
		routeToTest.set_toCity("Richmond");
		routeToTest.set_routeNo("96 B-Line");
		routeToTest.set_startingStop("92932");
		routeToTest.set_terminationStop("98329");
	}
	
	
	void testGetters() {
		// fill the route with values for its attributes
		populateRoute();
		
		// Test the getters
		assertEquals("Vancouver", routeToTest.get_fromCity());
		assertEquals("Richmond", routeToTest.get_toCity());
		assertEquals("96 B-Line", routeToTest.get_routeNo());
		assertEquals("92932", routeToTest.get_startingStop());
		assertEquals("98329", routeToTest.get_terminationStop());
		
	}

	
	@Test
	void testChangeFromCity() {
		// set the city to New York
		routeToTest.set_fromCity("New York");
		assertEquals("New York", routeToTest.get_fromCity());
	}
	
	@Test
	void testChangeToCity() {
		// set the city to New York
		routeToTest.set_toCity("Nakodar");
		assertEquals("Nakodar", routeToTest.get_toCity());
	}

	@Test
	void testChangeRouteNo() {
		// set the city to New York
		routeToTest.set_routeNo("301");
		assertEquals("301", routeToTest.get_routeNo());
	}

	@Test
	void testChangeStartingRoute() {
		// set the city to New York
		routeToTest.set_startingStop("00000");
		assertEquals("00000", routeToTest.get_startingStop());
	}
	
	@Test
	void testChangeTerminationStop() {
		// set the city to New York
		routeToTest.set_terminationStop("22222");
		assertEquals("22222", routeToTest.get_terminationStop());
	}

	
}
