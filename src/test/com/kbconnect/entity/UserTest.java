package test.com.kbconnect.entity;
/**
 * Junit test for entity of User
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kbconnect.entity.*;
import com.kbconnect.boundary.*;

class UserTest {
	User userToTest;

//	@Test
//	void test() {
//		fail("Not yet implemented");
//
//	}
	
	@BeforeEach
	void setUp() throws Exception {
		// initialize a new user
		userToTest = new User();

		userToTest.set_id(1);
		userToTest.set_fullName("Test Master");
		userToTest.set_username("testM");
 		userToTest.set_password("12345678");
		userToTest.set_email("test@gmail.com");
		userToTest.set_DOB("1999-02-12");
		userToTest.set_address("New Westminster, BC");
		userToTest.set_cardNumber("99999999999999");
	}
	/**
	 * Testing all getter methods
	 */

	@Test
	void testGetUsername() {
		assertEquals("testM", userToTest.get_username());
	}

	@Test
	void testGetPassword() {
		assertEquals("12345678", userToTest.get_password());
	}

	@Test
	void testGetFullName() {
		assertEquals("Test Master", userToTest.get_fullName());
	}

	@Test
	void testGetId() {
		assertEquals(1, userToTest.get_id());
	}

	@Test
	void testGetEmail() {
		assertEquals("test@gmail.com", userToTest.get_email());
	}

	@Test
	void testGetAddress() {
		assertEquals("New Westminster, BC", userToTest.get_address());
	}
	
	@Test 
	void testGetCardNumber() {
		assertEquals("99999999999999", userToTest.get_cardNumber());
	}
	// Testing all setter methods
	@Test
	void testSetUsername() {
		userToTest.set_username("newTestM");
		assertEquals("newTestM", userToTest.get_username());
	}

	@Test
	void testSetPassword() {
		userToTest.set_password("11111111");
		assertEquals("11111111", userToTest.get_password());
	}

	@Test
	void testSetFullName() {
		userToTest.set_fullName("New Test Master");
		assertEquals("New Test Master", userToTest.get_fullName());
	}

	@Test
	void testSetId() {
		userToTest.set_id(10);
		assertEquals(10, userToTest.get_id());
	}

	@Test
	void testSetEmail() {
		userToTest.set_email("newTest@gmail.com");
		assertEquals("newTest@gmail.com", userToTest.get_email());
	}

	@Test
	void testSetAddress() {
		userToTest.set_address("Burnaby, BC");
		assertEquals("Burnaby, BC", userToTest.get_address());
	}

	@Test
	void testSetCardNumber() {
		userToTest.set_cardNumber("99999");
		assertNotNull(userToTest.get_cardNumber());
		
	}
}
