import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Allows user to create a new customer profile
public class GuiCreateProfile extends GuiBaseMenuOption implements ActionListener {
    // Combo boxes for status, use, vehicle type, and method
    JComboBox<String> statusTypeDropdown, useTypeDropdown, vehicleTypeDropdown, methodTypeDropdown;
    // Labels for header and customer profile properties
    JLabel[] labels = new JLabel[13];
    // Text fields for customer profile properties that require typed input
    JTextField[] textFields = new JTextField[8];
    // Boolean that indicates if user input contains errors
    boolean hasErrors;
    // Default border for text fields with no errors
    Border defaultBorder;

    // GuiCreateProfile constructor
    GuiCreateProfile(GuiMainMenu mainMenu) {
        // Call parent constructor
        super(mainMenu);

        // Remove unneeded fields
        this.remove(adminIDLabel);
        this.remove(adminIDTextField);
        this.remove(lastNameLabel);
        this.remove(lastNameTextField);

        // Set title and size
        this.setTitle("Create Profile");
        this.setSize(500, 620);

        // Submit button
        button.setText("Submit");
        button.setBounds(175, 500, 150, 40);

        // Header label
        headerLabel.setText("Create Profile");
        headerLabel.setBounds(150, 20, 300, 50);

        // Field labels
        labels[0] = headerLabel;
        labels[1] = new JLabel("Admin ID: ");
        labels[2] = new JLabel("First Name: ");
        labels[3] = new JLabel("Last Name: ");
        labels[4] = new JLabel("Address: ");
        labels[5] = new JLabel("Phone Number: ");
        labels[6] = new JLabel("Income: ");
        labels[7] = new JLabel("Status: ");
        labels[8] = new JLabel("Use: ");
        labels[9] = new JLabel("Vehicle Model: ");
        labels[10] = new JLabel("Vehicle Year: ");
        labels[11] = new JLabel("Vehicle Type: ");
        labels[12] = new JLabel("Vehicle Method: ");

        // Set properties for field labels
        int yInc = 0;
        for (int i = 1; i < labels.length; i++) {
            labels[i].setFont(new Font("serif", Font.PLAIN, 18));
            labels[i].setBounds(55, 70+yInc, 120, 30);
            this.add(labels[i]);
            yInc += 33;
        }

        // Set properties for text fields
        yInc = 0;
        for (int i = 0; i < textFields.length; i++) {
            textFields[i] = new JTextField(20);
            textFields[i].setFont(new Font ("serif", Font.PLAIN, 18));
            if (i >= 6) {
                textFields[i].setBounds(new Rectangle(185, 72+66+yInc, 250, 30));
            } else {
                textFields[i].setBounds(new Rectangle(185, 72+yInc, 250, 30));
            }
            this.add(textFields[i]);
            yInc += 33;
        }

        // Set default border
        defaultBorder = textFields[0].getBorder();

        // Status type combo box
        statusTypeDropdown = new JComboBox<>(statusTypeOptions);
        statusTypeDropdown.setBounds(185, 303, 255, 32);
        statusTypeDropdown.setFont(new Font("serif", Font.PLAIN, 18));
        statusTypeDropdown.setAlignmentX((float) 0.5);

        // Use type combo box
        useTypeDropdown = new JComboBox<>(useTypeOptions);
        useTypeDropdown.setBounds(185, 268, 255, 32);
        useTypeDropdown.setFont(new Font("serif", Font.PLAIN, 18));
        useTypeDropdown.setAlignmentX((float) 0.5);

        // Vehicle type combo box
        vehicleTypeDropdown = new JComboBox<>(vehicleTypeOptions);
        vehicleTypeDropdown.setBounds(185, 402, 255, 32);
        vehicleTypeDropdown.setFont(new Font("serif", Font.PLAIN, 18));
        vehicleTypeDropdown.setAlignmentX((float) 0.5);

        // Vehicle method type combo box
        methodTypeDropdown = new JComboBox<>(methodTypeOptions);
        methodTypeDropdown.setBounds(185, 435, 255, 32);
        methodTypeDropdown.setFont(new Font("serif", Font.PLAIN, 18));
        methodTypeDropdown.setAlignmentX((float) 0.5);

        // Error label
        errorLabel.setText("");
        errorLabel.setBounds(new Rectangle(140, 470, 300, 45));
        errorLabel.setFont(new Font("serif", Font.BOLD, 10));
        errorLabel.setForeground(Color.red);

        // Add components
        this.add(statusTypeDropdown);
        this.add(useTypeDropdown);
        this.add(vehicleTypeDropdown);
        this.add(methodTypeDropdown);

        // Set visible to true
        this.setVisible(true);
    }

