import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Allows user to find customer profile by admin ID and last name
public class GuiFindProfile extends GuiBaseMenuOption implements ActionListener {

    // GuiFindProfile constructor
    GuiFindProfile(GuiMainMenu mainMenu) {
        // Call parent constructor
        super(mainMenu);
        // Set title
        this.setTitle("Find Profile");
        // Set text for button and header label
        button.setText("Find");
        headerLabel.setText("Find Profile");
        // Display screen
        this.setVisible(true);
    }

    @Override
    public void actionHandler() {
        // Find customer profile by admin ID and last name then display the profile
        new GuiDisplayProfile(menu, menu.db.findProfile(adminIDTextField.getText(), lastNameTextField.getText()));
    }

    // Test
    public static void main(String[] args) throws FileNotFoundException {
        // Create db object
        CustomerProfDB db = new CustomerProfDB("src/customers.txt");
        try {
            // Initialize db
            db.initializeDB("src/customers.txt");
            // Create new find profile screen
            new GuiFindProfile(new GuiMainMenu(db));
        } catch (FileNotFoundException e) {
            // Catch exception and display file not found
            System.out.println("File not found");
        }
    }

}
