package librarysystem;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import librarysystem.mainUI.MainUI;


public class Main {
	
	public static void main(String[] args) {
	      EventQueue.invokeLater(() -> 
	         {
	            MainUI.INSTANCE.setTitle("MIU Library System");
	            MainUI.INSTANCE.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            
	            MainUI.INSTANCE.init();
	            centerFrameOnDesktop(LibrarySystem.INSTANCE);
	            MainUI.INSTANCE.setVisible(true);
	         });
	   }
	   
	   public static void centerFrameOnDesktop(Component f) {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			int height = toolkit.getScreenSize().height;
			int width = toolkit.getScreenSize().width;
			int frameHeight = f.getSize().height;
			int frameWidth = f.getSize().width;
			f.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 3);
		}
}
