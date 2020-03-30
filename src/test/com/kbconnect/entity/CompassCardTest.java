/**
 * 
 */
package test.com.kbconnect.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import com.kbconnect.entity.CompassCard;

/**
 * @author William
 *
 */
class CompassCardTest {

	/**
	 * @throws java.lang.Exception
	 */
	
	CompassCard compassCardToTest;
	@BeforeEach
	void setUp() throws Exception {
		
		compassCardToTest= new CompassCard("9999999","999",false, 100.00 );
			
	}
//
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	@Test
	void testGetCompassCardNumber() {
		//should be actually same
		assertEquals("9999999",compassCardToTest.get_cardNumber());
	}
	
	@Test
	void testGetBalance() {
		//should be get a equal value
		assertEquals(100.00, compassCardToTest.get_loadedBalance());
	}
	
	@Test
	void testGetIsActive() {
		
		assertFalse(compassCardToTest.is_isActive());
	}
	
	@Test
	void testSetCompassCardNumber() {
		//set value to compassCardNumber
		compassCardToTest.set_cardNumber("888888888888");
		//should be same
		assertEquals("888888888888", compassCardToTest.get_cardNumber());
		
	}
	@Test
	void testSetBalance() {
		//set value to balance attribute
		compassCardToTest.set_loadedBalance(99.99);
		//should be get a equal value
		assertEquals(99.99,compassCardToTest.get_loadedBalance());
	}
	
	@Test
	void testSetIsActive() {
		//set boolean to isActive attribute
		compassCardToTest.set_isActive(true);
		assertTrue(compassCardToTest.is_isActive());
	}
	
	@Test
	void  testCompareToEncryptedCVN() {
		assertTrue(BCrypt.checkpw("999", compassCardToTest.get_cvn()));
	}
	
	@Test 
	void testSetCVN() {
		//set cvn value, after that it should be encrypted
		compassCardToTest.set_encryptedCvn("000");
		//should be get encrypted value of "000"
		assertTrue(BCrypt.checkpw("000", compassCardToTest.get_cvn()));
	}
	
	

}
