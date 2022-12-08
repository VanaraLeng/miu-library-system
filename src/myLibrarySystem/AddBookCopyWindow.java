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
import librarysystem.mainUI.MainUI;

import javax.swing.JTable;

public class AddBookCopyWindow  extends JPanel implements LibWindow {
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
    private AddBookCopyWindow () {    init();	
    	}
    
    public void init() {   
			setLayout(null);

		//	********************This is for the message**********************


			
//			setBounds(147, 10, 499, 349);
			
			JLabel lblNewLabel = new JLabel("Adding a new Book Copy");
			lblNewLabel.setBounds(195, 59, 150, 13);
			add(lblNewLabel);
			
			JLabel labelISBN = new JLabel("ISBN : ");
			labelISBN.setBounds(179, 149, 62, 13);
			add(labelISBN);
			
			textFieldISBN = new JTextField();
			textFieldISBN.setBounds(270, 146, 141, 19);
			add(textFieldISBN);
			textFieldISBN.setColumns(10);
			
			JButton btnAddBookCopy = new JButton("Add a copy");
			btnAddBookCopy.setBounds(211, 216, 115, 21);
			addButtonAddBookCopyListener(btnAddBookCopy);
			add(btnAddBookCopy);
		//	******************************************
		//	********************This is for the login window**********************
		
			
		//	******************************************
			
			setSize(595,442);		
    		isInitialized(true);
//    		pack();
    		//setSize(660, 500);
    		
    	
    }
   
    	
    	private void addButtonAddBookCopyListener(JButton butn) {
    		butn.addActionListener(evt -> {
    			SystemController sysCtrl= new SystemController();
    			try {
					sysCtrl.addBookCopy(textFieldISBN.getText());
					MainUI.INSTANCE.setMessage("     Copy added successfully");
				} catch (LibrarySystemException e) {
					// TODO Auto-generated catch block
					MainUI.INSTANCE.setMessage("     Couldn't add the copy");
					e.printStackTrace();
				}

    		});
    	}
}
