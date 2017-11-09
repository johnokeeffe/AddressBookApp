import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditContact {

	JFrame frame;
	private String username;
	private String contactName;
	private String address1;
	private String address2;
	private String address3;
	private String address4;
	private String oldEmail;
	private int oldTelno;
	
	private JTextField txtName;
	private JTextField txtTelno;
	private JTextField txtAddress1;
	private JTextField txtAddress2;
	private JTextField txtAddress3;
	private JTextField txtAddress4;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditContact window = new EditContact();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EditContact() {
		initialize();
	}
	public EditContact(String user) {
		initialize();
		username = user;
	}
	public EditContact(AddressBook addBook) {
		initialize();
		
		username = addBook.username;
		contactName = addBook.contactName;
		address1 = addBook.address1;
		address2 = addBook.address2;
		address3 = addBook.address3;
		address4 = addBook.address4;
		oldEmail = addBook.email;
		oldTelno = addBook.telno;
				
		txtName.setText(addBook.contactName);
		txtAddress1.setText(addBook.address1);
		txtAddress2.setText(addBook.address2);
		txtAddress3.setText(addBook.address3);
		txtAddress4.setText(addBook.address4);
		txtEmail.setText(addBook.email);
		String strTelno = String.valueOf(addBook.telno);
		txtTelno.setText(strTelno);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 299, 346);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Name");
		label.setBounds(10, 14, 102, 14);
		frame.getContentPane().add(label);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(122, 11, 144, 20);
		frame.getContentPane().add(txtName);
		
		JLabel label_1 = new JLabel("Telno");
		label_1.setBounds(10, 45, 102, 14);
		frame.getContentPane().add(label_1);
		
		txtTelno = new JTextField();
		txtTelno.setColumns(10);
		txtTelno.setBounds(122, 42, 144, 20);
		frame.getContentPane().add(txtTelno);
		
		JLabel label_2 = new JLabel("Address Line 1");
		label_2.setBounds(10, 76, 102, 14);
		frame.getContentPane().add(label_2);
		
		txtAddress1 = new JTextField();
		txtAddress1.setColumns(10);
		txtAddress1.setBounds(122, 73, 144, 20);
		frame.getContentPane().add(txtAddress1);
		
		JLabel label_3 = new JLabel("Address Line 2");
		label_3.setBounds(10, 110, 102, 14);
		frame.getContentPane().add(label_3);
		
		txtAddress2 = new JTextField();
		txtAddress2.setColumns(10);
		txtAddress2.setBounds(122, 107, 144, 20);
		frame.getContentPane().add(txtAddress2);
		
		JLabel label_4 = new JLabel("Address Line 3");
		label_4.setBounds(10, 142, 102, 14);
		frame.getContentPane().add(label_4);
		
		txtAddress3 = new JTextField();
		txtAddress3.setColumns(10);
		txtAddress3.setBounds(122, 139, 144, 20);
		frame.getContentPane().add(txtAddress3);
		
		JLabel label_5 = new JLabel("Address Line 4");
		label_5.setBounds(10, 172, 102, 14);
		frame.getContentPane().add(label_5);
		
		txtAddress4 = new JTextField();
		txtAddress4.setColumns(10);
		txtAddress4.setBounds(122, 169, 144, 20);
		frame.getContentPane().add(txtAddress4);
		
		JLabel label_6 = new JLabel("Email");
		label_6.setBounds(10, 208, 102, 14);
		frame.getContentPane().add(label_6);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(122, 205, 144, 20);
		frame.getContentPane().add(txtEmail);
		
		JButton btnSave = new JButton("Save Changes");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String add1 = txtAddress1.getText();
				String add2 = txtAddress2.getText();
				String add3 = txtAddress3.getText();
				String add4 = txtAddress4.getText();
				String email = txtEmail.getText();
				String strTelno = txtTelno.getText();
				int telno = Integer.parseInt(strTelno);
				AddressBook newAddBook = new AddressBook(username, name, add1, add2, add3, add4, email, telno);
				AddressBook oldAddBook = new AddressBook(username, contactName, address1, address2, address3, address4, oldEmail, oldTelno);
				SQLiteDB db = new SQLiteDB();
				db.editContact(oldAddBook, newAddBook);
				
				frame.dispose();
				UserHome userHome = new UserHome(username);
				userHome.frame.setVisible(true);
			}
		});
		btnSave.setBounds(54, 233, 158, 23);
		frame.getContentPane().add(btnSave);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UserHome userHome = new UserHome(username);
				userHome.frame.setVisible(true);
			}
		});
		button_1.setBounds(54, 267, 158, 23);
		frame.getContentPane().add(button_1);
	}

}
