package librarysystem.view;

import java.util.List;

import business.Author;
import business.Book;
import business.ControllerInterface;
import business.SystemController;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;


public class ViewAllBooksPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final ViewAllBooksPanel INSTANCE = new ViewAllBooksPanel();
    ControllerInterface ci = new SystemController();
    private JTable table;

	//Singleton class
	public ViewAllBooksPanel() {
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 101, 477, 299);
		add(scrollPane);
		
		table = new JTable();
		table.setBounds(6, 215, 502, 201);
		scrollPane.add(table);
		scrollPane.setViewportView(table);
		
		showData();
		
		System.out.println("view all book panel");
	
	}
	
	void showData() {
		setupTable(ci.getAllBooks());
	}
	
	void setupTable(List<Book> bookList) {
		DefaultTableModel model = new DefaultTableModel();
		String[] column = {"ISBN", "Title", "Author", "Copy", "Length"};
		model.setColumnIdentifiers(column);
		
		table.setModel(model);
		
	    
	    for (int i = 0; i < bookList.size(); i++) {
	        String[] data = new String[5];
	        Book book = bookList.get(i);
	        data[0] = book.getIsbn();
	        data[1] = book.getTitle();
	        
	        List<Author> authors = bookList.get(i).getAuthors();
	        data[2] = "";
	        int j = 0;
	        while (j < authors.size()-1) {
	        	data[2] += authors.get(j).getFirstName() + " " + authors.get(j).getLastName() + ", ";
	        	j++;
	        }
	        data[2] += authors.get(j).getFirstName() + " " + authors.get(j).getLastName();
	        data[3] = Integer.toString(book.getCopies().length);
	        data[4] = Integer.toString(book.getMaxCheckoutLength());
	        
	        model.addRow(data);
	    }
	    
	    table.getColumnModel().getColumn(0).setPreferredWidth(150);
	    table.getColumnModel().getColumn(1).setPreferredWidth(200);
	    table.getColumnModel().getColumn(2).setPreferredWidth(200);
	    table.getColumnModel().getColumn(3).setPreferredWidth(60);
	    table.getColumnModel().getColumn(4).setPreferredWidth(60);

	    table.setRowHeight(25);
	}
}
