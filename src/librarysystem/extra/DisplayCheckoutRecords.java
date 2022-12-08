package librarysystem.extra;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import business.CheckoutEntry;
import business.CheckoutRecord;
import business.LibrarySystemException;
import business.SystemController;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

public class DisplayCheckoutRecords extends JPanel {
	private JTable checkoutRecordsTable;
//	private final int numberOfColumn = 4;
	
	public DisplayCheckoutRecords(String mId) {		
		JLabel lblTitle = new JLabel("Checkout Records for memberid");
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblTitle.setBounds(21, 17, 224, 16);
		add(lblTitle);
		
		String[][] data = {
//			{"", "", "", ""},
			{"Java", "2", "2022/12/5", "2022/12/12"},
			{"Java2", "1", "2022/12/5", "2022/12/12"}
		};
		
		List<CheckoutRecord> records = getCheckoutRecord(mId);
		if (records != null) {
			data = new String[numberOfEntries(records)][4];
			int index = 0;
			for (CheckoutRecord cr: records) {
				List<CheckoutEntry> entries = cr.getCheckoutEntries();
				String[] entry = new String[4];
				for (CheckoutEntry ce: entries) {
					entry[0] = ce.getBookCopy().getBook().getTitle();
					entry[1] = "" + ce.getBookCopy().getCopyNum();
					entry[2] = ce.getCheckoutDate().toString();
					entry[3] = ce.getDueDate().toString();
				}
				data[index] = entry;
				index++;
			}
		}

		String[] columnNames = {"Book", "Book Copy", "Checkout Date", "Due Date"};
		
		checkoutRecordsTable = new JTable(data, columnNames);
//		checkoutRecordsTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
//		checkoutRecordsTable.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(checkoutRecordsTable);		
		add(scrollPane);
	}

	
	private static List<CheckoutRecord> getCheckoutRecord(String mId) {
		SystemController sc = new SystemController();
		try {
			return sc.getCheckoutRecords(mId);
		} catch(LibrarySystemException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	private static int numberOfEntries(List<CheckoutRecord> records) {
		int num = 0;
		for (CheckoutRecord cr: records) {
			num += cr.getCheckoutEntries().size();
		}
		return num;
	}
}

