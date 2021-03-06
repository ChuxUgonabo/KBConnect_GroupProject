/**
 * 
 */
package test.com.kbconnect.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kbconnect.entity.Admin;

/**
 * @author William
 * Creat Junit Test case for admin entity
 *
 */
class AdminTest {


	
	Admin adminToTest;
	@BeforeEach
	void setUp() throws Exception {
		
		adminToTest= new Admin("FakeName Tested","fakename","123456789","junitTest@kbconnect.com","Royal ave","1900-01-01");
	}

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	/**
	 * Testing all getter methods
	 */
	@Test 
	void testIsAdmin() {
		// by default isAdmin,  so should be return the true
         assertTrue(adminToTest.is_isAdmin());
	}
	/**
	 * Testing all setter methods
	 */
	
	@Test 
	void testSetIsAdmin() {
		adminToTest.set_isAdmin(false);
		//set false so that it should return the false
		assertFalse(adminToTest.is_isAdmin());
		
	}

}
