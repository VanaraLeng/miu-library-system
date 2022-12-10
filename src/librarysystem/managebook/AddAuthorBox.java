package librarysystem.managebook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import business.Address;
import business.Author;
import java.awt.GridLayout;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Font;

public class AddAuthorBox extends JDialog {
	
	private AddBookPanel owner;

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldTelephone;
	private JTextField textFieldStreet;
	private JTextField textFieldCity;
	private JTextField textFieldState;
	private JTextField textFieldZip;
	private JEditorPane editorBio;
	private JLabel lblError;

	
	/**
	 * Create the dialog.
	 */
	public AddAuthorBox() {
		setTitle("Add Author");
		setBounds(100, 100, 450, 548);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(18, 18, 100, 381);
		contentPanel.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JLabel lblFirstName = new JLabel("First Name");
			panel.add(lblFirstName);
			lblFirstName.setHorizontalAlignment(SwingConstants.TRAILING);
		}
		{
			JLabel lblLastName = new JLabel("Last Name");
			panel.add(lblLastName);
			lblLastName.setHorizontalAlignment(SwingConstants.TRAILING);
		}
		{
			JLabel lblTelephone = new JLabel("Telephone");
			panel.add(lblTelephone);
			lblTelephone.setHorizontalAlignment(SwingConstants.TRAILING);
		}
		
		JLabel lblStreet = new JLabel("Street");
		lblStreet.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblStreet);
		
		JLabel lblAddress = new JLabel("City");
		lblAddress.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblAddress);
		
		JLabel lblState = new JLabel("State");
		lblState.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblState);
		
		JLabel lblZipCode = new JLabel("Zip Code");
		lblZipCode.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblZipCode);
		
		JLabel lblBio = new JLabel("Bio");
		lblBio.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblBio);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(139, 18, 283, 330);
		contentPanel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 12));
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setColumns(10);
		panel_1.add(textFieldFirstName);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setColumns(10);
		panel_1.add(textFieldLastName);
		
		textFieldTelephone = new JTextField();
		textFieldTelephone.setColumns(10);
		panel_1.add(textFieldTelephone);
		
		textFieldStreet = new JTextField();
		textFieldStreet.setColumns(10);
		panel_1.add(textFieldStreet);
		
		textFieldCity = new JTextField();
		textFieldCity.setColumns(10);
		panel_1.add(textFieldCity);
		
		textFieldState = new JTextField();
		textFieldState.setColumns(10);
		panel_1.add(textFieldState);
		
		textFieldZip = new JTextField();
		textFieldZip.setColumns(10);
		panel_1.add(textFieldZip);
		
		editorBio = new JEditorPane();
		editorBio.setBounds(139, 363, 283, 67);
		contentPanel.add(editorBio);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(18, 448, 394, 27);
		contentPanel.add(lblError);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(e -> { addAuthorAction(); });
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(e -> { setVisible(false); });
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	void setOwner(AddBookPanel panel) {
		owner = panel; 
	}
	
	 void addAuthorAction() {
		 
		 String firstName = textFieldFirstName.getText().trim();
		 String lastName = textFieldLastName.getText().trim();
		 String telephone = textFieldTelephone.getText().trim();
		 String bio = editorBio.getText().trim();
		 
		 String street = textFieldStreet.getText().trim();
		 String city = textFieldCity.getText().trim();
		 String state = textFieldState.getText().trim();
		 String zip = editorBio.getText().trim();
		 
		 
		 Address address = new Address(street, city, state, zip);
		 
		 if (owner != null) {			 
			 
			 Author author = new Author(firstName, lastName, telephone, address, bio);
			 
		
			 String message = author.getValidationMessage();
			 
			 if (message != null) {
				 System.out.println(message);
				 lblError.setText(message);
				 return;
			 }
			 
			 owner.addAuthor(author);
			 
			 
		 }
		 
		 setVisible(false);
	 }
}
