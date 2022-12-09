package librarysystem.view;

import java.util.List;

import business.Author;
import business.Book;
import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;


public class ViewAllMembersPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final ViewAllMembersPanel INSTANCE = new ViewAllMembersPanel();
    ControllerInterface ci = new SystemController();
    private JTable table;

	//Singleton class
	public ViewAllMembersPanel() {
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 101, 477, 299);
		add(scrollPane);
		
		table = new JTable();
		table.setBounds(6, 215, 502, 201);
		scrollPane.add(table);
		scrollPane.setViewportView(table);
		
		showData();	
	}
	
	void showData() {
		setupTable(ci.getAllMembers());
	}
	
	void setupTable(List<LibraryMember> members) {
		DefaultTableModel model = new DefaultTableModel();
		String[] column = {"ID", "FirstName", "LastName", "Tel", "Zip"};
		model.setColumnIdentifiers(column);
		
		table.setModel(model);
		
	    
	    for (int i = 0; i < members.size(); i++) {
	        String[] data = new String[5];
	        LibraryMember member = members.get(i);
	        data[0] = member.getMemberId();
	        data[1] = member.getFirstName();
	        data[2] = member.getLastName();
	        data[3] = member.getTelephone();
	        data[4] = member.getAddress().getZip();
	        
	        model.addRow(data);
	    }
	    
//	    table.getColumnModel().getColumn(0).setPreferredWidth(150);
//	    table.getColumnModel().getColumn(1).setPreferredWidth(200);
//	    table.getColumnModel().getColumn(2).setPreferredWidth(200);
//	    table.getColumnModel().getColumn(3).setPreferredWidth(60);
//	    table.getColumnModel().getColumn(4).setPreferredWidth(60);

	    table.setRowHeight(25);
	}
}
