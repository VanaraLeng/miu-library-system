package myLibrarySystem;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.LoginException;
import business.SystemController;
import librarysystem.LibWindow;

public class AddMemberWindow extends JFrame implements LibWindow {
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
			
			JPanel panelAddMember = new JPanel();
			panelAddMember.setBounds(142, 0, 504, 359);
			getContentPane().add(panelAddMember);
			panelAddMember.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Adding new member");
			lblNewLabel.setBounds(193, 21, 106, 13);
			panelAddMember.add(lblNewLabel);
			
			JLabel labelID = new JLabel("ID : ");
			labelID.setBounds(22, 76, 45, 13);
			panelAddMember.add(labelID);
			
			JLabel labelLastName = new JLabel("Last Name");
			labelLastName.setBounds(22, 131, 60, 13);
			panelAddMember.add(labelLastName);
			
			JLabel labelState = new JLabel("State : ");
			labelState.setBounds(22, 234, 45, 13);
			panelAddMember.add(labelState);
			
			JLabel labelStreet = new JLabel("Street : ");
			labelStreet.setBounds(22, 188, 45, 13);
			panelAddMember.add(labelStreet);
			
			JLabel labelFirstName = new JLabel("First Name : ");
			labelFirstName.setBounds(234, 76, 65, 13);
			panelAddMember.add(labelFirstName);
			
			JLabel labelTelephone = new JLabel("Telephone");
			labelTelephone.setBounds(234, 131, 65, 13);
			panelAddMember.add(labelTelephone);
			
			JLabel labelCity = new JLabel("City : ");
			labelCity.setBounds(234, 188, 45, 13);
			panelAddMember.add(labelCity);
			
			JLabel labelZip = new JLabel("Zip : ");
			labelZip.setBounds(234, 234, 45, 13);
			panelAddMember.add(labelZip);
			
			textFieldID = new JTextField();
			textFieldID.setBounds(78, 73, 96, 19);
			panelAddMember.add(textFieldID);
			textFieldID.setColumns(10);
			
			textFieldLastName = new JTextField();
			textFieldLastName.setBounds(78, 128, 96, 19);
			panelAddMember.add(textFieldLastName);
			textFieldLastName.setColumns(10);
			
			textFieldStreet = new JTextField();
			textFieldStreet.setBounds(78, 185, 96, 19);
			panelAddMember.add(textFieldStreet);
			textFieldStreet.setColumns(10);
			
			textFieldState = new JTextField();
			textFieldState.setBounds(78, 231, 96, 19);
			panelAddMember.add(textFieldState);
			textFieldState.setColumns(10);
			
			textFieldFirstName = new JTextField();
			textFieldFirstName.setBounds(309, 73, 96, 19);
			panelAddMember.add(textFieldFirstName);
			textFieldFirstName.setColumns(10);
			
			textFieldTelephone = new JTextField();
			textFieldTelephone.setBounds(309, 128, 96, 19);
			panelAddMember.add(textFieldTelephone);
			textFieldTelephone.setColumns(10);
			
			textFieldCity = new JTextField();
			textFieldCity.setBounds(309, 185, 96, 19);
			panelAddMember.add(textFieldCity);
			textFieldCity.setColumns(10);
			
			textFieldZip = new JTextField();
			textFieldZip.setBounds(309, 234, 96, 19);
			panelAddMember.add(textFieldZip);
			textFieldZip.setColumns(10);
			
			JButton btnAddMember = new JButton("Add Member");
			btnAddMember.setBounds(164, 291, 118, 21);
			addButtonAddMemberListener(btnAddMember);
			panelAddMember.add(btnAddMember);
		//	******************************************
		
		
			
		//	******************************************
			
			setSize(660,500);		
    		isInitialized(true);
//    		pack();
    		//setSize(660, 500);
    		
    	
    }
    private void addButtonAddMemberListener(JButton butn) {
		butn.addActionListener(evt -> {
			SystemController sysCtrl= new SystemController();
			sysCtrl.addMember(textFieldID.getText(), textFieldFirstName.getText(), textFieldLastName.getText(),textFieldTelephone.getText(), textFieldStreet.getText(), textFieldCity.getText(), textFieldState.getText(), textFieldZip.getText());
			//might require exception handling

		});
	}

}
