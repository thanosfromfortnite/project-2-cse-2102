import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Message that displays when there are no customer profiles
public class GuiNoProfilesMsg extends GuiBaseMessage implements ActionListener {

    // GuiNoProfilesMsg constructor
    GuiNoProfilesMsg(GuiMainMenu mainMenu) {
        // Call parent constructor
        super(mainMenu);
        // Set title
        this.setTitle("No Profiles Found");
        // Set text for button, header label, and message label
        button.setText("Back");
        headerLabel.setText("Unable to perform task");
        messageLabel.setText("No profiles found. Please first create a customer profile.");
        // Display screen
        this.setVisible(true);
    }

    // Test
    public static void main(String[] args) throws FileNotFoundException {
        // Create db object
        CustomerProfDB db = new CustomerProfDB("src/empty.txt");
        try {
            // Initialize empty db
            db.initializeDB("src/empty.txt");
            // Create new no profiles screen
            new GuiNoProfilesMsg(new GuiMainMenu(db));
        } catch (FileNotFoundException e) {
            // Catch exception and display file not found
            System.out.println("File not found");
        }
    }
}
