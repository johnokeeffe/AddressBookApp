import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

//this is the user home page
public class UserHome {

	JFrame frame;
	private User user = new User();
	private JTable table;
	private JTextField txtFind;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserHome window = new UserHome();
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
	public UserHome() {
		initialize();
	}
	
	public UserHome(String username) {
		initialize();
		user.setUsername(username);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 923, 343);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Home");

		
		JButton btnAddContact = new JButton("Add Contact");
		btnAddContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to the add contact window
				frame.dispose();
				AddContact addContact = new AddContact(user.getUsername());
				addContact.frame.setVisible(true);
			}
		});
		btnAddContact.setBounds(26, 26, 116, 23);
		frame.getContentPane().add(btnAddContact);
		
		JButton btnEditContact = new JButton("Edit Contact");
		btnEditContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedRow = table.getSelectedRow();
				//ensure the user has selected a contact to be edited
				if(selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Please select a contact first!", 
							"Error", JOptionPane.WARNING_MESSAGE);

				}else {
					//get the details of the selected contact
					DefaultTableModel model = (DefaultTableModel)table.getModel();

					String name = (String) model.getValueAt(selectedRow, 0);
					String address1 = (String) model.getValueAt(selectedRow, 1);
					String address2 = (String) model.getValueAt(selectedRow, 2);
					String address3 = (String) model.getValueAt(selectedRow, 3);
					String address4 = (String) model.getValueAt(selectedRow, 4);
					String email = (String) model.getValueAt(selectedRow, 5);
					String strTelno = (String) model.getValueAt(selectedRow, 6);
					
					int telno = Integer.parseInt(strTelno);
					
					AddressBook addBook = new AddressBook(user.getUsername(), name, 
							address1, address2, address3, address4, email, telno);
					
					//go to the edit contact window
					frame.dispose();
					EditContact editContact = new EditContact(addBook);
					editContact.frame.setVisible(true);
				}
							}
		});
		btnEditContact.setBounds(178, 26, 105, 23);
		frame.getContentPane().add(btnEditContact);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 74, 871, 215);
		frame.getContentPane().add(scrollPane);
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Name");
		model.addColumn("Address1");
		model.addColumn("Address2");
		model.addColumn("Address3");
		model.addColumn("Address4");
		model.addColumn("Email");
		model.addColumn("Telno");
		table = new JTable(model);
		
		scrollPane.setViewportView(table);
		
		JButton btnViewAll = new JButton("View All");
		btnViewAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//retrieve all contacts for the current user
					
					SQLiteDB db = new SQLiteDB();
					ResultSet rs = db.viewAll(user.getUsername());

					//clear the table
					model.setRowCount(0);
					//populate the table
					while(rs.next()) {
						String contactname = rs.getString("CONTACTNAME");
						String address1 = rs.getString("ADDRESS1");
						String address2 = rs.getString("ADDRESS2");
						String address3 = rs.getString("ADDRESS3");
						String address4 = rs.getString("ADDRESS4");
						String email = rs.getString("EMAIL");
						String telno = rs.getString("TELNO");

						model.addRow(new Object[] {contactname, address1, address2, 
								address3, address4, email, telno});
						
					}
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
				
			}
		});
		btnViewAll.setBounds(330, 26, 99, 23);
		frame.getContentPane().add(btnViewAll);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//log out
				frame.dispose();
				LoginForm loginForm = new LoginForm();
				loginForm.frame.setVisible(true);
			}
		});
		btnLogOut.setBounds(808, 26, 89, 23);
		frame.getContentPane().add(btnLogOut);
		
		txtFind = new JTextField();

		txtFind.setBounds(537, 27, 166, 20);
		frame.getContentPane().add(txtFind);
		txtFind.setColumns(10);
		
		JLabel lblFindContact = new JLabel("Find Contact:");
		lblFindContact.setBounds(453, 30, 89, 14);
		frame.getContentPane().add(lblFindContact);
		
		JButton btnFind = new JButton("Go");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//find a contact
				try {
					String findName = txtFind.getText();
	
					SQLiteDB db = new SQLiteDB();
					ResultSet rs = db.findContact(user.getUsername(), findName);
					//db.closeConnection();

					model.setRowCount(0);
					while(rs.next()) {
						String contactname = rs.getString("CONTACTNAME");
						String address1 = rs.getString("ADDRESS1");
						String address2 = rs.getString("ADDRESS2");
						String address3 = rs.getString("ADDRESS3");
						String address4 = rs.getString("ADDRESS4");
						String email = rs.getString("EMAIL");
						String telno = rs.getString("TELNO");

						model.addRow(new Object[] {contactname, address1, address2, address3, address4, email, telno});
						
					}
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		btnFind.setBounds(713, 26, 60, 23);
		frame.getContentPane().add(btnFind);
		
		
	}
}
