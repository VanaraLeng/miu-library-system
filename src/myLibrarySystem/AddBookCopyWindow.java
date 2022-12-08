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
    private AddBookCopyWindow () {    	
    	}
    
    public void init() {   

		//	********************This is for the message**********************


			
//			setBounds(147, 10, 499, 349);
			
			JLabel lblNewLabel = new JLabel("Adding a new Book Copy");
			lblNewLabel.setBounds(166, 10, 144, 13);
			add(lblNewLabel);
			
			JLabel labelISBN = new JLabel("ISBN : ");
			labelISBN.setBounds(131, 88, 45, 13);
			add(labelISBN);
			
			textFieldISBN = new JTextField();
			textFieldISBN.setBounds(218, 85, 96, 19);
			add(textFieldISBN);
			textFieldISBN.setColumns(10);
			
			JButton btnAddBookCopy = new JButton("Add");
			btnAddBookCopy.setBounds(166, 213, 85, 21);
			addButtonAddBookCopyListener(btnAddBookCopy);
			add(btnAddBookCopy);
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

