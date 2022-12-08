package librarysystem.managebook;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;

import business.*;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import java.util.List;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.ImageIcon;

public class AddBookPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldTitle;
	private JTextField textFieldIsbn;
	private JTextField textFieldLength;
	private JList<String> listAuthor;
	
	private List<Author> authors = new ArrayList<>();
	
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
		lblAuthor.setBounds(21, 209, 150, 16);
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
		btnAddAuthor.setBounds(416, 209, 34, 34);
		add(btnAddAuthor);
		
		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.addActionListener( event -> {
			String title = textFieldTitle.getText();
			String isbn = textFieldIsbn.getText();
			String checkoutLength = textFieldLength.getText();
			addBook(isbn, title, checkoutLength);
		});
		
		btnAddBook.setBounds(183, 340, 211, 50);
		add(btnAddBook);
		
		listAuthor = new JList<String>();
		listAuthor.setBounds(183, 209, 211, 93);
		add(listAuthor);

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
	
	void addBook(String isbn, String title, String checkoutLength) {
		int length = Integer.parseInt(checkoutLength);
		Book book = new Book(isbn, title, length, authors);
		String message = book.getValidationMessage();
		System.out.println(message);
	}
}
