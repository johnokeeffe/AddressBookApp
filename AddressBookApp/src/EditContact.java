import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//users can edit a contact at this page
public class EditContact {

	JFrame frame;
	private AddressBook oldAddressBook = new AddressBook();
	private User user = new User();
	
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
	
	public EditContact(AddressBook addBook) {
		initialize();
		user.setUsername(addBook.getUsername());
		oldAddressBook = addBook;
			
		txtName.setText(addBook.getContactName());
		txtAddress1.setText(addBook.getAddress1());
		txtAddress2.setText(addBook.getAddress2());
		txtAddress3.setText(addBook.getAddress3());
		txtAddress4.setText(addBook.getAddress4());
		txtEmail.setText(addBook.getEmail());
		String strTelno = String.valueOf(addBook.getTelno());
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
		frame.setTitle("Edit Contact");
		
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
				//get the new contact details
				String name = txtName.getText();
				String add1 = txtAddress1.getText();
				String add2 = txtAddress2.getText();
				String add3 = txtAddress3.getText();
				String add4 = txtAddress4.getText();
				String email = txtEmail.getText();
				String strTelno = txtTelno.getText();
				try {
					int telno = Integer.parseInt(strTelno);
					
					AddressBook newAddBook = new AddressBook(user.getUsername(), name, 
							add1, add2, add3, add4, email, telno);
					
					SQLiteDB db = new SQLiteDB();
					boolean success = db.editContact(oldAddressBook, newAddBook);
					if(success) {
						JOptionPane.showMessageDialog(null, "Details updated!", "Success", JOptionPane.WARNING_MESSAGE);
						//return to the user home page
						frame.dispose();
						UserHome userHome = new UserHome(user.getUsername());
						userHome.frame.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Error updating details. Ensure all fields have "
								+ "been entered correctly!", "Error", JOptionPane.WARNING_MESSAGE);
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Phone number may only contain numbers",
							"Error", JOptionPane.WARNING_MESSAGE);
				}
				int telno = Integer.parseInt(strTelno);
				
				AddressBook newAddBook = new AddressBook(user.getUsername(), name, 
						add1, add2, add3, add4, email, telno);
				
				SQLiteDB db = new SQLiteDB();
				boolean success = db.editContact(oldAddressBook, newAddBook);
				if(success) {
					JOptionPane.showMessageDialog(null, "Details updated!", "Success", JOptionPane.WARNING_MESSAGE);
					//return to the user home page
					frame.dispose();
					UserHome userHome = new UserHome(user.getUsername());
					userHome.frame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Error updating details. Ensure all fields have "
							+ "been entered correctly!", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSave.setBounds(54, 233, 158, 23);
		frame.getContentPane().add(btnSave);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//return to the user home page
				frame.dispose();
				UserHome userHome = new UserHome(user.getUsername());
				userHome.frame.setVisible(true);
			}
		});
		button_1.setBounds(54, 267, 158, 23);
		frame.getContentPane().add(button_1);
	}

}
