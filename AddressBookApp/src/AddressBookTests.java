import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class AddressBookTests {

	SQLiteDB db = new SQLiteDB();
	User user = new User();
	AddressBook addressBook = new AddressBook("JOK", "DANNY", 
									"1 GREEN PARK", "GREEN ROAD", "GREEN TOWN",
									"GREEN COUNTY", "D@GREEN.IE", 7822772);
	
	@Test
	void validLoginTest() {
		//correct username and password
		//JOK has already been registered with password 'JOK'
		
		user.setUsername("JOK");
		user.setPassword("JOK");
		
		assertTrue(db.login(user));
		
	}
	
	@Test
	void invalidLoginTest() {
		//incorrect username and password
		user.setUsername("hjwdhdw");
		user.setPassword("kskjs");
		
		assertFalse(db.login(user));
		
	}
	
	@Test
	void usernameAvailableTest() {
		//available username
		assertTrue(db.usernameAvailable("unusedName"));
		
	}
	
	@Test
	void usernameUnavailableTest() {
		//unavailable username
		assertFalse(db.usernameAvailable("JOK"));
		
	}
	
	
	@Test
	void registerUserTest() {
		//register a new user test
		user.setUsername("NewUser");
		user.setPassword("pass");
		assertTrue(db.registerUser(user));
		
	}
	
	
	@Test
	void addContactTest() {
		//add a new contact test
		
		assertTrue(db.addContact(addressBook));
		
	}
	
	@Test
	void editContactTest() {
		//add a new contact test
		AddressBook newAddressBook = new AddressBook("JOK", "UPDATED NAME", 
				"1 GREEN PARK", "GREEN ROAD", "GREEN TOWN",
				"GREEN COUNTY", "D@GREEN.IE", 7822772);
		assertTrue(db.editContact(addressBook, newAddressBook));
		
	}
	
	
	@Test
	void viewAllTest() throws SQLException {
		//view all test
		ResultSet rs = db.viewAll("TM");
		int counter = 0;
		while(rs.next()) {
			counter++;
		}
		//assert that all 3 of TM's contacts are retrieved
		assertEquals(3, counter);
		
	}
	
	@Test
	void findTest() throws SQLException {
		//view all test
		ResultSet rs = db.findContact("TM", "J");
		int counter = 0;
		while(rs.next()) {
			counter++;
		}
		//assert that both of TM's contacts that begin with J are retrieved
		assertEquals(2, counter);
		
	}

}
