package librarysystem.authentication;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.LoginException;
import business.SystemController;

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
		labelID.setBounds(69, 90, 30, 13);
		add(labelID);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(109, 87, 96, 19);
		add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel labelPassword = new JLabel("Password : ");
		labelPassword.setBounds(262, 90, 70, 13);
		add(labelPassword);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(327, 87, 96, 19);
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
		});
	}
	
	private void addLoginButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			SystemController sysCtrl= new SystemController();
			try {
				sysCtrl.login(textFieldID.getText(),textFieldID.getText());
			} catch (LoginException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		});
	}
}
