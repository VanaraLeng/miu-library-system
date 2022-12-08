package myLibrarySystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import librarysystem.authentication.LoginWindow;
import librarysystem.mainUI.MainUI;

public class Menu extends JPanel {
	JButton btnAddMember;
	JButton btnLoginLogout;
	JButton btnAddBook;
	JButton btnAddBookCopy;
	JButton btnCheckoutBook;
	JButton btnCheckoutRecord;
	JButton btnCheckoutOverdue;
	JButton btnAllMembers;
	JButton btnAllMembers_1;
	
	public Menu() {
		setBounds(0, 0, 144, 359);
    	setLayout(null);
    	
    	btnAddMember = new JButton("Add Member");
    	btnAddMember.setBounds(10, 55, 124, 21);
    	btnAddMember.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.out.println("hiiii");
    			MainUI.INSTANCE.setMainPanel(AddMemberWindow.INSTANCE);
    		}
    	});
    	add(btnAddMember);
    	
    	btnLoginLogout = new JButton("Login/Logout");
    	btnLoginLogout.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			MainUI.INSTANCE.setMainPanel(LoginWindow.INSTANCE);
    		}
    	});
    	btnLoginLogout.setBounds(10, 17, 124, 21);
    	add(btnLoginLogout);
    	
    	btnAddBook = new JButton("Add Book");
    	btnAddBook.setBounds(10, 93, 124, 21);
    	add(btnAddBook);
    	
    	btnAddBookCopy = new JButton("Add Book Copy");
    	btnAddBookCopy.setBounds(10, 131, 124, 21);
    	add(btnAddBookCopy);
    	
    	btnCheckoutBook = new JButton("Checkout Book");
    	btnCheckoutBook.setBounds(10, 169, 124, 21);
    	add(btnCheckoutBook);
    	
    	btnCheckoutRecord = new JButton("Checkout Record");
    	btnCheckoutRecord.setBounds(10, 207, 124, 21);
    	add(btnCheckoutRecord);
    	
    	btnCheckoutOverdue = new JButton("Checkout Overdue");
    	btnCheckoutOverdue.setBounds(10, 245, 124, 21);
    	add(btnCheckoutOverdue);
    	
    	btnAllMembers = new JButton("All Members");
    	btnAllMembers.setBounds(10, 283, 124, 21);
    	add(btnAllMembers);
    	
    	btnAllMembers_1 = new JButton("All Books");
    	btnAllMembers_1.setBounds(10, 321, 124, 21);
    	add(btnAllMembers_1);
	}
	
	private static final long serialVersionUID = 1L;

}
