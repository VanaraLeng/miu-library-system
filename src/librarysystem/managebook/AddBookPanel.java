package librarysystem.managebook;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

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
import utility.DataUtil;

public class AddBookPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldTitle;
	private JTextField textFieldIsbn;
	private JTextField textFieldLength;
	private JList<String> listAuthor;
	
	private ControllerInterface ci = new SystemController();
	
	private List<Author> authors = new ArrayList<>();
	private JTextField textFieldNumberCopy;
	
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
		textFieldTitle.setText("Java Advance");
		textFieldTitle.setBounds(183, 103, 211, 36);
		add(textFieldTitle);
		textFieldTitle.setColumns(10);
		
		textFieldIsbn = new JTextField();
		textFieldIsbn.setText("1234567890123");
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
		lblAuthor.setBounds(21, 199, 150, 16);
		add(lblAuthor);
		
		textFieldLength = new JTextField();
		textFieldLength.setText("1");
		textFieldLength.setColumns(10);
		textFieldLength.setBounds(183, 151, 211, 36);
		add(textFieldLength);
		
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
		btnAddAuthor.setBounds(406, 199, 34, 34);
		add(btnAddAuthor);
		
		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.addActionListener( event -> {
			String title = textFieldTitle.getText().trim();
			String isbn = textFieldIsbn.getText().trim();
			String checkoutLength = textFieldLength.getText().trim();
			String numCopies = textFieldNumberCopy.getText().trim();
			addBook(isbn, title, checkoutLength, numCopies);
		});
		
		btnAddBook.setBounds(183, 340, 211, 50);
		add(btnAddBook);
		
		listAuthor = new JList<String>();
		listAuthor.setBounds(183, 199, 211, 71);
		add(listAuthor);
		
		textFieldNumberCopy = new JTextField();
		textFieldNumberCopy.setText("1");
		textFieldNumberCopy.setColumns(10);
		textFieldNumberCopy.setBounds(183, 282, 211, 36);
		add(textFieldNumberCopy);
		
		JLabel lblNumberOfCopies = new JLabel("Number of copies");
		lblNumberOfCopies.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNumberOfCopies.setBounds(21, 292, 150, 16);
		add(lblNumberOfCopies);

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
			return; 
		} else if (numCopy < 1) {
			System.out.println("Num copy must be greater than 1");
			return;
		}

		// Add to system
		try {
			ci.addBook(book);
			System.out.print(book.getTitle() + " is added");
			clearInput();
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	
	private void clearInput() { 
		textFieldTitle.setText(null);
		textFieldIsbn.setText(null);
		textFieldLength.setText(null);
		listAuthor.setListData(new String[0]);
		authors = new ArrayList<>();
	}
}
