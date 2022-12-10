package librarysystem.mainUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

import librarysystem.Constant;

public class AboutUsPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public AboutUsPanel() {
		setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("MIU Library System Group 2");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(6, 38, Constant.CONTENT_WIDTH, 28);
		add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(85, 102, 338, 137);
		add(panel);
		panel.setLayout(new GridLayout(0, 3, 10, 0));
		
		JLabel lblNewLabel_2 = new JLabel("1");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Vanara Leng");
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("615690");
		panel.add(lblNewLabel_2_1_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("2");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("You Kim Chhay");
		panel.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("615725");
		panel.add(lblNewLabel_2_1_2_1);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("3");
		lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Salah Daoud");
		panel.add(lblNewLabel_2_1_1_1);
		
		JLabel lblNewLabel_2_1_2_1_1 = new JLabel("615689");
		panel.add(lblNewLabel_2_1_2_1_1);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("4");
		lblNewLabel_2_2_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblNewLabel_2_2_1_1);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Trung Dung Ly");
		panel.add(lblNewLabel_2_1_1_1_1);
		
		JLabel lblNewLabel_2_1_2_1_1_1 = new JLabel("615706");
		panel.add(lblNewLabel_2_1_2_1_1_1);
		
		JLabel lblNewLabel_2_1_3 = new JLabel("Members:");
		lblNewLabel_2_1_3.setBounds(85, 67, 106, 42);
		add(lblNewLabel_2_1_3);
		
		JLabel lblNewLabel_2_1_3_1 = new JLabel("Special thanks to: ");
		lblNewLabel_2_1_3_1.setBounds(85, 287, 338, 21);
		add(lblNewLabel_2_1_3_1);
		
		JLabel lblNewLabel_2_1_3_1_1 = new JLabel("Prof. Renuka Mohanraj");
		lblNewLabel_2_1_3_1_1.setBounds(204, 315, 212, 28);
		add(lblNewLabel_2_1_3_1_1);
		
		JLabel lblNewLabel_2_1_3_1_1_1 = new JLabel("TA. Burmaa Enkhbat");
		lblNewLabel_2_1_3_1_1_1.setBounds(204, 339, 212, 28);
		add(lblNewLabel_2_1_3_1_1_1);

	}
}
