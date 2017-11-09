
public class AddressBook {
	public String username;
	public String contactName;
	public String address1;
	public String address2;
	public String address3;
	public String address4;
	public String email;
	public int telno;
	
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
	
	public AddressBook() {};
	

}
