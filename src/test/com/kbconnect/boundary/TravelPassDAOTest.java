package test.com.kbconnect.boundary;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.kbconnect.boundary.TravelPassDAO;
import com.kbconnect.entity.TravelPass;

class TravelPassDAOTest {
	TravelPass travelPassToTest;
	TravelPassDAO travelPassDAOToTest;
	ArrayList<TravelPass> listToTest;

	@BeforeEach
	void setUp() throws Exception {

		// instantiate a new TravelPass
		travelPassToTest = new TravelPass();
		// set values to attributes
		travelPassToTest.set_passDuration("daily");
		travelPassToTest.set_passType("adult");
		travelPassToTest.set_price(0);

		travelPassDAOToTest = new TravelPassDAO();

	}

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

	/**
	 * testing for get list of all passes in travelPassDAO
	 */
	@Test
	void testGetAllPass() {
		listToTest = travelPassDAOToTest.getAllPasses();

		travelPassDAOToTest.createPass(travelPassToTest);
		ArrayList<TravelPass> currList = new ArrayList<TravelPass>();
		currList= travelPassDAOToTest.getAllPasses();
		assertEquals(listToTest.size() + 1, currList.size());

		TravelPass curr = new TravelPass();
		curr = currList.get(currList.size() - 1);
        travelPassDAOToTest.deletePass(curr);

	}

	/**
	 * testing for get onePass function in travelPassDAO
	 */

	@Test
	void testGetOnePass() {
		travelPassDAOToTest.createPass(travelPassToTest);
		ArrayList<TravelPass> currList = new ArrayList<TravelPass>();
		currList= travelPassDAOToTest.getAllPasses();
		TravelPass curr = new TravelPass();
		curr = currList.get(currList.size() - 1);
		
        assertEquals(curr.get_passDuration(), travelPassDAOToTest.getPass(curr.get_id()).get_passDuration());

	}

	/**
	 * testing for update method in TravelPassDAO
	 */
	@Test
	void testUpdatePass() {
		
		travelPassDAOToTest.createPass(travelPassToTest);
		
		ArrayList<TravelPass> currList = new ArrayList<TravelPass>();
		currList= travelPassDAOToTest.getAllPasses();
		TravelPass curr = new TravelPass();
		curr = currList.get(currList.size() - 1);
		curr.set_passDuration("year");
		assertTrue(travelPassDAOToTest.updatePass(curr));
		
		travelPassDAOToTest.deletePass(curr);

	}

	/**
	 * testing for delete and create functions at travelPassDAO
	 */
	@Test
	void testDeletePass() {
		assertTrue(travelPassDAOToTest.createPass(travelPassToTest));
		listToTest = travelPassDAOToTest.getAllPasses();

		TravelPass curr = new TravelPass();
		curr = listToTest.get(listToTest.size() - 1);

		assertTrue(travelPassDAOToTest.deletePass(curr));

	}

}
