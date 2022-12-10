package librarysystem.mainUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import business.SystemController;
import dataaccess.Auth;
import librarysystem.checkout.CheckoutPanel;
import librarysystem.checkout.CheckoutRecordPanel;
import librarysystem.checkout.OverdueRecordPanel;
import librarysystem.authentication.LoginWindow;
import librarysystem.managebook.AddBookCopyWindow;
import librarysystem.managebook.AddBookPanel;
import librarysystem.view.ViewAllBooksPanel;
import librarysystem.view.ViewAllMembersPanel;
import managemember.AddMemberWindow;

public class Menu extends JPanel {
	JButton btnAddMember;
	JButton btnLoginLogout;
	JButton btnAddBook;
	JButton btnAddBookCopy;
	JButton btnCheckoutBook;
	JButton btnCheckoutRecord;
	JButton btnCheckoutOverdue;
	JButton btnAllMembers;
	JButton btnAllBooks;
	
	MainUI mainUI;
	
	public Menu() {
		setBounds(0, 0, 174, 397);
    	setLayout(null);
    	
    	btnAddMember = new JButton("Add Member");
    	btnAddMember.setBounds(10, 65, 154, 21);
    	btnAddMember.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(SystemController.getCurrentAuth()==Auth.ADMIN || SystemController.getCurrentAuth()==Auth.BOTH) {
    				MainUI.INSTANCE.setMessage("");
        			MainUI.INSTANCE.setMainPanel(AddMemberWindow.INSTANCE);
    			}
    			else {
    				if(SystemController.getCurrentAuth()==null)
    				MainUI.INSTANCE.setMessage("      You need to login first!");
    				else
    					MainUI.INSTANCE.setMessage("      You don't have the authorization for that!");
    				
    			}
    		}
    	});
    	add(btnAddMember);
    	
    	btnLoginLogout = new JButton("Login/Logout");
    	btnLoginLogout.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			MainUI.INSTANCE.setMessage("");
    			MainUI.INSTANCE.setMainPanel(LoginWindow.INSTANCE);
    		}
    	});
    	btnLoginLogout.setBounds(10, 27, 154, 21);
    	add(btnLoginLogout);

    	
    	btnAddBook = new JButton("Add Book");
    	btnAddBook.setBounds(10, 103, 154, 21);
    	btnAddBook.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(SystemController.getCurrentAuth()==Auth.ADMIN || SystemController.getCurrentAuth()==Auth.BOTH) {
        			MainUI.INSTANCE.setMessage("");
        			MainUI.INSTANCE.setMainPanel(new AddBookPanel());
    			}
    			else {
    				if(SystemController.getCurrentAuth()==null)
    				MainUI.INSTANCE.setMessage("You need to login first!");
    				else
    					MainUI.INSTANCE.setMessage("You don't have the authority for that!");
    			}
    		}
    	});
    	add(btnAddBook);
    	
    	btnAddBookCopy = new JButton("Add Book Copy");
    	btnAddBookCopy.setBounds(10, 141, 154, 21);
    	btnAddBookCopy.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(SystemController.getCurrentAuth() == Auth.ADMIN || SystemController.getCurrentAuth() ==Auth.BOTH) {
    				MainUI.INSTANCE.setMessage("");
    				MainUI.INSTANCE.setMainPanel( AddBookCopyWindow.INSTANCE);
    			}
    			else {
    				if(SystemController.getCurrentAuth()==null)
    				MainUI.INSTANCE.setMessage("You need to login first!");
    				else
    					MainUI.INSTANCE.setMessage("You don't have the authority for that!");
    			}
    		}
    	});
    	add(btnAddBookCopy);
    	
    	btnCheckoutBook = new JButton("Checkout Book");
    	btnCheckoutBook.setBounds(10, 179, 154, 21);
    	btnCheckoutBook.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(SystemController.getCurrentAuth()==Auth.LIBRARIAN || SystemController.getCurrentAuth()==Auth.BOTH) {
    				MainUI.INSTANCE.setMessage("");
    				MainUI.INSTANCE.setMainPanel(new CheckoutPanel());
    			}
    			else {
    				if(SystemController.getCurrentAuth()==null)
    				MainUI.INSTANCE.setMessage("You need to login first!");
    				else
    					MainUI.INSTANCE.setMessage("You don't have the authority for that!");
    			}
    		}
    	});
    	add(btnCheckoutBook);
    	
    	btnCheckoutRecord = new JButton("Checkout Record");
    	btnCheckoutRecord.setBounds(10, 217, 154, 21);
    	btnCheckoutRecord.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(SystemController.getCurrentAuth()==Auth.LIBRARIAN || SystemController.getCurrentAuth()==Auth.BOTH) {
    				MainUI.INSTANCE.setMessage("");
    				MainUI.INSTANCE.setMainPanel(new CheckoutRecordPanel());
    			}
    			else {
    				if(SystemController.getCurrentAuth()==null)
    				MainUI.INSTANCE.setMessage("You need to login first!");
    				else
    					MainUI.INSTANCE.setMessage("You don't have the authority for that!");
    			}
    		}
    	});
    	add(btnCheckoutRecord);
    	
    	btnCheckoutOverdue = new JButton("Checkout Overdue");
    	btnCheckoutOverdue.setBounds(10, 255, 154, 21);
    	btnCheckoutOverdue.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(SystemController.getCurrentAuth()==Auth.LIBRARIAN ||   SystemController.getCurrentAuth()==Auth.BOTH) {
        			MainUI.INSTANCE.setMainPanel(new OverdueRecordPanel());
        			MainUI.INSTANCE.setMessage("");
    			}
    			else {
    				if(SystemController.getCurrentAuth()==null)
    				MainUI.INSTANCE.setMessage("      You need to login first!");
    				else
    					MainUI.INSTANCE.setMessage("      You don't have the authorization for that!");
    			}
    		}
    	});
    	add(btnCheckoutOverdue);

    	
    	btnAllMembers = new JButton("All Members");
    	btnAllMembers.setBounds(10, 293, 154, 21);
    	btnAllMembers.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(SystemController.getCurrentAuth()==Auth.ADMIN ||  SystemController.getCurrentAuth()==Auth.LIBRARIAN||SystemController.getCurrentAuth()==Auth.BOTH) {
    				MainUI.INSTANCE.setMessage("");
    				MainUI.INSTANCE.setMainPanel(new ViewAllMembersPanel());
    			}
    			else {
    				if(SystemController.getCurrentAuth()==null)
    				MainUI.INSTANCE.setMessage("You need to login first!");
    				else
    					MainUI.INSTANCE.setMessage("You don't have the authority for that!");
    			}
    		}
    	});
    	add(btnAllMembers);

    	
    	btnAllBooks = new JButton("All Books");
    	btnAllBooks.setBounds(10, 331, 154, 21);
    	btnAllBooks.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(SystemController.getCurrentAuth()==Auth.ADMIN || SystemController.getCurrentAuth()==Auth.LIBRARIAN ||SystemController.getCurrentAuth()==Auth.BOTH) {
    				MainUI.INSTANCE.setMainPanel(new ViewAllBooksPanel());
        			MainUI.INSTANCE.setMessage("");
    			}
    			else {
    				if(SystemController.getCurrentAuth()==null)
    				MainUI.INSTANCE.setMessage("You need to login first!");
    				else
    					MainUI.INSTANCE.setMessage("You don't have the authority for that!");
    			}
    		}
    	});
    	add(btnAllBooks);
    	
	}
	public void enable_buttons() {
		
		if(SystemController.getCurrentAuth()==Auth.ADMIN || SystemController.getCurrentAuth()==Auth.BOTH) {
	    	btnAllBooks.setEnabled(true);
	    	btnAllMembers.setEnabled(true);
	    	btnAddBookCopy.setEnabled(true);
	    	btnAddBook.setEnabled(true);
	    	btnAddMember.setEnabled(true);
		}
		
		if(SystemController.getCurrentAuth()==Auth.LIBRARIAN || SystemController.getCurrentAuth()==Auth.BOTH) {
			btnCheckoutOverdue.setEnabled(true);
			btnCheckoutRecord.setEnabled(true);
			btnCheckoutBook.setEnabled(true);
			btnAllBooks.setEnabled(true);
			btnAllMembers.setEnabled(true);

		}
		
	}	
	public void disable_buttons() {
		btnCheckoutOverdue.setEnabled(false);
		btnCheckoutRecord.setEnabled(false);
		btnCheckoutBook.setEnabled(false);
    	btnAllBooks.setEnabled(false);
    	btnAllMembers.setEnabled(false);
    	btnAddBookCopy.setEnabled(false);
    	btnAddBook.setEnabled(false);
    	btnAddMember.setEnabled(false);
	}
	
	
	private static final long serialVersionUID = 1L;

}
