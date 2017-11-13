//Users have a username and password which allows them to log in and view their contacts
public class User {

	private String username;
	private String password;
	
	public User(){}
	
	public User(String user, String pass){
		this.username = user;
		this.password = pass;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public void setUsername(String user) {
		this.username = user;
	}
	
}
