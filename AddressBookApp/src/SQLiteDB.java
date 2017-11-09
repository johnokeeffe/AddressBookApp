import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//all SQL statements are found here
public class SQLiteDB {
	
	Connection c = null;
	Statement stmt = null;
	
	SQLiteDB(){
		//try connect to db
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:AddressBookDB.sqlite");
			System.out.println("Connection Successful!");
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void listContacts() {
		try {

			this.stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");
			while(rs.next()) {
				String username = rs.getString("USERNAME");
				String password = rs.getString("PASSWORD");
				System.out.println(username + " " + password);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void closeConnection() {
		try {
			c.close();
		}catch(Exception e) {
			// error
		}
	}
	
	public void executeQuery(String query) {
		try {
			this.stmt = c.createStatement();
			stmt.executeQuery(query);
			
		}catch(Exception e) {
			//error
		}
	}
	
	public boolean login(String user, String pass) {
		try {
			String query = "SELECT * FROM USERS WHERE USERNAME='" + user +
					"' AND PASSWORD = '" + pass + "'";
			this.stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			String username = rs.getString("USERNAME");
			String password = rs.getString("PASSWORD");
			int counter = 0;
			while(rs.next()) {
				counter++;
			}
			System.out.println(counter);
			
			if((user.equals(username)) && (pass.equals(password))) {
				return true;
			}else {
				return false;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
			
		}
	}
	
	public boolean usernameAvailable(String user) {
		try {
			String query = "SELECT * FROM USERS WHERE USERNAME='" + user + "'";
			this.stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			int counter = 0;
			while(rs.next()) {
				counter++;
			}
			System.out.println(counter);
			
			if(counter > 0) {
				System.out.println("username taken!!");
				return false;
			}else {
				System.out.println("username available!!");
				return true;
			}
		
		}catch(Exception e) {
			e.printStackTrace();
			return false;
			
		}
		
		
	}
	
	public void registerUser(String user, String pass) {
		String query = "INSERT INTO USERS(USERNAME, PASSWORD) "
					+ "VALUES ('" + user + "','" + pass + "')";
		executeQuery(query);
		
		
	}
	public ResultSet getResultSet(String query) {
		try {
			this.stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public void addContact(AddressBook addBook) {
		String query = "INSERT INTO CONTACTS(USERNAME,CONTACTNAME,ADDRESS1,"
				+ "ADDRESS2,ADDRESS3,ADDRESS4,EMAIL,TELNO) "
				+ "VALUES ('" + addBook.username + "','" + addBook.contactName + 
				"','" + addBook.address1 + "','" + addBook.address2 +
				"','" + addBook.address3 + "','" + addBook.address4 +
				"','" + addBook.email + "','" + addBook.telno +
				"')";
		executeQuery(query);
		System.out.println("contact added");

	}
	public void editContact(AddressBook oldAddBook, AddressBook newAddBook) {
		String query = "UPDATE CONTACTS SET "
				+ "USERNAME = '" + newAddBook.username + "',CONTACTNAME = '" + newAddBook.contactName
				+ "', ADDRESS1 = '" + newAddBook.address1 + "', ADDRESS2 = '" + newAddBook.address2
				+ "', ADDRESS3 = '" + newAddBook.address3 + "', ADDRESS4 = '" + newAddBook.address4
				+ "', EMAIL = '" + newAddBook.email + "', TELNO = '" + newAddBook.telno
				+ "' WHERE USERNAME = '" + oldAddBook.username + "' AND CONTACTNAME = '" + oldAddBook.contactName
				+ "' AND ADDRESS1 = '" + oldAddBook.address1 + "' AND ADDRESS2 = '" + oldAddBook.address2
				+ "' AND ADDRESS3 = '" + oldAddBook.address3 + "' AND ADDRESS4 = '" + oldAddBook.address4
				+ "' AND EMAIL = '" + oldAddBook.email + "' AND TELNO = '" + oldAddBook.telno + "'";
		executeQuery(query);
	}

}
