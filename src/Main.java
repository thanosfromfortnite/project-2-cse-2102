import java.io.IOException;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		String directory = System.getProperty("user.dir") + "\\src\\";
		String fileName = "customers.txt";
		CustomerProfInterface inter = new CustomerProfInterface(directory + fileName);
		
		while (inter.getUserChoice()) {
			
		}
		
		System.out.println("Exiting...");
	}
}
