import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.NoSuchElementException;

// Allows user to update selected attribute of customer profile
public class GuiUpdateProfileConfirm extends JFrame implements ActionListener {
    JButton updateButton;
    JLabel headerLabel, adminIDLabel, lastNameLabel, updateFieldLabel, errorLabel;
    JTextField updateTextField;
    JComboBox<String> updatedDropdown;
    GuiUpdateProfile update;
    CustomerProf customerProf;

    // Create text field for field to update
    private void createTextField() {
        updateTextField = new JTextField(20);
        updateTextField.setBounds(200, 158, 250, 33);
        updateTextField.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(updateTextField);
    }

    // Create dropdown for field to update
    private void createDropdown(String[] arr) {
        updatedDropdown = new JComboBox<>(arr);
        updatedDropdown.setBounds(190, 160, 250, 33);
        updatedDropdown.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(updatedDropdown);
    }

    // Throws no such element exception if field is empty
    private void checkIfEmpty(JTextField field) {
        if (field.getText().isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    // GuiUpdateProfileConfirm constructor
    GuiUpdateProfileConfirm(final GuiUpdateProfile updateProfile) {
        // Set update variable to be updateProfile input
        update = updateProfile;

        // Return to update profile screen when window is closed
        final GuiUpdateProfileConfirm guiUpdateProfileConfirm = this;
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                guiUpdateProfileConfirm.dispose();
                new GuiUpdateProfile(updateProfile.menu);
            }
        });

        // Find customer profile using admin ID and last name
        customerProf = update.menu.db.findProfile(update.adminIDTextField.getText(), update.lastNameTextField.getText());

        // Set background, title, and size
        Color c1 = new Color(21, 210, 131);
        this.getContentPane().setBackground(c1);
        this.setTitle("Update");
        this.setLayout(null);
        this.setSize(500, 320);

        // Update button
        updateButton = new JButton("Submit");
        updateButton.setBounds(175, 220, 150, 40);
        updateButton.setFont(new Font("serif", Font.PLAIN, 20));
        updateButton.addActionListener(this);

        // Header label
        headerLabel = new JLabel("Update");
        headerLabel.setBounds(195, 20, 300, 50);
        headerLabel.setFont(new Font("serif", Font.PLAIN, 35));

        // Admin ID label
        adminIDLabel = new JLabel("Admin ID:           " + update.adminIDTextField.getText());
        adminIDLabel.setBounds(60, 70, 300, 50);
        adminIDLabel.setFont(new Font("serif", Font.PLAIN, 20));

        // Last name label
        lastNameLabel = new JLabel ("Last Name:         " + update.lastNameTextField.getText());
        lastNameLabel.setBounds(60, 110, 300, 50);
        lastNameLabel.setFont(new Font("serif", Font.PLAIN, 20));

        // Field to update label
        updateFieldLabel = new JLabel(update.choiceDropdown.getSelectedItem() + ":");
        updateFieldLabel.setBounds(60, 150, 150, 50);
        updateFieldLabel.setFont(new Font("serif", Font.PLAIN, 20));

        // Error label
        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setBounds(0, 190, 500, 33);
        errorLabel.setFont(new Font("serif", Font.BOLD, 14));
        errorLabel.setForeground(Color.red);

        // Add components
        this.add(updateButton);
        this.add(headerLabel);
        this.add(adminIDLabel);
        this.add(lastNameLabel);
        this.add(updateFieldLabel);
        this.add(errorLabel);

        // Switch for dropdowns
        switch(update.choice) {
            case 2:
                // Create dropdown for use type options
                createDropdown(update.useTypeOptions);
                break;
            case 3:
                // Create dropdown for status type options
                createDropdown(update.statusTypeOptions);
                break;
            case 6:
                // Create dropdown for vehicle type options
                createDropdown(update.vehicleTypeOptions);
                break;
            case 7:
                // Create dropdown for method type options
                createDropdown(update.methodTypeOptions);
                break;
            default:
                // Create text field for all other options
                createTextField();
        }

        // Set resizable to false and visible to true
        this.setResizable(false);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == updateButton) {
            // Switch for user choice
            switch(update.choice) {
                case 0:
                    // Update address
                    try {
                        // If field is not empty, update address then return to main menu
                        checkIfEmpty(updateTextField);
                        customerProf.updateAddress(updateTextField.getText());
                        update.menu.returnToMainMenu(this);
                    } catch(NoSuchElementException e) {
                        // Catch exceptions and display error message
                        errorLabel.setText("This field cannot be empty.");
                        this.repaint();
                    }
                    break;
                case 1:
                    // Update phone number
                    try {
                        // If field is not empty, update phone number then return to main menu
                        checkIfEmpty(updateTextField);
                        customerProf.updatePhone(updateTextField.getText());
                        update.menu.returnToMainMenu(this);
                    } catch(NoSuchElementException e) {
                        // Catch exceptions and display error message
                        errorLabel.setText("This field cannot be empty.");
                        this.repaint();
                    } catch(Exception e) {
                        errorLabel.setText("Phone number must be 10 digits (e.g. 1235559999).");
                        this.repaint();
                    }
                    break;
                case 2:
                    // Update use and return to main menu
                    customerProf.updateUse((String) updatedDropdown.getSelectedItem());
                    update.menu.returnToMainMenu(this);
                    break;
                case 3:
                    // Update status and return to main menu
                    customerProf.updateStatus((String) updatedDropdown.getSelectedItem());
                    update.menu.returnToMainMenu(this);
                    break;
                case 4:
                    // Update vehicle model
                    try {
                        // If field is not empty, update vehicle model then return to main menu
                        checkIfEmpty(updateTextField);
                        customerProf.getVehicleInfo().updateModel(updateTextField.getText());
                        update.menu.returnToMainMenu(this);
                    } catch(NoSuchElementException e) {
                        // Catch exceptions and display error message
                        errorLabel.setText("This field cannot be empty");
                        this.repaint();
                    }
                    break;
                case 5:
                    // Update vehicle year
                    try {
                        // If field is not empty, update vehicle year then return to main menu
                        checkIfEmpty(updateTextField);
                        customerProf.getVehicleInfo().updateYear(updateTextField.getText());
                        update.menu.returnToMainMenu(this);
                    } catch(NoSuchElementException e) {
                        // Catch exceptions and display error message
                        errorLabel.setText("This field cannot be empty.");
                        this.repaint();
                    } catch(Exception e) {
                        errorLabel.setText("Vehicle year must be 4 digits (e.g. 1998).");
                        this.repaint();
                    }
                    break;
                case 6:
                    // Update vehicle type and return to main menu
                    customerProf.getVehicleInfo().updateType((String) updatedDropdown.getSelectedItem());
                    update.menu.returnToMainMenu(this);
                    break;
                case 7:
                    // Update vehicle method and return to main menu
                    customerProf.getVehicleInfo().updateMethod((String) updatedDropdown.getSelectedItem());
                    update.menu.returnToMainMenu(this);
                    break;
            }
        }
    }
}
