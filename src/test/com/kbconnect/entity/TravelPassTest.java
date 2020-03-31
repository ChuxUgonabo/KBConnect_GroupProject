package test.com.kbconnect.entity;

/**
 * @author William
 * Junit Test case for entity of TravelPass
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kbconnect.entity.TravelPass;

class TravelPassTest {

	TravelPass travelPassTotest;

	@BeforeEach
	void setUp() throws Exception {
        //instantiate a new travelpass for test
		travelPassTotest = new TravelPass();
		// set the value to attributes to test the getter method
		travelPassTotest.set_passDuration("yearly");
		travelPassTotest.set_price(200.00);
		travelPassTotest.set_passType("normal");
	}

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
    /**
     * Testing all getter Methods
     */
	@Test
	void testGet_passDuration() {
		assertEquals("yearly", travelPassTotest.get_passDuration());
	}

	@Test
	void testSet_passDuration() {
		travelPassTotest.set_passDuration("biweekly");
		assertEquals("biweekly", travelPassTotest.get_passDuration());
	}

	@Test
	void testGet_price() {
		assertEquals(200.00, travelPassTotest.get_price());
	}
/**
 * Testing all setter Methods
 */
	@Test
	public void testSet_price() {
		travelPassTotest.set_price(100.00);
		assertEquals(100.00, travelPassTotest.get_price());

	}

	@Test
	void testGet_passType() {
		assertEquals("normal", travelPassTotest.get_passType());
	}

	@Test
	void testSet_passType() {
		travelPassTotest.set_passType("private");
		assertEquals("private", travelPassTotest.get_passType());

	}

}
