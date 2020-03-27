package test.com.kbconnect.entity;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import java.text.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.kbconnect.entity.Alert;
import com.kbconnect.entity.Route;
/**
 * Create JUnit test case for testing the entity of Alert
 * @author user
 *
 */

class AlertTest {

	Alert alertToTest;
	Date aday = new Date(1584333710);
	Route routeToTest;

	@BeforeEach
	void setUp() throws Exception {

		// initialize a new alert
		alertToTest = new Alert();
	
		alertToTest.set_id(1);
		alertToTest.set_shortDescription("School will be closed");
		alertToTest.set_description("This week is last week of this semester");	
		alertToTest.set_dateCreated(aday);
		alertToTest.set_dateOfLastUpdate(aday);
		
		routeToTest= new Route();
		routeToTest.set_routeNo("No3275");
		routeToTest.set_startingStop("douglas");
		routeToTest.set_terminationStop("waterFront");
		routeToTest.set_fromCity("New Westminster");
		routeToTest.set_toCity("Vancouver");
		
		alertToTest.set_route(routeToTest);

	}

	@Test
	void testGetId() {

		assertEquals(1, alertToTest.get_id());
	}

	@Test
	void testGetShortDescription() {
		assertEquals("School will be closed", alertToTest.get_shortDescription());
	}

	@Test
	void testGetDescription() {
		assertEquals("This week is last week of this semester", alertToTest.get_description());
	}

	@Test
	void testGetDateCreated() {
		assertEquals(aday, alertToTest.get_dateCreated());
	}

	@Test
	void testGetDateOfLastUpdate() {
		assertEquals(aday, alertToTest.get_dateOfLastUpdate());
	}
	
	@Test
	void testGetRoute() {
		assertSame(routeToTest, alertToTest.get_route());
		
	}

	@Test
	void testsetId() {
		alertToTest.set_id(3);
		assertEquals(3, alertToTest.get_id());
	}

	@Test
	void testsetShortDescription() {
		alertToTest.set_shortDescription("For school");
		assertEquals("For school", alertToTest.get_shortDescription());
	}

	@Test
	void testsetDescription() {
		alertToTest.set_description("we will not have classes after this week");
		assertEquals("we will not have classes after this week", alertToTest.get_description());
	}

	@Test
	void testsetDateCreated() throws ParseException {

		Date date = new Date(1584333718);
		alertToTest.set_dateCreated(date);
		assertEquals(date, alertToTest.get_dateCreated());
	}

	@Test
	void testsetDateOfLastUpdate() throws ParseException {
		Long mills = System.currentTimeMillis();
		Date date = new Date(mills);
		alertToTest.set_dateOfLastUpdate(date);
		assertEquals(date, alertToTest.get_dateOfLastUpdate());
	}

	@Test
	void testSetRoute() {
		Route curr= new Route();
		alertToTest.set_route(curr);
		assertNull(alertToTest.get_route().get_startingStop(), "it may get a null value of route statringStop");
	}
}
