package librarysystem.managebook;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import business.Author;
import business.Book;
import business.ControllerInterface;
import business.SystemController;
import librarysystem.mainUI.MainUI;
import utility.DataUtil;
import javax.swing.JRadioButton;

public class AddBookPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldTitle;
	private JTextField textFieldIsbn;
	private JList<String> listAuthor;
	
	private ControllerInterface ci = new SystemController();
	
	private List<Author> authors = new ArrayList<>();
	private JTextField textFieldNumberCopy;
	private JRadioButton radioButton7Days;
	private JRadioButton radioButton21Days;
	ButtonGroup bg;
	
	/**
	 * Create the panel.
	 */
	public AddBookPanel() {
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Add New Book");
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblTitle.setBounds(21, 17, 224, 16);
		add(lblTitle);
		
		textFieldTitle = new JTextField();
		textFieldTitle.setBounds(183, 103, 211, 36);
		add(textFieldTitle);
		textFieldTitle.setColumns(10);
		
		textFieldIsbn = new JTextField();
		textFieldIsbn.setColumns(10);
		textFieldIsbn.setBounds(183, 55, 211, 36);
		add(textFieldIsbn);
		
		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(21, 65, 150, 16);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(21, 113, 150, 16);
		add(lblNewLabel_1);
		
		JLabel lblAuthor = new JLabel("Author(s)");
		lblAuthor.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAuthor.setBounds(20, 209, 150, 16);
		add(lblAuthor);
		
		JLabel lblMaxCheckoutLength = new JLabel("Checkout Length");
		lblMaxCheckoutLength.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMaxCheckoutLength.setBounds(21, 161, 150, 16);
		add(lblMaxCheckoutLength);
		
		JButton btnAddAuthor = new JButton("");
		btnAddAuthor.setIcon(new ImageIcon(AddBookPanel.class.getResource("/librarysystem/assets/iconPlus.png")));
		btnAddAuthor.setBackground(new Color(192, 192, 192));
		btnAddAuthor.addActionListener(e -> {
			AddAuthorBox box = new AddAuthorBox();
			box.setOwner(this);
			box.setVisible(true);
		});
		btnAddAuthor.setBounds(405, 209, 34, 34);
		add(btnAddAuthor);
		
		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.addActionListener( event -> {
			String title = textFieldTitle.getText().trim();
			String isbn = textFieldIsbn.getText().trim();
			String checkoutLength="";
			if(radioButton7Days.isSelected())checkoutLength="7";
			if(radioButton21Days.isSelected())checkoutLength="21";
			String numCopies = textFieldNumberCopy.getText().trim();
			addBook(isbn, title, checkoutLength, numCopies);
		});
		
		btnAddBook.setBounds(183, 340, 211, 50);
		add(btnAddBook);
		
		listAuthor = new JList<String>();
		listAuthor.setBounds(182, 209, 211, 71);
		add(listAuthor);
		
		textFieldNumberCopy = new JTextField();
		textFieldNumberCopy.setText("1");
		textFieldNumberCopy.setColumns(10);
		textFieldNumberCopy.setBounds(182, 292, 211, 36);
		add(textFieldNumberCopy);
		
		JLabel lblNumberOfCopies = new JLabel("Number of copies");
		lblNumberOfCopies.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNumberOfCopies.setBounds(20, 302, 150, 16);
		add(lblNumberOfCopies);
		radioButton7Days = new JRadioButton("7 days");
		radioButton7Days.setBounds(196, 151, 86, 36);
		add(radioButton7Days);
		
		radioButton21Days = new JRadioButton("21 days");
		radioButton21Days.setBounds(293, 151, 86, 36);
		add(radioButton21Days);
		bg=new ButtonGroup(); 
		bg.add(radioButton7Days);
		bg.add(radioButton21Days);


	}
	
	void addAuthor(Author author) {
		authors.add(author);
		System.out.print(author);
		
		String[] dataSource = new String[authors.size()];
		for (int i = 0; i < authors.size(); i++) {
			Author cur = authors.get(i);
			dataSource[i] = cur.getFirstName() + " " + cur.getLastName();
		}
		listAuthor.setListData(dataSource);
	}
	
	void addBook(String isbn, String title, String checkoutLength, String numCopies) {
		int length = DataUtil.getInt(checkoutLength);
		int numCopy = DataUtil.getInt(numCopies);
		
		Book book = new Book(isbn, title, length, authors);
	
		// Validate 
		String message = book.getValidationMessage();
		if (message != null) {
			System.out.println(message);	
			MainUI.INSTANCE.setMessage(message);
			return; 
		} else if (numCopy < 1) {
			System.out.println("Num copy must be greater than 1");
			MainUI.INSTANCE.setMessage("Num copy must be greater than 1");
			return;
		}

		// Add to system
		try {
			ci.addBook(book);
			System.out.print(book.getTitle() + " is added");
			MainUI.INSTANCE.setMessage(book.getTitle() + " is added");
			clearInput();
		} catch (Exception e) {
			System.out.print(e);
			MainUI.INSTANCE.setMessage(e.getMessage());
		}
	}
	
	private void clearInput() { 
		textFieldTitle.setText(null);
		textFieldIsbn.setText(null);
//		textFieldLength.setText(null);
		radioButton7Days.setSelected(false);
		radioButton21Days.setSelected(false);
		listAuthor.setListData(new String[0]);
		authors = new ArrayList<>();
	}
}
