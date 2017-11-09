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

public class UserHome {

	JFrame frame;
	private JTable table;
	private String username;

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
	
	public UserHome(String user) {
		initialize();
		username = user;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 923, 660);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAddContact = new JButton("Add Contact");
		btnAddContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AddContact addContact = new AddContact(username);
				addContact.frame.setVisible(true);
			}
		});
		btnAddContact.setBounds(211, 26, 116, 23);
		frame.getContentPane().add(btnAddContact);
		
		JButton btnEditContact = new JButton("Edit Contact");
		btnEditContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				String name = (String) model.getValueAt(selectedRow, 0);
				String address1 = (String) model.getValueAt(selectedRow, 1);
				String address2 = (String) model.getValueAt(selectedRow, 2);
				String address3 = (String) model.getValueAt(selectedRow, 3);
				String address4 = (String) model.getValueAt(selectedRow, 4);
				String email = (String) model.getValueAt(selectedRow, 5);
				String strTelno = (String) model.getValueAt(selectedRow, 6);
				
				int telno = Integer.parseInt(strTelno);
				AddressBook addBook = new AddressBook(username, name, address1, address2, address3, address4, email, telno);
				
				frame.dispose();
				EditContact editContact = new EditContact(addBook);
				editContact.frame.setVisible(true);
			
			}
		});
		btnEditContact.setBounds(402, 26, 105, 23);
		frame.getContentPane().add(btnEditContact);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 74, 871, 536);
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
					
					String query = "SELECT * FROM CONTACTS WHERE USERNAME = '"
							+ username + "' ORDER BY CONTACTNAME";
					SQLiteDB db = new SQLiteDB();
					ResultSet rs = db.getResultSet(query);
					
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
		btnViewAll.setBounds(599, 26, 99, 23);
		frame.getContentPane().add(btnViewAll);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				LoginForm loginForm = new LoginForm();
				loginForm.frame.setVisible(true);
			}
		});
		btnLogOut.setBounds(763, 26, 89, 23);
		frame.getContentPane().add(btnLogOut);
		
		
	}
}
