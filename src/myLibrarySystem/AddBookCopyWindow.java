package myLibrarySystem;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.LibrarySystemException;
import business.LoginException;
import business.SystemController;
import librarysystem.LibWindow;
import javax.swing.JTable;

public class AddBookCopyWindow  extends JFrame implements LibWindow {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final AddBookCopyWindow INSTANCE = new AddBookCopyWindow();
	
	private boolean isInitialized = false;
	

	
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	private JTextField textFieldPassword;
	private JTextField textFieldID;
	private JTextField textFieldISBN;
	/* This class is a singleton */
    private AddBookCopyWindow () { init();
    	
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
			
			JPanel panel = new JPanel();
			panel.setBounds(147, 10, 499, 349);
			getContentPane().add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Adding a new Book Copy");
			lblNewLabel.setBounds(166, 10, 144, 13);
			panel.add(lblNewLabel);
			
			JLabel labelISBN = new JLabel("ISBN : ");
			labelISBN.setBounds(131, 88, 45, 13);
			panel.add(labelISBN);
			
			textFieldISBN = new JTextField();
			textFieldISBN.setBounds(218, 85, 96, 19);
			panel.add(textFieldISBN);
			textFieldISBN.setColumns(10);
			
			JButton btnAddBookCopy = new JButton("Add");
			btnAddBookCopy.setBounds(166, 213, 85, 21);
			addButtonAddBookCopyListener(btnAddBookCopy);
			panel.add(btnAddBookCopy);
		//	******************************************
		//	********************This is for the login window**********************
		
			
		//	******************************************
			
			setSize(660,500);		
    		isInitialized(true);
//    		pack();
    		//setSize(660, 500);
    		
    	
    }
   
    	
    	private void addButtonAddBookCopyListener(JButton butn) {
    		butn.addActionListener(evt -> {
    			SystemController sysCtrl= new SystemController();
    			try {
					sysCtrl.addBookCopy(textFieldISBN.getText());
				} catch (LibrarySystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

    		});
    	}
}

