package librarysystem.authentication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import librarysystem.LibWindow;
import librarysystem.LibrarySystem;
import librarysystem.Util;
import myLibrarySystem.Menu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MyLoginWindow extends JFrame implements LibWindow {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final MyLoginWindow INSTANCE = new MyLoginWindow();
	
	private boolean isInitialized = false;
	

	
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	private JTextField textFieldPassword;
	private JTextField textFieldID;
	/* This class is a singleton */
    private MyLoginWindow () { setSize(660,500);	 init(); setVisible(true);
    	
    	}
    
    public void init() {   
		    getContentPane().setLayout(null);
			
			JPanel panelMenu = (JPanel) new Menu();
			getContentPane().add(panelMenu);
		//	********************This is for the message**********************

			JPanel panelMessage = new JPanel();
			panelMessage.setBounds(0, 358, 646, 105);
			getContentPane().add(panelMessage);
			panelMessage.setLayout(null);
			
			JLabel labelMessage = new JLabel("");
			labelMessage.setBounds(109, 40, 378, 13);
			panelMessage.add(labelMessage);
		//	******************************************
		//	********************This is for the login window**********************
		
			JPanel panelLogin = new JPanel();
			panelLogin.setBounds(143, 0, 503, 359);
			getContentPane().add(panelLogin);
			panelLogin.setLayout(null);
			
			JLabel labelID = new JLabel("ID : ");
			labelID.setBounds(69, 90, 30, 13);
			panelLogin.add(labelID);
			
			textFieldID = new JTextField();
			textFieldID.setBounds(109, 87, 96, 19);
			panelLogin.add(textFieldID);
			textFieldID.setColumns(10);
			
			JLabel labelPassword = new JLabel("Password : ");
			labelPassword.setBounds(262, 90, 70, 13);
			panelLogin.add(labelPassword);
			
			textFieldPassword = new JTextField();
			textFieldPassword.setBounds(327, 87, 96, 19);
			panelLogin.add(textFieldPassword);
			textFieldPassword.setColumns(10);
			
			JButton btnLogin = new JButton("Login");
			btnLogin.setBounds(182, 174, 85, 21);
			addLoginButtonListener(btnLogin);
			panelLogin.add(btnLogin);
			
			JButton btnLogout = new JButton("logout");
			btnLogout.setBounds(182, 212, 85, 21);
			addLogoutButtonListener(btnLogout);
			panelLogin.add(btnLogout);
		//	******************************************
			
			setSize(660,500);		
    		isInitialized(true);
//    		pack();
    		//setSize(660, 500);
    		
    	
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
