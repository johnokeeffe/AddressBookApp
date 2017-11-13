import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//A new user can register at this window
public class NewUser {

	JFrame frame;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUser window = new NewUser();
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
	public NewUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 273);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Register");

		
		txtUsername = new JTextField();
		txtUsername.setBounds(118, 30, 144, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(118, 72, 144, 20);
		frame.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(22, 33, 63, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(22, 75, 86, 14);
		frame.getContentPane().add(lblPassword);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User user = new User(txtUsername.getText(), txtPassword.getText());
				
				
				//check user name availability
				SQLiteDB db = new SQLiteDB();
				boolean usernameAvailable = db.usernameAvailable(user.getUsername());
				
				if(user.getUsername() == null || user.getPassword() == null) {
					JOptionPane.showMessageDialog(null, "Please enter a username and a "
							+ "password.", "Error", JOptionPane.WARNING_MESSAGE);

				}else if(usernameAvailable) {
					//add the new user to the db
					boolean success = db.registerUser(user);
					db.closeConnection();
					if(success) {
						//go to the login page
						frame.dispose();
						LoginForm loginForm = new LoginForm();
						loginForm.frame.setVisible(true);
						JOptionPane.showMessageDialog(null, "Registered Successfully! You can now login using your username and password.", "Success", JOptionPane.WARNING_MESSAGE);

					}else {
						JOptionPane.showMessageDialog(null, "Error registering, please check that all fields have been entered correctly.", "Error", JOptionPane.WARNING_MESSAGE);

					}
				}else {
					JOptionPane.showMessageDialog(null, "This username already exists!", "Error", JOptionPane.WARNING_MESSAGE);
				}
				
				
			}
			
		});
		btnRegister.setBounds(66, 103, 128, 31);
		frame.getContentPane().add(btnRegister);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//return to the login page
				frame.dispose();
				LoginForm loginForm = new LoginForm();
				loginForm.frame.setVisible(true);
			}
		});
		btnCancel.setBounds(66, 147, 128, 31);
		frame.getContentPane().add(btnCancel);
	}
}
