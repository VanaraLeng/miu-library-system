package librarysystem.checkout;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import business.CheckoutEntry;
import business.CheckoutRecord;
import business.ControllerInterface;
import business.LibraryMember;
import business.LibrarySystemException;
import business.SystemController;
import utility.DataUtil;

public class OverdueRecordPanel extends JPanel {
	
	private JTextField textMemberID;
	
	private CheckoutRecord record;
	private ControllerInterface ci = new SystemController();
	private JTable table;
	
	/**
	 * Create the panel.
	 */
	public OverdueRecordPanel() {
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Overdue");
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblTitle.setBounds(42, 19, 224, 16);
		add(lblTitle);
		
		textMemberID = new JTextField();
		textMemberID.setText("1004");
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
		panel.setBounds(6, 111, 500, 56);
		add(panel);
		panel.setLayout(new GridLayout(0, 2, 12, 8));
		
		JLabel lblNewLabel_1 = new JLabel("Book Title");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblNewLabel_1);
		
		JList list_1 = new JList();
		list_1.setEnabled(false);
		panel.add(list_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Copy Number");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblNewLabel_1_1);
		
		JList list_1_1 = new JList();
		list_1_1.setEnabled(false);
		panel.add(list_1_1);
		
		table = new JTable();
		table.setBounds(16, 179, 547, 238);
		add(table);
	}
	
	void checkRecord(String mid) {
		try {
			if (record == null) {
				LibraryMember member = ci.getMember(mid);
				member.setupCheckoutRecord();
				record = member.getCheckoutRecord();
			}
			
			setupTable(record.getCheckoutEntries());
			
		} catch (LibrarySystemException e) {
			System.out.print(e.getMessage());
			// Show error
		}
	}
	
	void setupTable(List<CheckoutEntry> list) {
		DefaultTableModel model = new DefaultTableModel();
		String[] column = {"ISBN", "Book Title", "Copy numbers" };
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
	
	public void clearTable() {
		DefaultTableModel dm = (DefaultTableModel)table.getModel();
		int rowCount = dm.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
		    dm.removeRow(i);
		}
	}
}
