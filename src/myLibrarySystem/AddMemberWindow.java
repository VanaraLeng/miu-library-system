package myLibrarySystem;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.Address;
import business.LibraryMember;
import business.LibrarySystemException;
import business.LoginException;
import business.SystemController;
import librarysystem.LibWindow;
import librarysystem.mainUI.MainUI;
import utility.Validator;

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
    private String checkInputValidity() {
//    	all good if returns null otherwise returns the errors
    	String message ="";
		if(!Validator.isFilled(textFieldID.getText())) {message+=" The ID is empty"+ System.lineSeparator() ;}
		if(!Validator.isFilled(textFieldFirstName.getText())) {message+=" The first name is incorrect"+ System.lineSeparator();}
		if(!Validator.isFilled(textFieldLastName.getText())) {message+=" The last name is incorrect"+ System.lineSeparator();}
		if(!Validator.isCorrectPhoneNumber(textFieldTelephone.getText())) {message+=" The telephone is incorrect"+ System.lineSeparator();}
		if(!Validator.isFilled(textFieldStreet.getText())) {message+=" The street is incorrect"+ System.lineSeparator();}
		if(!Validator.isFilled(textFieldCity.getText())) {message+=" The city is incorrect"+ System.lineSeparator();}
		if(!Validator.isFilled(textFieldState.getText())) {message+=" The state is incorrect"+ System.lineSeparator();}
		if(!Validator.isCorrectZipCode(textFieldZip.getText())) {message+=" The Zip Code is incorrect"+ System.lineSeparator();}
		System.out.println(message);
		return message;
    	
    }
    private void addButtonAddMemberListener(JButton butn) {
		butn.addActionListener(evt -> {
			if(checkInputValidity()=="") {
				SystemController sysCtrl= new SystemController();
				Address address= new Address(textFieldStreet.getText(), textFieldCity.getText(),
						textFieldState.getText(), textFieldZip.getText());
				LibraryMember member = new LibraryMember(textFieldID.getText(), textFieldFirstName.getText(), 
						textFieldLastName.getText(), textFieldTelephone.getText(), address);
				try {
					sysCtrl.addMember(member);
				} catch (LibrarySystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MainUI.INSTANCE.setMessage("     Copy added successfully");
			}else {
				MainUI.INSTANCE.setMessage(checkInputValidity());
			}
			//might require exception handling

		});
	}

}
