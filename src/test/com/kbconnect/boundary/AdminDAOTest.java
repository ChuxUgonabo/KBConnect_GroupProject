
package test.com.kbconnect.boundary;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kbconnect.boundary.AdminDAO;
import com.kbconnect.entity.Admin;

/**
 * Create JUint test case for testing the AdminDAO.JAVA
 * 
 * @author William
 *
 */
class AdminDAOTest {

	/**
	 * @throws java.lang.Exception
	 */
	Admin adminToTest;
	AdminDAO adminDAOToTest;
	ArrayList<Admin> listToTest;
	int previousSize;

	@BeforeEach
	void setUp() throws Exception {

		adminDAOToTest = new AdminDAO();
		listToTest = adminDAOToTest.getAllUsers();
		previousSize = listToTest.size();

		adminToTest = new Admin();
		adminToTest.set_fullName("Mr key_test");
		adminToTest.set_username("Mr KEY_TEST");
		adminToTest.set_password("ForTesting3275");
		adminToTest.set_email("testing@gmail.com");
		adminToTest.set_isAdmin(true);

	}

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

	/**
	 * Testing the getAllUsers and createUser methods
	 */
	@Test
	void testGetAllUsers() {
		assertTrue(adminDAOToTest.createUser(adminToTest));
		ArrayList<Admin> currList = new ArrayList<Admin>();
		currList = adminDAOToTest.getAllUsers();
		// after processing above method of create a new Admin ,
		// the list should be longer 1 then previous
		assertEquals(previousSize + 1, currList.size());

	}

	@Test
	void testGetUserByID() {
		Admin curr = new Admin();
		curr = listToTest.get(0);

		int currId = curr.get_id();
		String username = curr.get_username();

		assertEquals(username, adminDAOToTest.getUser(currId).get_username());

	};

	@Test
	void testUpdateUser() {

		Admin curr = new Admin();
		curr = adminDAOToTest.getUser("Mr KEY_TEST");
		curr.set_address("updated address");
		assertTrue(adminDAOToTest.updateUser(curr));

	};

	/**
	 * Testing for getUserByName and deleteUser methods
	 */
	@Test
	void testDeleteUser() {

		Admin curr = new Admin();
		curr = adminDAOToTest.getUser("Mr KEY_TEST");
		adminDAOToTest.deleteUser(curr);

		ArrayList<Admin> currList = new ArrayList<Admin>();
		currList = adminDAOToTest.getAllUsers();
		// After processing delete method so current list should be less 1 than before
		assertEquals(previousSize - 1, currList.size());

	};

}
