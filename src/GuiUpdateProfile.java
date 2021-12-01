import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Allows user to select an attribute to update on customer profile
public class GuiUpdateProfile extends GuiBaseMenuOption implements ActionListener {
    JComboBox<String> choiceDropdown;
    int choice;
    String[] info = {"Address", "Phone", "Use", "Status", "Vehicle Model", "Vehicle Year", "Vehicle Type", "Method of Obtaining Vehicle"};
    JLabel updateFieldLabel;

    // GuiUpdateProfile constructor
    GuiUpdateProfile(GuiMainMenu mainMenu) {
        // Call parent constructor
        super(mainMenu);

        // Set title and size
        this.setTitle("Update Profile");
        this.setSize(500, 620);

        // Set text and bounds for button
        button.setText("Find");
        button.setBounds(175, 500, 150, 40);

        // Set header label text
        headerLabel.setText("Update Profile");

        // Set bounds for admin ID label, last name label, admin ID text field, and last name text field
        adminIDLabel.setBounds(70, 100, 150, 50);
        lastNameLabel.setBounds(70, 150, 150, 50);
        adminIDTextField.setBounds(new Rectangle(220, 110, 210, 33));
        lastNameTextField.setBounds(new Rectangle(220, 160, 210, 33));

        // Set bounds for error label
        errorLabel.setBounds(0, 300, 500, 33);

        // Update label
        updateFieldLabel = new JLabel("Update Field: ");
        updateFieldLabel.setBounds(70, 200, 150, 50);
        updateFieldLabel.setFont(new Font("serif", Font.PLAIN, 20));

        // Choice dropdown
        choiceDropdown = new JComboBox<>(info);
        choiceDropdown.setBounds(220, 210, 220, 33);
        choiceDropdown.setFont(new Font("serif", Font.PLAIN, 20));
        choiceDropdown.setAlignmentX((float) 0.5);

        // Add components
        this.add(updateFieldLabel);
        this.add(choiceDropdown);

        // Display screen
        this.setVisible(true);
    }

    @Override
    public void actionHandler() {
        // Get user choice and display update profile confirmation
        choice = choiceDropdown.getSelectedIndex();
        new GuiUpdateProfileConfirm(this);
    }

    // Test
    public static void main(String[] args) throws FileNotFoundException {
        // Create db object
        CustomerProfDB db = new CustomerProfDB("src/customers.txt");
        try {
            // Initialize db
            db.initializeDB("src/customers.txt");
            // Create new update profile screen
            new GuiUpdateProfile(new GuiMainMenu(db));
        } catch (FileNotFoundException e) {
            // Catch exception and display file not found
            System.out.println("File not found");
        }
    }
}
