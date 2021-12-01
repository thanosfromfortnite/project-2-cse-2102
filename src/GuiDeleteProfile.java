import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Allows user to delete customer profile by admin ID and last name
public class GuiDeleteProfile extends GuiBaseMenuOption implements ActionListener {

    // GuiDeleteProfile constructor
    GuiDeleteProfile(GuiMainMenu mainMenu) {
        // Call parent constructor
        super(mainMenu);
        // Set title
        this.setTitle("Delete Profile");
        // Set text for button and header label
        button.setText("Delete");
        headerLabel.setText("Delete Profile");
        // Display screen
        this.setVisible(true);
    }

    @Override
    public void actionHandler() {
        // Delete customer profile by admin ID and last name
        menu.db.deleteProfile(adminIDTextField.getText(), lastNameTextField.getText());
        // Display delete confirmation
        new GuiDeleteProfileMsg(menu);
    }

    // Test
    public static void main(String[] args) throws FileNotFoundException {
        // Create db object
        CustomerProfDB db = new CustomerProfDB("src/customers.txt");
        try {
            // Initialize db
            db.initializeDB("src/customers.txt");
            // Create new delete profile screen
            new GuiDeleteProfile(new GuiMainMenu(db));
        } catch (FileNotFoundException e) {
            // Catch exception and display file not found
            System.out.println("File not found");
        }
    }


}
