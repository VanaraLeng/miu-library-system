package librarysystem.checkout;

import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
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
import librarysystem.mainUI.MainUI;
import utility.DataUtil;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class CheckoutRecordPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public static final CheckoutRecordPanel INSTANCE = new CheckoutRecordPanel();
	
	private JTextField textMemberID;

	private ControllerInterface ci = new SystemController();
	private JTable table;
	
	/**
	 * Create the panel.
	 */
	public CheckoutRecordPanel() {
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Checkout Record");
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblTitle.setBounds(17, 17, 224, 16);
		add(lblTitle);
		
		textMemberID = new JTextField();
		textMemberID.setBounds(173, 53, 200, 36);
		add(textMemberID);
		textMemberID.setColumns(10);
		
		JLabel lblMemberID = new JLabel("Member ID");
		lblMemberID.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMemberID.setBounds(42, 62, 113, 16);
		add(lblMemberID);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 101, 589, 317);
		add(scrollPane);
		
		table = new JTable();
		table.setBounds(6, 215, 502, 201);
		scrollPane.add(table);
		scrollPane.setViewportView(table);
		
		JButton btnClear = new JButton("Print Checkout");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String memberId = textMemberID.getText().trim();
				clearTable();
				MainUI.INSTANCE.setMessage("");
				checkRecord(memberId);
			}
		});
		btnClear.setBounds(382, 54, 137, 32);
		add(btnClear);
	}
	
	void checkRecord(String mid) {
		try {
			LibraryMember member = ci.getMember(mid);
			member.setupCheckoutRecord();
			CheckoutRecord record = member.getCheckoutRecord();
			setupTable(record.getCheckoutEntries());
		} catch (LibrarySystemException e) {
			MainUI.INSTANCE.setMessage(e.getMessage());
			System.out.print(e.getMessage());
			// Show error
		}
	}
	
	void setupTable(List<CheckoutEntry> list) {
		DefaultTableModel model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			    public boolean isCellEditable(int row, int column) {
			       return false;
			    }
		};
		String[] column = {"ISBN", "Book Name", "Due Date" };
		model.setColumnIdentifiers(column);
		
		table.setModel(model);
		
	    
	    for (int i = 0; i < list.size(); i++) {
	        String[] data = new String[3];
	        data[0] = list.get(i).getBookCopy().getBook().getIsbn();
	        data[1] = list.get(i).getBookCopy().getBook().getTitle();
	        data[2] = DataUtil.dateString(list.get(i).getDueDate());
	        
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
