package librarysystem.mainUI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import librarysystem.authentication.LoginWindow;

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
			panelMenu.setBounds(0, 0, 194, 553);
			getContentPane().add(panelMenu);
		//	********************This is for the message**********************

			panelMessage = new JPanel();
			panelMessage.setBounds(199, 448, 577, 105);
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
