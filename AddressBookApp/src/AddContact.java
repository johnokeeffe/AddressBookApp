import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddContact {

	JFrame frame;
	private String username;
	private JTextField txtName;
	private JTextField txtTelno;
	private JTextField txtAddress1;
	private JTextField txtAddress2;
	private JTextField txtAddress3;
	private JTextField txtAddress4;
	private JTextField txtEmail;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddContact window = new AddContact();
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
	public AddContact() {
		initialize();
	}
	
	public AddContact(String user) {
		initialize();
		username = user;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 347, 369);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtName = new JTextField();
		txtName.setBounds(147, 34, 144, 20);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtTelno = new JTextField();
		txtTelno.setBounds(147, 65, 144, 20);
		frame.getContentPane().add(txtTelno);
		txtTelno.setColumns(10);
		
		txtAddress1 = new JTextField();
		txtAddress1.setColumns(10);
		txtAddress1.setBounds(147, 96, 144, 20);
		frame.getContentPane().add(txtAddress1);
		
		txtAddress2 = new JTextField();
		txtAddress2.setColumns(10);
		txtAddress2.setBounds(147, 130, 144, 20);
		frame.getContentPane().add(txtAddress2);
		
		txtAddress3 = new JTextField();
		txtAddress3.setColumns(10);
		txtAddress3.setBounds(147, 162, 144, 20);
		frame.getContentPane().add(txtAddress3);
		
		txtAddress4 = new JTextField();
		txtAddress4.setColumns(10);
		txtAddress4.setBounds(147, 192, 144, 20);
		frame.getContentPane().add(txtAddress4);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(35, 37, 102, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblTelno = new JLabel("Telno");
		lblTelno.setBounds(35, 68, 102, 14);
		frame.getContentPane().add(lblTelno);
		
		JLabel lblAddressLine = new JLabel("Address Line 1");
		lblAddressLine.setBounds(35, 99, 102, 14);
		frame.getContentPane().add(lblAddressLine);
		
		JLabel lblAddressLine_1 = new JLabel("Address Line 2");
		lblAddressLine_1.setBounds(35, 133, 102, 14);
		frame.getContentPane().add(lblAddressLine_1);
		
		JLabel lblAddressLine_2 = new JLabel("Address Line 3");
		lblAddressLine_2.setBounds(35, 165, 102, 14);
		frame.getContentPane().add(lblAddressLine_2);
		
		JLabel lblAddressLine_3 = new JLabel("Address Line 4");
		lblAddressLine_3.setBounds(35, 195, 102, 14);
		frame.getContentPane().add(lblAddressLine_3);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(35, 231, 102, 14);
		frame.getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(147, 228, 144, 20);
		frame.getContentPane().add(txtEmail);
		
		JButton btnAddContact = new JButton("Add Contact");
		btnAddContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int telno = Integer.parseInt(txtTelno.getText());
				AddressBook addBook = new AddressBook(
						username, txtName.getText(), 
						txtAddress1.getText(), txtAddress2.getText(),
						txtAddress3.getText(), txtAddress4.getText(),
						txtEmail.getText(), telno);
				
				SQLiteDB db = new SQLiteDB();
				db.addContact(addBook);

				frame.dispose();
				UserHome userHome = new UserHome(username);
				userHome.frame.setVisible(true);
			}
		});
		btnAddContact.setBounds(79, 256, 158, 23);
		frame.getContentPane().add(btnAddContact);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UserHome userHome = new UserHome(username);
				userHome.frame.setVisible(true);
			}
		});
		btnCancel.setBounds(79, 290, 158, 23);
		frame.getContentPane().add(btnCancel);
	}

}
