package test.com.kbconnect.boundary;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kbconnect.boundary.AlertDAO;
import com.kbconnect.entity.Alert;

class AlertDAOTest {

	AlertDAO alertDAOToTest;
	Alert alertToTest;

    @BeforeEach
    void setUp() throws Exception {
    	alertDAOToTest = new AlertDAO();
    	alertToTest = new Alert();

    }

//  @Test
//  void test() {
//      fail("Not yet implemented");
//  }

//    @Test
//    void testCreateAlert() {
//    	alertToTest.set_description("This is the long description");
//    	alertToTest.set_shortDescription("short Description");
//    	boolean wasSuccessFull = alertDAOToTest.createAlert(alertToTest);
//    	assertTrue(wasSuccessFull);
//    	}
    
    /**
     * This test checks that an alert with no data in it is not created
     */
    @Test 
    void testEmptyCreateAlert() {
    	
    	// create an empty alert
    	Alert emptyAlert = new Alert();
    	// save the result returned by the AlertDAO
    	boolean wasSuccessfull = alertDAOToTest.createAlert(emptyAlert);
    	// test if the result was false, i.e. the alert was not created
    	assertFalse(wasSuccessfull);
    }

    
    /**
     * This test checks that an alert with no description is not created
     */
    @Test
    void testNullDescriptionAlert() {
    	Alert nullDescAlert = new Alert();
    	nullDescAlert.set_shortDescription("hello");
    	long millis = System.currentTimeMillis();
    	Date alertDate = new Date(millis);
    	
    	nullDescAlert.set_dateCreated(alertDate);
    	nullDescAlert.set_dateOfLastUpdate(alertDate);
    	
    	assertFalse(alertDAOToTest.createAlert(nullDescAlert));
    }
    
    /**
     * This test checks that an alert with null dates is not created
     */
    @Test
    void testNullDateAlert() {
    	Alert nullDateAlert = new Alert();
    	nullDateAlert.set_shortDescription("Short Description");
    	nullDateAlert.set_description("Long Description");
    	assertFalse(alertDAOToTest.createAlert(nullDateAlert));
    }
    
    /**
     * This test checks to see if the alert is created successfully
     */
    @Test
    void testAlertCreated() {
    	Alert newAlert = new Alert();
    	newAlert.set_shortDescription("test Description");
    	newAlert.set_description("test long description");
    	long millis = System.currentTimeMillis();
    	Date now = new Date(millis);
    	newAlert.set_dateCreated(now);
    	newAlert.set_dateOfLastUpdate(now);
    	assertTrue(alertDAOToTest.createAlert(newAlert));
    }

}
