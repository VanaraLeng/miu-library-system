package myLibrarySystem;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.LoginException;
import business.SystemController;
import librarysystem.LibWindow;

public class AddMemberWindow extends JPanel implements LibWindow {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final AddMemberWindow INSTANCE = new AddMemberWindow();
	
	private boolean isInitialized = false;
	private JTextField textFieldID;
	private JTextField textFieldLastName;
	private JTextField textFieldStreet;
	private JTextField textFieldState;
	private JTextField textFieldFirstName;
	private JTextField textFieldTelephone;
	private JTextField textFieldCity;
	private JTextField textFieldZip;
	

	
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	/* This class is a singleton */
    private AddMemberWindow () {init();
    	}
    
    public void init() {
			setLayout(null);
			
		//	********************This is for the message**********************
			
//			setBounds(142, 0, 504, 359);
//			setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Adding new member");
			lblNewLabel.setBounds(193, 21, 146, 13);
			add(lblNewLabel);
			
			JLabel labelID = new JLabel("ID : ");
			labelID.setBounds(22, 76, 45, 13);
			add(labelID);
			
			JLabel labelLastName = new JLabel("Last Name : ");
			labelLastName.setBounds(22, 131, 81, 13);
			add(labelLastName);
			
			JLabel labelState = new JLabel("State : ");
			labelState.setBounds(22, 234, 45, 13);
			add(labelState);
			
			JLabel labelStreet = new JLabel("Street : ");
			labelStreet.setBounds(22, 188, 45, 13);
			add(labelStreet);
			
			JLabel labelFirstName = new JLabel("First Name : ");
			labelFirstName.setBounds(264, 76, 90, 13);
			add(labelFirstName);
			
			JLabel labelTelephone = new JLabel("Telephone : ");
			labelTelephone.setBounds(264, 131, 90, 13);
			add(labelTelephone);
			
			JLabel labelCity = new JLabel("City : ");
			labelCity.setBounds(264, 188, 75, 13);
			add(labelCity);
			
			JLabel labelZip = new JLabel("Zip : ");
			labelZip.setBounds(264, 234, 75, 13);
			add(labelZip);
			
			textFieldID = new JTextField();
			textFieldID.setBounds(113, 76, 96, 19);
			add(textFieldID);
			textFieldID.setColumns(10);
			
			textFieldLastName = new JTextField();
			textFieldLastName.setBounds(113, 131, 96, 19);
			add(textFieldLastName);
			textFieldLastName.setColumns(10);
			
			textFieldStreet = new JTextField();
			textFieldStreet.setBounds(113, 188, 96, 19);
			add(textFieldStreet);
			textFieldStreet.setColumns(10);
			
			textFieldState = new JTextField();
			textFieldState.setBounds(113, 234, 96, 19);
			add(textFieldState);
			textFieldState.setColumns(10);
			
			textFieldFirstName = new JTextField();
			textFieldFirstName.setBounds(375, 73, 96, 19);
			add(textFieldFirstName);
			textFieldFirstName.setColumns(10);
			
			textFieldTelephone = new JTextField();
			textFieldTelephone.setBounds(375, 128, 96, 19);
			add(textFieldTelephone);
			textFieldTelephone.setColumns(10);
			
			textFieldCity = new JTextField();
			textFieldCity.setBounds(375, 185, 96, 19);
			add(textFieldCity);
			textFieldCity.setColumns(10);
			
			textFieldZip = new JTextField();
			textFieldZip.setBounds(375, 234, 96, 19);
			add(textFieldZip);
			textFieldZip.setColumns(10);
			
			JButton btnAddMember = new JButton("Add Member");
			btnAddMember.setBounds(164, 291, 118, 21);
			addButtonAddMemberListener(btnAddMember);
			add(btnAddMember);
		//	******************************************
		
		
			
		//	******************************************
			
			setSize(508,399);		
    		isInitialized(true);
//    		pack();
    		//setSize(660, 500);
    		
    	
    }
    private void addButtonAddMemberListener(JButton butn) {
		butn.addActionListener(evt -> {
			SystemController sysCtrl= new SystemController();
			sysCtrl.addMember(textFieldID.getText(), textFieldFirstName.getText(), textFieldLastName.getText(),
					 textFieldStreet.getText(), textFieldCity.getText(),
					textFieldState.getText(), textFieldZip.getText(), textFieldTelephone.getText());
			//might require exception handling

		});
	}

}
