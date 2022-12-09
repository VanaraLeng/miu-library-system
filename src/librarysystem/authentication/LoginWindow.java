package librarysystem.authentication;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.LoginException;
import business.SystemController;
import dataaccess.Auth;
import librarysystem.mainUI.MainUI;

public class LoginWindow extends JPanel{
	private static final long serialVersionUID = 1L;

	public static final LoginWindow INSTANCE = new LoginWindow();
	
	private boolean isInitialized = false;
	

	
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	private JTextField textFieldPassword;
	private JTextField textFieldID;
	public LoginWindow() {init(); 
	}
	public void init() {
		setBounds(143, 0, 503, 359);
		setLayout(null);
		
		JLabel labelID = new JLabel("ID : ");
		labelID.setBounds(69, 98, 30, 13);
		add(labelID);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(109, 87, 131, 36);
		add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel labelPassword = new JLabel("Password : ");
		labelPassword.setBounds(262, 98, 70, 13);
		add(labelPassword);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(342, 87, 131, 36);
		add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(182, 174, 85, 21);
		addLoginButtonListener(btnLogin);
		add(btnLogin);
		
		JButton btnLogout = new JButton("logout");
		btnLogout.setBounds(182, 212, 85, 21);
		addLogoutButtonListener(btnLogout);
		add(btnLogout);
	}
	private void addLogoutButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			SystemController sysCtrl= new SystemController();
			sysCtrl.logout();
			MainUI.INSTANCE.setMessage("Logout successful");
		});
	}
	
	private void addLoginButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			SystemController sysCtrl= new SystemController();
			try {
				sysCtrl.login(textFieldID.getText(),textFieldPassword.getText());
				String status="";
				if(sysCtrl.currentAuth==Auth.ADMIN)status="Admin";
				if(sysCtrl.currentAuth==Auth.LIBRARIAN)status="Librarian";
				if(sysCtrl.currentAuth==Auth.BOTH)status="both Admin and Librarian";

				MainUI.INSTANCE.setMessage("   You're logged in as "+status);
			} catch (LoginException e) {
				// TODO Auto-generated catch block
				MainUI.INSTANCE.setMessage(e.getMessage());
			}
				
		});
	}
}
