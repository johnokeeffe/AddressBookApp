import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//all database interactions are handled here
public class SQLiteDB {
	
	Connection c = null;
	Statement stmt = null;
	ResultSet rs;
	
	SQLiteDB(){
		//connect to the database
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:AddressBookDB.sqlite");
			this.stmt = c.createStatement();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void closeConnection() {
		//close the db connection when finished
		try {
			c.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean executeQuery(String query) {
		//execute queries that return a result set
		try {
			this.stmt = c.createStatement();
			stmt.executeQuery(query);
			closeConnection();
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean executeUpdate(String query) {
		//execute queries that change the db
		try {
			this.stmt = c.createStatement();
			stmt.executeUpdate(query);
			closeConnection();
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean login(User user) {
		try {
			//check if the login details match a registered user
			String query = "SELECT * FROM USERS WHERE USERNAME='" + user.getUsername() +
					"' AND PASSWORD = '" + user.getPassword() + "'";
			this.stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			

			String username = rs.getString("USERNAME");
			String password = rs.getString("PASSWORD");
			closeConnection();
			if((user.getUsername().equals(username)) && (user.getPassword().equals(password))) {
				return true; //correct login details
			}else {
				return false;
			}
			
		}catch(Exception e) {
			//wrong details entered
			return false;
			
		}
	}
	
	public boolean usernameAvailable(String user) {
		//check the availability of a user name
		try {
			String query = "SELECT * FROM USERS WHERE USERNAME='" + user + "'";
			this.stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			//count the rows returned by the query
			int counter = 0;
			while(rs.next()) {
				counter++;
			}
			System.out.println(counter);
			//if the number of rows are greater than 0 this username is taken
			if(counter > 0) {
				return false;
			}else {
				return true;
			}
		
		}catch(Exception e) {
			e.printStackTrace();
			return false;
			
		}
		
		
	}
	
	public boolean registerUser(User user) {
		//register a new user
		String query = "INSERT INTO USERS(USERNAME, PASSWORD) "
					+ "VALUES ('" + user.getUsername() + "','" 
					+ user.getPassword() + "')";
		boolean success = executeUpdate(query);
		
		return success;
		
	}
	
	public ResultSet getResultSet(String query) {
		//get a result set from the db
		try {
			this.stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addContact(AddressBook addBook) {
		//add a new contact into the database
		String query = "INSERT INTO CONTACTS(USERNAME,CONTACTNAME,ADDRESS1,"
				+ "ADDRESS2,ADDRESS3,ADDRESS4,EMAIL,TELNO) "
				+ "VALUES ('" + addBook.getUsername() + "','" + addBook.getContactName() + 
				"','" + addBook.getAddress1() + "','" + addBook.getAddress2() +
				"','" + addBook.getAddress3() + "','" + addBook.getAddress4() +
				"','" + addBook.getEmail() + "','" + addBook.getTelno() +
				"')";
		boolean success = executeUpdate(query);
		
		return success;
	}
	
	public boolean editContact(AddressBook oldAddBook, AddressBook newAddBook) {
		//edit a contact
		String query = "UPDATE CONTACTS SET "
				+ "USERNAME = '" + newAddBook.getUsername() + "',CONTACTNAME = '" + newAddBook.getContactName()
				+ "', ADDRESS1 = '" + newAddBook.getAddress1() + "', ADDRESS2 = '" + newAddBook.getAddress2()
				+ "', ADDRESS3 = '" + newAddBook.getAddress3() + "', ADDRESS4 = '" + newAddBook.getAddress4()
				+ "', EMAIL = '" + newAddBook.getEmail() + "', TELNO = '" + newAddBook.getTelno()
				+ "' WHERE USERNAME = '" + oldAddBook.getUsername() + "' AND CONTACTNAME = '" + oldAddBook.getContactName()
				+ "' AND ADDRESS1 = '" + oldAddBook.getAddress1() + "' AND ADDRESS2 = '" + oldAddBook.getAddress2()
				+ "' AND ADDRESS3 = '" + oldAddBook.getAddress3() + "' AND ADDRESS4 = '" + oldAddBook.getAddress4()
				+ "' AND EMAIL = '" + oldAddBook.getEmail() + "' AND TELNO = '" + oldAddBook.getTelno() + "'";
		boolean success = executeUpdate(query);
		return success;
	}
	
	public ResultSet viewAll(String username) {
		String query = "SELECT * FROM CONTACTS WHERE USERNAME = '"
				+ username + "' ORDER BY CONTACTNAME";
		ResultSet rs = getResultSet(query);
		return rs;
	}
	
	public ResultSet findContact(String username, String contactName) {
		String query = "SELECT * FROM CONTACTS WHERE USERNAME = '"
						+ username + "' AND CONTACTNAME LIKE '" + contactName +  "%'"
								+ " ORDER BY CONTACTNAME";
		ResultSet rs = getResultSet(query);
		return rs;
	}
	

}
