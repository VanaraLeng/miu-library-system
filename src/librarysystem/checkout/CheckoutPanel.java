package librarysystem.checkout;

import java.awt.Component;
import java.awt.Font;
import java.awt.Menu;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import business.Author;
import business.Book;
import business.BookCopy;
import business.CheckoutEntry;
import business.CheckoutRecord;
import business.ControllerInterface;
import business.LibraryMember;
import business.LibrarySystemException;
import business.SystemController;
import librarysystem.Constant;
import librarysystem.mainUI.MainUI;
import utility.DataUtil;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CheckoutPanel extends JPanel {
	private JTextField textMemberID;
	private JTextField textFieldIsbn;
	
	private CheckoutRecord record;
	private ControllerInterface ci = new SystemController();
	private JTable table;
	
	/**
	 * Create the panel.
	 */
	public CheckoutPanel() {
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Checkout Book");
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblTitle.setBounds(31, 18, 224, 16);
		add(lblTitle);
		
		textMemberID = new JTextField();
		textMemberID.setBounds(162, 53, 231, 36);
		add(textMemberID);
		textMemberID.setColumns(10);
		
		textFieldIsbn = new JTextField();
		textFieldIsbn.setColumns(10);
		textFieldIsbn.setBounds(162, 101, 231, 36);
		add(textFieldIsbn);
		
		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(31, 110, 113, 16);
		add(lblNewLabel);
		
		JLabel lblMemberID = new JLabel("Member ID");
		lblMemberID.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMemberID.setBounds(31, 62, 113, 16);
		add(lblMemberID);
		
		JButton btnAddBook = new JButton("Checkout");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String isbn = textFieldIsbn.getText().trim();
				String mid = textMemberID.getText().trim();
				
				checkRecord(mid, isbn);
			}
		});
		btnAddBook.setBounds(419, 102, 136, 36);
		add(btnAddBook);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 150, Constant.CONTENT_WIDTH, 290);
		add(scrollPane);
		
		table = new JTable();
		table.setBounds(6, 215, 502, 201);
		scrollPane.add(table);
		scrollPane.setViewportView(table);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearInput();
			}
		});
		btnClear.setBounds(419, 54, 136, 36);
		add(btnClear);
	}
	
	void checkRecord(String mid, String isbn) {
		try {
			if (record == null) {
				LibraryMember member = ci.getMember(mid);
				member.setupCheckoutRecord();
				record = member.getCheckoutRecord();
			}
			
			BookCopy bookCopy = ci.getBookCopy(isbn);
			if (bookCopy == null) {
				MainUI.INSTANCE.setMessage("No copy available");
				return;
			}
			
			ci.checkoutBookCopy(record, bookCopy);
			
			setupTable(record.getCheckoutEntries());
			
		} catch (LibrarySystemException e) {
			MainUI.INSTANCE.setMessage(e.getMessage());
		}
	}
	
	void setupTable(List<CheckoutEntry> list) {
		DefaultTableModel model = new DefaultTableModel();
		String[] column = {"ISBN", "Book Name", "Due Date" };
		model.setColumnIdentifiers(column);
		
		table.setModel(model);
		
	    
	    for (int i = 0; i < list.size(); i++) {
	        String[] data = new String[3];
	        data[0] = list.get(i).getBookCopy().getBook().getIsbn();
	        data[1] = list.get(i).getBookCopy().getBook().getTitle();
	        data[2] = DataUtil.dateString(list.get(i).getCheckoutDate());
	        
	        model.addRow(data);
	    }
	    
	    table.sizeColumnsToFit(0);
	    table.sizeColumnsToFit(1);
	}
	
	private void clearInput() {
		record = null;
		textMemberID.setText(null);
		textFieldIsbn.setText(null);
		clearTable();
		textMemberID.grabFocus();
	}
	
	public void clearTable() {
		DefaultTableModel dm = (DefaultTableModel)table.getModel();
		int rowCount = dm.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
		    dm.removeRow(i);
		}
	}
}
