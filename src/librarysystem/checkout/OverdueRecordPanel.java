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
import utility.Validator;

public class OverdueRecordPanel extends JPanel {
	public static final OverdueRecordPanel INSTANCE = new OverdueRecordPanel();
	
	private JTextField textMemberID;
	
	private ControllerInterface ci = new SystemController();
	private JTable table;
	private JTextField textBookTitle;
	private JTextField textFieldCopyNumber;
	private JTextField textFieldAuthor;
	private JLabel lblAuthor;
	
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
		textMemberID.setBounds(197, 44, 190, 36);
		add(textMemberID);
		textMemberID.setColumns(10);
		
		JLabel lblMemberID = new JLabel("ISBN");
		lblMemberID.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMemberID.setBounds(66, 53, 113, 16);
		add(lblMemberID);
		
		JButton btnClear = new JButton("Check Overdue");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String memberId = textMemberID.getText().trim();
				checkRecord(memberId);
			}
		});
		btnClear.setBounds(406, 45, 137, 36);
		add(btnClear);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 99, 577, 109);
		add(panel);
		panel.setLayout(new GridLayout(0, 3, 8, 6));
		
		JLabel lblNewLabel_1 = new JLabel("Book Title");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblNewLabel_1);
		
		textBookTitle = new JTextField();
		textBookTitle.setEnabled(false);
		textBookTitle.setEditable(false);
		panel.add(textBookTitle);
		textBookTitle.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setEnabled(false);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Number of Copy");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblNewLabel_1_1);
		
		textFieldCopyNumber = new JTextField();
		textFieldCopyNumber.setEnabled(false);
		textFieldCopyNumber.setEditable(false);
		textFieldCopyNumber.setColumns(10);
		panel.add(textFieldCopyNumber);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setEnabled(false);
		panel.add(lblNewLabel_2);
		
		lblAuthor = new JLabel("Author(s)");
		lblAuthor.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblAuthor);
		
		textFieldAuthor = new JTextField();
		textFieldAuthor.setEnabled(false);
		textFieldAuthor.setEditable(false);
		textFieldAuthor.setColumns(10);
		panel.add(textFieldAuthor);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 220, 589, 208);
		add(scrollPane);
		
		table = new JTable();
		table.setBounds(6, 215, 502, 201);
		scrollPane.add(table);
		scrollPane.setViewportView(table);
		

	}
	
	void checkRecord(String isbn) {
		
		if (!Validator.isCorrectISBN(isbn)) {
			MainUI.INSTANCE.setMessage("ISBN is incorrect");
			return;
		}
		
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
			textFieldAuthor.setText(book.getAuthorNames());
			
			List<LibraryMember> members = ci.getAllMembers();
			setupTable(members, isbn, book);
			
		} catch (LibrarySystemException e) {
			System.out.print(e.getMessage());
			// Show error
		}
	}
	
	void setupTable(List<LibraryMember> list, String inputIsbn, Book book) {
		
		DefaultTableModel model = new DefaultTableModel() {
			 @Override
			    public boolean isCellEditable(int row, int column) {
			       return false;
			    }
		};
		String[] column = {"Copy Number", "Member ID", "Name", "Checkout Date", "Due Date" };
		
		model.setColumnIdentifiers(column);
		table.setModel(model);
		
		ArrayList<CheckoutEntry> entries = new ArrayList<>();
		
		// Loop through member
		for (LibraryMember m: list) {
			CheckoutRecord record = m.getCheckoutRecord();
			if (record != null) {
				for (CheckoutEntry entry : record.getCheckoutEntries()) {
					String isbn = entry.getBookCopy().getBook().getIsbn();
							
					if (inputIsbn.equals(isbn)) {
						entries.add(entry);
						// Table 
						 String[] data = new String[5];
						 	
						 	data[0] = String.valueOf(entry.getBookCopy().getCopyNum());
						 	data[1] = m.getMemberId();
					        data[2] = m.getFirstName() + " " + m.getLastName();
					        data[3] = DataUtil.dateString(entry.getCheckoutDate());
					        data[4] = DataUtil.dateString(entry.getDueDate());
					        
					        model.addRow(data);
					}
				}
			}
		}
		
		// Loop through book, display only available
		BookCopy[] books = book.getCopies();
		
		for (BookCopy copy: books) {
			String[] data = new String[5];
			
			System.out.println(book.isAvailable());
			
			if (book.isAvailable()) {
			 	data[0] = String.valueOf(copy.getCopyNum());
			 	data[1] = "N/A";
		        data[2] = "N/A";
		        data[3] = "N/A";
		        data[4] = "N/A";
		        model.addRow(data);
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
