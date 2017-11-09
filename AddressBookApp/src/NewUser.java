import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		lblPassword.setBounds(22, 75, 46, 14);
		frame.getContentPane().add(lblPassword);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = txtUsername.getText();
				String password = txtPassword.getText();
				
				//check user name availability
				SQLiteDB db = new SQLiteDB();
				boolean usernameAvailable = db.usernameAvailable(username);
				
				if(usernameAvailable) {
					db.registerUser(username, password);
					System.out.println("Registered Successfully, you can now log in!!");
					frame.dispose();
					LoginForm loginForm = new LoginForm();
					loginForm.frame.setVisible(true);
					
				}else {
					System.out.println("This username already exists!!");
				}
				db.closeConnection();
				
				
			}
			
		});
		btnRegister.setBounds(66, 103, 128, 31);
		frame.getContentPane().add(btnRegister);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				LoginForm loginForm = new LoginForm();
				loginForm.frame.setVisible(true);
			}
		});
		btnCancel.setBounds(66, 147, 128, 31);
		frame.getContentPane().add(btnCancel);
	}
}
