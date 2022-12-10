package librarysystem.checkout;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

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

public class OverdueRecordPanel extends JPanel {
	
	private JTextField textMemberID;
	
	private CheckoutRecord record;
	private ControllerInterface ci = new SystemController();
	private JTable table;
	private JTextField textBookTitle;
	private JTextField textFieldCopyNumber;
	
	/**
	 * Create the panel.
	 */
	public OverdueRecordPanel() {
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Overdue");
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblTitle.setBounds(18, 16, 224, 16);
		add(lblTitle);
		
		textMemberID = new JTextField();
		textMemberID.setText("48-56882");
		textMemberID.setBounds(173, 53, 200, 36);
		add(textMemberID);
		textMemberID.setColumns(10);
		
		JLabel lblMemberID = new JLabel("ISBN");
		lblMemberID.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMemberID.setBounds(42, 62, 113, 16);
		add(lblMemberID);
		
		JButton btnClear = new JButton("Check Overdue");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String memberId = textMemberID.getText().trim();
				checkRecord(memberId);
			}
		});
		btnClear.setBounds(382, 54, 137, 32);
		add(btnClear);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 111, 513, 56);
		add(panel);
		panel.setLayout(new GridLayout(0, 2, 12, 8));
		
		JLabel lblNewLabel_1 = new JLabel("Book Title");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblNewLabel_1);
		
		textBookTitle = new JTextField();
		textBookTitle.setEnabled(false);
		textBookTitle.setEditable(false);
		panel.add(textBookTitle);
		textBookTitle.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Copy Number");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblNewLabel_1_1);
		
		textFieldCopyNumber = new JTextField();
		textFieldCopyNumber.setEnabled(false);
		textFieldCopyNumber.setEditable(false);
		textFieldCopyNumber.setColumns(10);
		panel.add(textFieldCopyNumber);
		
		table = new JTable();
		table.setBounds(16, 179, 547, 238);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 205, Constant.CONTENT_WIDTH, 223);
		add(scrollPane);
		
		table = new JTable();
		table.setBounds(6, 215, 502, 201);
		scrollPane.add(table);
		scrollPane.setViewportView(table);
		

	}
	
	void checkRecord(String isbn) {
		try {
			Book book = ci.getBook(isbn);
			
			if (book == null) {
				MainUI.INSTANCE.setMessage("Book is not found");
				return;
			}
			
			String bookTitle = book.getTitle();
			textBookTitle.setText(bookTitle);
			String copyNumber = String.valueOf(book.getCopies().length);
			textFieldCopyNumber.setText(copyNumber);
			
			List<LibraryMember> members = ci.getAllMembers();
			setupTable(members, isbn);
			
		} catch (LibrarySystemException e) {
			System.out.print(e.getMessage());
			// Show error
		}
	}
	
	void setupTable(List<LibraryMember> list, String inputIsbn) {
		
		DefaultTableModel model = new DefaultTableModel();
		String[] column = { "ISBN", "Checkout Date", "Due Date" };
		
		model.setColumnIdentifiers(column);
		
		table.setModel(model);
		
		
		
		ArrayList<CheckoutEntry> entries = new ArrayList<>();
		
		for (LibraryMember m: list) {
			CheckoutRecord record = m.getCheckoutRecord();
			if (record != null) {
				
				for (CheckoutEntry entry : record.getCheckoutEntries()) {
					String isbn = entry.getBookCopy().getBook().getIsbn();
							
					if (inputIsbn.equals(isbn)) {
						entries.add(entry);
						
						
						// Table 
						 String[] data = new String[3];
					        data[0] = m.getFirstName() + " " + m.getLastName();
					        data[1] = DataUtil.dateString(entry.getCheckoutDate());
					        data[2] = DataUtil.dateString(entry.getDueDate());
					        
					        model.addRow(data);
						
					}
				}
			}
		}
	    
	    table.sizeColumnsToFit(0);
	    table.sizeColumnsToFit(1);
	}
	
	public void clearTable() {
		DefaultTableModel dm = (DefaultTableModel)table.getModel();
		int rowCount = dm.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
		    dm.removeRow(i);
		}
	}
}
