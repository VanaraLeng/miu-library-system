package librarysystem.checkout;

import java.awt.Font;
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
		lblTitle.setBounds(21, 17, 224, 16);
		add(lblTitle);
		
		textMemberID = new JTextField();
		textMemberID.setText("1004");
		textMemberID.setBounds(173, 53, 231, 36);
		add(textMemberID);
		textMemberID.setColumns(10);
		
		textFieldIsbn = new JTextField();
		textFieldIsbn.setText("48-56882");
		textFieldIsbn.setColumns(10);
		textFieldIsbn.setBounds(173, 101, 231, 36);
		add(textFieldIsbn);
		
		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(42, 110, 113, 16);
		add(lblNewLabel);
		
		JLabel lblMemberID = new JLabel("Member ID");
		lblMemberID.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMemberID.setBounds(42, 62, 113, 16);
		add(lblMemberID);
		
		JButton btnAddBook = new JButton("Checkout");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String isbn = textFieldIsbn.getText().trim();
				String mid = textMemberID.getText().trim();
				
				checkRecord(mid, isbn);
			}
		});
		btnAddBook.setBounds(416, 102, 103, 33);
		add(btnAddBook);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 150, 477, 250);
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
		btnClear.setBounds(416, 54, 103, 32);
		add(btnClear);
	}
	
	void checkRecord(String mid, String isbn) {
		
		try {
			if (record == null) {
				LibraryMember member = ci.getMember(mid);
				record = ci.createCheckoutRecord(member);	
			}
			
			BookCopy copy = ci.getBookCopy(isbn);
			ci.addBookCopy(record, copy, LocalDate.now());
			
			setupTable(record.getCheckoutEntries());
			
		} catch (LibrarySystemException e) {
			System.out.print(e.getMessage());
			// Show error
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
	}
	
	private void clearInput() {
		record = null;
		textMemberID.setText(null);
		textFieldIsbn.setText(null);
		clearTable();
	}
	
	private void clearTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (int i = 0; i < model.getRowCount(); i++) {
			model.removeRow(i);	
		}
	}
}
