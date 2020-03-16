package test.com.kbconnect.entity;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import java.text.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.kbconnect.entity.Alert;

class AlertTest {

	Alert alertToTest;
	Date aday = new Date(1584333710);

	@BeforeEach
	void setUp() throws Exception {

		// initialize a new alert
		alertToTest = new Alert();

		alertToTest.set_id(1);
		alertToTest.set_shortDescription("School will be closed");
		alertToTest.set_description("This week is last week of this semester");	
		alertToTest.set_dateCreated(aday);
		alertToTest.set_dateOfLastUpdate(aday);

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

}
