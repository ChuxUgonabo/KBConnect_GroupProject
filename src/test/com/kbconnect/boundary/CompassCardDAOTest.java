/**
 * 
 */
package test.com.kbconnect.boundary;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import com.kbconnect.boundary.CompassCardDAO;
import com.kbconnect.entity.CompassCard;

/**
 * @author William
 *
 */
class CompassCardDAOTest {

	/**
	 * @throws java.lang.Exception
	 */
	// define the variables for testing
	CompassCard compassCardToTest;
	CompassCardDAO compassCardDAOToTest;
	ArrayList<CompassCard> OriginalListToTest;

	@BeforeEach
	void setUp() throws Exception {
		// instantiate a new compassCard object
		compassCardToTest = new CompassCard();
		// set the values to attributes
		compassCardToTest.set_cardNumber("333000333");
		compassCardToTest.set_cvn("210");
		compassCardToTest.set_isActive(true);
		compassCardToTest.set_loadedBalance(100.00);

		// instantiate compassCardDAO
		compassCardDAOToTest = new CompassCardDAO();

	}

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

	/**
	 * Testing the getAllCompassCards method
	 */
	@Test
	void testGetAllCards() {

		OriginalListToTest = new ArrayList<CompassCard>();
		OriginalListToTest = compassCardDAOToTest.getAllCards();
		// insert a new compassCard into db and assertTrue
		compassCardDAOToTest.createCard(compassCardToTest);
		// call getAll method to get current list
		ArrayList<CompassCard> currList = new ArrayList<CompassCard>();
		currList = compassCardDAOToTest.getAllCards();
		// compare the size of different lists
		assertEquals(OriginalListToTest.size() + 1, currList.size());

	}

	/**
	 * Testing the deleteCard method
	 */
	@Test
	void testDeleteCompassCard() {
		CompassCard curr = new CompassCard();
		curr = compassCardDAOToTest.getCompassCard("333000333");
		if (curr == null) {
			assertEquals(null, curr);
		} else {
			assertTrue(compassCardDAOToTest.deleteCard(curr));
		}

	}

	/**
	 * Testing the updateCard method
	 */
	@Test
	void testUpdateCard() {

		CompassCard curr = new CompassCard();
		curr.set_cardNumber("00000000");
		String cvn = BCrypt.hashpw("000", BCrypt.gensalt());
		curr.set_cvn(cvn);
		compassCardDAOToTest.createCard(curr);

		CompassCard n = new CompassCard();
		n = compassCardDAOToTest.getCompassCard("00000000");
		curr.set_cardNumber("11111111");

		assertTrue(compassCardDAOToTest.updateCard(n));

		curr = compassCardDAOToTest.getCompassCard("99999");
		compassCardDAOToTest.deleteCard(curr);

	};

}
