package librarysystem.authentication;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import business.LoginException;
import business.SystemController;
import dataaccess.Auth;
import librarysystem.mainUI.MainUI;
import java.awt.Font;

public class LoginWindow extends JPanel{
	private static final long serialVersionUID = 1L;

	public static final LoginWindow INSTANCE = new LoginWindow();
	
	private boolean isInitialized = false;
	
	private JTextField textFieldPassword;
	private JTextField textFieldID;
	private JButton btnLogin;
	private JLabel lblWelcomeToMiu;

	
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	public LoginWindow() {init();
	
	}
	
	public void init() {
		setBounds(143, 0, 573, 367);
		setLayout(null);
		
		JLabel labelID = new JLabel("ID : ");
		labelID.setHorizontalAlignment(SwingConstants.TRAILING);
		labelID.setBounds(89, 112, 103, 13);
		add(labelID);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(199, 101, 174, 36);
		add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel labelPassword = new JLabel("Password : ");
		labelPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		labelPassword.setBounds(89, 160, 103, 13);
		add(labelPassword);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(199, 149, 174, 36);
		add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(199, 210, 174, 36);
		addLoginButtonListener(btnLogin);
		add(btnLogin);
		
		lblWelcomeToMiu = new JLabel("Welcome to MIU Library System");
		lblWelcomeToMiu.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblWelcomeToMiu.setBounds(24, 23, 294, 16);
		add(lblWelcomeToMiu);
		
		setLoggedInState(false);
	}
	
	private void addLoginButtonListener(JButton button) {
		button.addActionListener(evt -> {
			
			if (SystemController.getCurrentAuth() == null) {
				// Login button action
				SystemController sysCtrl= new SystemController();
				try {
					sysCtrl.login(textFieldID.getText(),textFieldPassword.getText());
					String status="";
					
					Auth currentAuth = SystemController.getCurrentAuth();
					if (currentAuth == Auth.ADMIN) {
						status = "Administration";
					} else if (currentAuth == Auth.LIBRARIAN) {
						status = "Librarian";
					} else {
						status = "both Admin and Librarian";
					}
	
					MainUI.INSTANCE.setMessage("You're logged in as " + status);
					setLoggedInState(true);
					
				} catch (LoginException e) {
					MainUI.INSTANCE.setMessage(e.getMessage());
				}
				
			} else {
				// Logout button action 
				SystemController sysCtrl = new SystemController();
				sysCtrl.logout();
				MainUI.INSTANCE.setMessage("Logout successful");
				setLoggedInState(false);
			}
		});
	}
	
	private void setLoggedInState(boolean loggedIn) { 
		textFieldID.setEnabled(!loggedIn);
		textFieldPassword.setEnabled(!loggedIn);
		
		if (loggedIn) {
			btnLogin.setText("Log Out");
		} else {
			btnLogin.setText("Log In");
		}
	}
}
