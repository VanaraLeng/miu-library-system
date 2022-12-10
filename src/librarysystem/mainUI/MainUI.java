package librarysystem.mainUI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import librarysystem.Constant;
import librarysystem.authentication.LoginWindow;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;


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
    private MainUI () {
    	setResizable(false); init();
    }
	private JTextField txtFieldMessage = new JTextField();
	private JPanel panel_1;
	private JScrollPane scrollPane_1;
	private JButton btnNewButton;
	public void clear() {
		txtFieldMessage.setText("");
	}
    public void init() {   
    
		    getContentPane().setLayout(null);
		    
			
			panelMenu = (JPanel) new Menu();
			panelMenu.setBounds(6, 145, 174, 408);
			getContentPane().add(panelMenu);
			

			
		//	******************************************
		//	********************This is for the login window**********************
		
			setMainPanel(LoginWindow.INSTANCE);
			
		//	********************This is for the message**********************

			panelMessage = new JPanel();
			panelMessage.setBounds(22, 450, 730, 103);
			panelMessage.setLayout(null);
			
			textFieldMessage = new JTextArea();
			textFieldMessage.setEditable(false);
			textFieldMessage.setBounds(21, 10, 694, 85);
			//panelMessage.add(textFieldMessage);
		//	******************************************
			
			
			JScrollPane scrollPane = new JScrollPane(textFieldMessage);
			scrollPane.setBounds(192, 450, 589, 105);
			scrollPane.setViewportView(textFieldMessage);
			getContentPane().add(scrollPane);
			
			btnNewButton = new JButton("");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setMainPanel(new AboutUsPanel());
				}
			});
			btnNewButton.setIcon(new ImageIcon(MainUI.class.getResource("/librarysystem/assets/education.png")));
			btnNewButton.setBounds(16, 6, 163, 139);
			getContentPane().add(btnNewButton);
			
			setSize(800, 600);		
    		isInitialized(true);
    }
    
    
    public void setMessage(String str) {
    	textFieldMessage.setText("   " + str);
    }
    
    public void setMainPanel(JPanel panel) {
    	if (panel_to_change != null) {
    		panel_to_change.setVisible(false);
    	}
    	
    	panel_to_change= panel;
    	panel_to_change.setBorder(new LineBorder(new Color(0.5f, 0.5f, 0.5f)));
    	panel_to_change.setBounds(192, 12, Constant.CONTENT_WIDTH, Constant.CONTENT_HEIGHT);
		getContentPane().add(panel_to_change);
		panel_to_change.setVisible(true);
    }
}
