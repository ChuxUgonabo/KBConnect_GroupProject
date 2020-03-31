package test.com.kbconnect.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kbconnect.entity.CompassCard;
import com.kbconnect.entity.LoadedPasses;
import com.kbconnect.entity.TravelPass;
/**
 * @author Gursewak Singh
 * Create Junit test case for LoadedPassed entity
 *
 */

class LoadedPassesTest {

	LoadedPasses passesToTest;
	CompassCard newCard;
	TravelPass newPass;
	Date currentDate;
	Date weekAfter;
	
	@BeforeEach
	void setUp() throws Exception {
		
		// initialize the current Date
		currentDate = new Date(System.currentTimeMillis());
		
		// initialize the week after date
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.DATE, 7); // minus number would decrement the days
		weekAfter = new Date(cal.getTimeInMillis());
		
		
		// initialize a new loaded pass
		passesToTest = new LoadedPasses();
		
		// create a new compass Card and travel pass
		newCard = new CompassCard("0909090909", "930", false, 0);
		newPass = new TravelPass();
		
		// populate the travel pass
		newPass.set_passDuration("weekly");
		newPass.set_passType("adult");
		newPass.set_price(179);
		
		// populate the pass that is to be tested
		passesToTest.set_card(newCard);
		passesToTest.set_pass(newPass);
		passesToTest.set_startDate(currentDate);
		passesToTest.setAutorenew(false);
		
	}
	
	
	
	
//	int _id;
//	CompassCard _card; // the card on which the passes is loaded
//	TravelPass _pass; // the pass that is loaded
//	Date _startDate; // starting date of the pass
//	Date _endDate; // ending date of the pass
//	boolean autorenew; // pointer to check if the pass auto-renews
//	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	/**
	 * Testing all getter methods
	 */

	@Test 
	void testGetters(){
		assertEquals(newPass, passesToTest.get_pass());
		assertEquals(newCard, passesToTest.get_card());
		assertEquals(currentDate, passesToTest.get_startDate());
		assertEquals(weekAfter, passesToTest.get_endDate());
	}
	

	
}
