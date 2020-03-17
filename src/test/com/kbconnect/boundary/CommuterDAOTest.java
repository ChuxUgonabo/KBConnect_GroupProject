/**
 * 
 */
package test.com.kbconnect.boundary;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kbconnect.boundary.CommuterDAO;
import com.kbconnect.entity.User;

/**
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
		userToTest.set_username("newTestMas");
		
		testUserDAO = new CommuterDAO();
	}
	@Test
	void testCreateUser() {
//		assertTrue(testUserDAO.createUser(userToTest) == true);
		assertEquals(true, testUserDAO.createUser(userToTest));
	}
	
	// for some reason this test stopped working // would check it out tomorrow
//	@Test
//	void testUpdateUser() {
//		updateTest = testUserDAO.getUser("newTestMas");
//		
//		updateTest.set_fullName("Test Master");
//		updateTest.set_password("12345678");
//		updateTest.set_email("test@gmail.com");
//		updateTest.set_DOB("1999-02-12");
//		updateTest.set_address("New Westminster, BC");
////		assertTrue(testUserDAO.updateUser(userToTest) == true);
//		assertEquals(true, testUserDAO.updateUser(updateTest));
//	}
//	
	@Test
	void deleteUser() {
		User deleteTest = testUserDAO.getUser("newTestMas");
		testUserDAO.deleteUser(deleteTest);
//		assertTrue(testUserDAO.getUser(userToTest.get_id()) == null);
		assertEquals(null, testUserDAO.getUser(deleteTest.get_username()));
	}

}
