//AddressBook objects are essentially contacts
//each contact has a name, address, email and telephone number
//each contact is associated with one user

public class AddressBook {
	private String username;
	private String contactName;
	private String address1;
	private String address2;
	private String address3;
	private String address4;
	private String email;
	private int telno;
	
	public AddressBook(String user, String contact, String add1, String add2, String add3, String add4, String emailAdd, int phone) {
		this.username = user;
		this.contactName = contact;
		this.address1 = add1;
		this.address2 = add2;
		this.address3 = add3;
		this.address4 = add4;
		this.email = emailAdd;
		this.telno = phone;
	};
	
	public AddressBook() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress4() {
		return address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelno() {
		return telno;
	}

	public void setTelno(int telno) {
		this.telno = telno;
	};
	

}