    // Helper function that checks if field is empty
    public void checkIfEmpty(JTextField field) {
        // If field is empty, then user input contains an error
        if (field.getText().isEmpty()) {
            hasErrors = true;
            // Display error label
            if (!errorLabel.getText().contains("* Missing one or more fields")) {
                errorLabel.setText(errorLabel.getText() + "* Missing one or more fields<br>");
            }
            // Set red border around empty field
            field.setBorder(new LineBorder(Color.red, 1));
            // Add error label
            this.add(errorLabel);
            // Update component
            this.repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // Create new profile if submit button is clicked
        if (event.getSource() == button) {
            hasErrors = false;
            // Begin html for error label
            errorLabel.setText("<html>");

            // Reset text field borders
            textFields[0].setBorder(defaultBorder); // admin ID
            textFields[1].setBorder(defaultBorder); // first name
            textFields[2].setBorder(defaultBorder); // last name
            textFields[3].setBorder(defaultBorder); // address
            textFields[4].setBorder(defaultBorder); // phone number
            textFields[5].setBorder(defaultBorder); // income
            textFields[6].setBorder(defaultBorder); // vehicle model
            textFields[7].setBorder(defaultBorder); // vehicle year

            // If admin ID is invalid, display error label
            if (!menu.db.getValidAdminIDs().contains(textFields[0].getText())) {
                hasErrors = true;
                // Display error label
                errorLabel.setText(errorLabel.getText() + "* Unable to authenticate Admin ID<br>");
                // Set red border around admin ID field
                textFields[0].setBorder(new LineBorder(Color.red, 1));
                // Add error label and update component
                this.add(errorLabel);
                this.repaint();
            }

            // Check if first name, last name, address, or vehicle model are empty
            checkIfEmpty(textFields[1]);
            checkIfEmpty(textFields[2]);
            checkIfEmpty(textFields[3]);
            checkIfEmpty(textFields[6]);

            // New vehicle information
            VehicleInfo newVehicleInfo = new VehicleInfo(textFields[6].getText(), "0000", (String) vehicleTypeDropdown.getSelectedItem(), (String) methodTypeDropdown.getSelectedItem());

            // Check if vehicle year is valid before setting
            if (textFields[7].getText().length() != 4) {
                hasErrors = true;
                errorLabel.setText(errorLabel.getText() + "* Vehicle year must be 4 digits (e.g. 1998)<br>");
                textFields[7].setBorder(new LineBorder(Color.red, 1));
                this.add(errorLabel);
                this.repaint();
            } else {
                newVehicleInfo.updateYear(textFields[7].getText());
            }

            // New customer profile
            CustomerProf newCustomerProf = new CustomerProf(textFields[0].getText(), textFields[1].getText(), textFields[2].getText(), textFields[3].getText(), "000-000-0000", 0, (String) statusTypeDropdown.getSelectedItem(), (String) useTypeDropdown.getSelectedItem(), newVehicleInfo);

            // Check if phone number is valid before setting
            if (textFields[4].getText().length() != 10) {
                hasErrors = true;
                errorLabel.setText(errorLabel.getText() + "* Phone number must be 10 digits (e.g. 1235559999)<br>");
                textFields[4].setBorder(new LineBorder(Color.red, 1));
                this.add(errorLabel);
                this.repaint();
            } else {
                newCustomerProf.updatePhone(textFields[4].getText());
            }

            // Try to update income, display error label if input is not a float
            try {
                newCustomerProf.updateIncome(Float.parseFloat(textFields[5].getText()));
            } catch (Exception e){
                hasErrors = true;
                errorLabel.setText(errorLabel.getText() + "* Income must be a float<br>");
                textFields[5].setBorder(new LineBorder(Color.red, 1));
                this.add(errorLabel);
                this.repaint();
            }

            // End html for error label
            errorLabel.setText(errorLabel.getText() + "</html>");

            // If no errors, insert new customer profile and return to main menu
            if (!hasErrors) {
                menu.db.insertNewProfile(newCustomerProf);
                menu.returnToMainMenu(this);
            }
        }
    }

    // Test
    public static void main(String[] args) throws FileNotFoundException {
        // Create db object
        CustomerProfDB db = new CustomerProfDB("src/customers.txt");
        try {
            // Initialize db
            db.initializeDB("src/customers.txt");
            // Make new create profile screen
            new GuiCreateProfile(new GuiMainMenu(db));
        } catch (FileNotFoundException e) {
            // Catch exception and display file not found
            System.out.println("File not found");
        }
    }
}
