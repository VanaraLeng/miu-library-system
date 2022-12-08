package librarysystem.mainUI;
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
import librarysystem.authentication.LoginWindow;
import librarysystem.managebook.AddBookPanel;
import myLibrarySystem.Menu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;


public class MainUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final MainUI INSTANCE = new MainUI();
	
	private boolean isInitialized = false;
	

	JPanel panel_to_change;
	JPanel panelMenu;
	JPanel panelMessage;
	JTextArea textFieldMessage;
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	/* This class is a singleton */
    private MainUI () { init();
    }
	private JTextField txtFieldMessage = new JTextField();
	private JPanel panel_1;
	public void clear() {
		txtFieldMessage.setText("");
	}
    public void init() {   
		    getContentPane().setLayout(null);
		    
			
			panelMenu = (JPanel) new Menu();
			panelMenu.setBounds(0, 0, 194, 438);
			getContentPane().add(panelMenu);
		//	********************This is for the message**********************

			panelMessage = new JPanel();
			panelMessage.setBounds(10, 448, 766, 105);
			getContentPane().add(panelMessage);
			panelMessage.setLayout(null);
			
			textFieldMessage = new JTextArea();
			textFieldMessage.setBounds(21, 10, 694, 85);
			panelMessage.add(textFieldMessage);
			
			JScrollBar scrollBar = new JScrollBar();
			scrollBar.setBounds(669, 10, 46, 85);
			scrollBar.setVisible(true);
			panelMessage.add(scrollBar);
		//	******************************************
		//	********************This is for the login window**********************
		
			panel_to_change =  LoginWindow.INSTANCE;
			panel_to_change.setBounds(202, 0, 536, 438);
			getContentPane().add(panel_to_change);
		//	******************************************
			
			setSize(800, 600);		
    		isInitialized(true);
//    		pack();
    		//setSize(660, 500);  
    		
    }
    public void setMessage(String str) {
    	textFieldMessage.setText(str);
    }
    
    public void setMainPanel(JPanel panel) {
    	panel_to_change.setVisible(false);
//    	remove(panel_to_change);
    	panel_to_change= panel;
    	panel_to_change.setBounds(202, 0, 536, 438);
		getContentPane().add(panel_to_change);
		panel_to_change.setVisible(true);
    }
}
