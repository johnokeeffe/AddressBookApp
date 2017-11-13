import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//users login at this window
public class LoginForm {

	JFrame frame;
	private User user = new User();
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JButton btnNewUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
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
	public LoginForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 299, 244);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Login");
		
		txtUsername = new JTextField();
		txtUsername.setBounds(130, 47, 128, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(130, 92, 128, 20);
		frame.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(26, 50, 79, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(26, 95, 79, 14);
		frame.getContentPane().add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//check that all fields have been entered
				user.setUsername(txtUsername.getText());
				user.setPassword(txtPassword.getText());
				if(user.getUsername().equals("") || user.getPassword().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a username and password!", "Error", JOptionPane.WARNING_MESSAGE);
				}else {
					
					
					
					SQLiteDB db = new SQLiteDB();
					boolean loginSuccess = db.login(user);
					
					if(loginSuccess) {
						db.closeConnection();
						frame.dispose();
						//go to the user home window
						UserHome userHome = new UserHome(user.getUsername());
						userHome.frame.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Incorrect login details!", "Error",
								JOptionPane.WARNING_MESSAGE);
					}
				}
				
				
			}
			
		});
		btnLogin.setBounds(74, 123, 137, 23);
		frame.getContentPane().add(btnLogin);
		
		btnNewUser = new JButton("New User");
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//go to the register user window
				frame.dispose();
				NewUser newUser = new NewUser();
				newUser.frame.setVisible(true);
			}
		});
		btnNewUser.setBounds(74, 157, 137, 23);
		frame.getContentPane().add(btnNewUser);
	}
}
