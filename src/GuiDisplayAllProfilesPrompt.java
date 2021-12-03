import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Allows user to enter an admin ID to see all customer profiles under that admin ID
public class GuiDisplayAllProfilesPrompt extends GuiBaseMenuOption implements ActionListener {

    // GuiDisplayAllProfilesPrompt
    GuiDisplayAllProfilesPrompt(GuiMainMenu mainMenu) {
        // Call parent constructor
        super(mainMenu);
        // Set title
        this.setTitle("Display All Profiles");

        // Remove unneeded fields
        this.remove(lastNameLabel);
        this.remove(lastNameTextField);

        // Set text and bounds for button, header label and error label
        button.setText("Display");
        headerLabel.setText("Display All Profiles");
        headerLabel.setBounds(120, 20, 300, 50);
        errorLabel.setText("No customer profiles found.");

        // Display screen
        this.setVisible(true);
    }

    @Override
    public void actionHandler() {
        CustomerProf firstProf = menu.db.findFirstProfile();
        while (!firstProf.getAdminID().equals(adminIDTextField.getText())) {
            firstProf = menu.db.findNextProfile();
        }
        new GuiDisplayAllProfiles(menu, firstProf, adminIDTextField.getText());
    }

    // Test
    public static void main(String[] args) throws FileNotFoundException {
        // Create db object
        CustomerProfDB db = new CustomerProfDB("src/customers.txt");
        try {
            // Initialize db
            db.initializeDB("src/customers.txt");
            // Create new display all profiles prompt screen
            new GuiDisplayAllProfilesPrompt(new GuiMainMenu(db));
        } catch (FileNotFoundException e) {
            // Catch exception and display file not found
            System.out.println("File not found");
        }
    }
}
