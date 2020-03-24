
package test.com.kbconnect.boundary;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mindrot.jbcrypt.BCrypt;

import com.kbconnect.boundary.CommuterDAO;
import com.kbconnect.entity.User;

/**
 * Create JUnit test case for testing the adminDAO.JAVA
 * 
 * @author user
 *
 */

class CommuterDAOTest {
	User userToTest;
	CommuterDAO testUserDAO;
	User updateTest;

//	@Test
//	void test() {
//		fail("Not yet implemented");		
//	}
	@BeforeEach
	void setUp() throws Exception {
		// initialize a new user
		userToTest = new User();
		testUserDAO = new CommuterDAO();
	}

	@Test
	void testCreateUser() {
//		assertTrue(testUserDAO.createUser(userToTest) == true);
		userToTest.set_username("newTestMas");
		userToTest.set_DOB("1999-01-01");
		assertEquals(true, testUserDAO.createUser(userToTest));
	}

	@Test
	void testUpdateUser() {

		User n = new User();
		n.set_username("Testinging");
		testUserDAO.createUser(n);

		updateTest = new User();
		updateTest = testUserDAO.getUser("Testinging");
		updateTest.set_fullName("Test");
		String pw = BCrypt.hashpw("12345678", BCrypt.gensalt());
		updateTest.set_password(pw);
		updateTest.set_email("testUpdated---@gmail.com");
		updateTest.set_DOB("1999-02-12");
		updateTest.set_address("New Westminster, BC");
		updateTest.set_cardNumber("12345555");

		assertTrue(testUserDAO.updateUser(updateTest));
		testUserDAO.deleteUser(updateTest);

	}

	@Test
	void testDeleteUser() {
		User deleteTest = testUserDAO.getUser("newTestMas");
		testUserDAO.deleteUser(deleteTest);
//		assertTrue(testUserDAO.getUser(userToTest.get_id()) == null);
		assertEquals(null, testUserDAO.getUser(deleteTest.get_username()));
	}

}
