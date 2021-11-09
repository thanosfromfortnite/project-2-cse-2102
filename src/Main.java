import java.io.IOException;
import javax.swing.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		String directory = System.getProperty("user.dir") + "\\src\\";
		String fileName = "customers.txt";
		CustomerProfInterface inter = new CustomerProfInterface(directory + fileName);
		
		JFrame frame = new JFrame("Main");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel("Test");
		frame.getContentPane().add(label);
		
		frame.setSize(800, 600);
		frame.setVisible(true);
		
		while (inter.getUserChoice()) {
			
		}
		
		System.out.println("Exiting...");
	}
}
