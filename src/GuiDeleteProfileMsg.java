import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Message that displays after deleting customer profile
public class GuiDeleteProfileMsg extends GuiBaseMessage implements ActionListener {

    // GuiDeleteProfileMsg constructor
    GuiDeleteProfileMsg(GuiMainMenu mainMenu) {
        // Call parent constructor
        super(mainMenu);
        // Set title
        this.setTitle("Delete Profile");
        // Set text for button
        button.setText("Confirm");

        // Header label
        headerLabel.setText("Delete Profile");
        headerLabel.setBounds(150, 20, 300, 50);
        headerLabel.setFont(new Font("serif", Font.PLAIN, 35));

        // Successful deletion label
        messageLabel.setText("Profile Deleted!");
        messageLabel.setBounds(175, 100, 300, 50);
        messageLabel.setFont(new Font("serif", Font.PLAIN, 20));

        // Display screen
        this.setVisible(true);
    }

    // Test
    public static void main(String[] args) throws FileNotFoundException {
        // Create db object
        CustomerProfDB db = new CustomerProfDB("src/customers.txt");
        try {
            // Initialize db
            db.initializeDB("src/customers.txt");
            // Create new no profiles screen
            new GuiDeleteProfileMsg(new GuiMainMenu(db));
        } catch (FileNotFoundException e) {
            // Catch exception and display file not found
            System.out.println("File not found");
        }
    }
}
